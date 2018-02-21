package kivaaz.com.on_demand_service;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.firebase.geofire.core.GeoHash;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

import kivaaz.com.ondemandserviceslibrary.AddressConverter;
import kivaaz.com.ondemandserviceslibrary.DataIntent;
import kivaaz.com.ondemandserviceslibrary.FirebaseChat.FirebaseConstants;
import kivaaz.com.ondemandserviceslibrary.LocationUpdateService;

public class StoreRestaurantActivity extends AppCompatActivity {
    EditText rest_address, rest_name;
    Button storeBtn, findNearby;
    String name;
    String address;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Dialog dialog;

    Intent locationService;
    LatLng currentPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_restaurant);
        mAuth = FirebaseAuth.getInstance();
        rest_name = findViewById(R.id.rest_name);
        rest_address = findViewById(R.id.rest_address);

        storeBtn = findViewById(R.id.storeAdd);
        findNearby = findViewById(R.id.findNearby);
        database = FirebaseDatabase.getInstance();

        dialog = new Dialog(getBaseContext());
        dialog.setTitle("Storing Data");
//        storeBtn.setEnabled(false);
        rest_name.setText("Sushi King");

        rest_address.setText("LG1.42, 3, Jalan PJS 11/15, Bandar Sunway, 46150 Subang Jaya, Selangor");

        findNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationService = new Intent(getBaseContext(), LocationUpdateService.class);
                startService(locationService);
            }
        });

        storeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = rest_name.getText().toString();
                address = rest_address.getText().toString();
                Log.d("ADDRESS: ", address);

                if(name.isEmpty() || address.isEmpty()){
                    name = "McDonald's Drive-Thru Cyberjaya";
                    address ="Persiaran Apec, Cyber 5, 63000 Cyberjaya, Selangor";
                    AddressConverter converter = new AddressConverter(getBaseContext());
                    converter.setAddress(address);
                }else{
                    AddressConverter converter = new AddressConverter(getBaseContext());
                    converter.setAddress(address);
                }


            }
        });
    }

    BroadcastReceiver loc_receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            currentPoint = new LatLng(intent.getExtras().getDouble(DataIntent.LOCATION_LATITUDE),intent.getExtras().getDouble(DataIntent.LOCATION_LONGITUDE));
            stopService(locationService);
            getAllRestaurants(currentPoint);
        }
    };
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            LatLng point = new LatLng(intent.getExtras().getDouble(DataIntent.LOCATION_LATITUDE),intent.getExtras().getDouble(DataIntent.LOCATION_LONGITUDE));
            if(point!=null){
                Log.d("ADDRESS TO GEOCODE: ", "Latitude - " + point.latitude + ", Longitude - " +point.longitude);
                storeRestaurant(point);
            }else{
                Log.d("ADDRESS TO GEOCODE: ", "Null");
            }
        }
    };
    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(getBaseContext()).unregisterReceiver(receiver);
        LocalBroadcastManager.getInstance(getBaseContext()).unregisterReceiver(loc_receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(getBaseContext()).registerReceiver(receiver, new IntentFilter(FirebaseConstants.FIREBASE_GEOCODE_INTENT));
        LocalBroadcastManager.getInstance(getBaseContext()).registerReceiver(loc_receiver, new IntentFilter(DataIntent.CURRENT_GEOCODE_INTENT));
    }

    public void storeRestaurant(LatLng point){
        myRef = database.getReference("Restaurant");
        GeoLocation location = new GeoLocation(point.latitude,point.longitude);
        GeoHash hash = new GeoHash(location);
        GeoFire geoFire = new GeoFire(myRef);

//        geoFire.setLocation("McDonald's", location, new GeoFire.CompletionListener() {
//            @Override
//            public void onComplete(String key, DatabaseError error) {
//                Toast.makeText(getBaseContext(),key + " Details Has Been Stored",Toast.LENGTH_SHORT).show();
//            }
//        });

        RestaurantDetClass restaurant = new RestaurantDetClass(name,address,hash.getGeoHashString(), Arrays.asList(location.latitude,location.longitude));
        String pushKey = myRef.push().getKey();
        restaurant.setRestaurant_Id(hash.getGeoHashString());
        myRef.child(pushKey).setValue(restaurant).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getBaseContext(),"Restaurant Details Has Been Stored",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getAllRestaurants(LatLng currentPoint){

        final Location currentLocation = new Location("");
        currentLocation.setLatitude(currentPoint.latitude);
        currentLocation.setLongitude(currentPoint.longitude);

        myRef = database.getReference("Restaurant");
        GeoFire geoFire = new GeoFire(myRef);

        GeoQuery geoQuery = geoFire.queryAtLocation(new GeoLocation(currentPoint.latitude, currentPoint.longitude), 5);
        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                myRef.child(key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String name = dataSnapshot.child("restaurant_Name").getValue(String.class);
                        Toast toast = Toast.makeText(getBaseContext(),name + " is nearby",Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 20);
                                toast.show();
                        Log.d("RESTAURANT: ", name);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onKeyExited(String key) {

            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {

            }

            @Override
            public void onGeoQueryReady() {

            }

            @Override
            public void onGeoQueryError(DatabaseError error) {

            }
        });
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    RestaurantDetClass restaurant = snapshot.getValue(RestaurantDetClass.class);
//
//                    Location restaurantLocation = new Location("");
//                    restaurantLocation.setLatitude(restaurant.getLatitude());
//                    restaurantLocation.setLongitude(restaurant.getLongitude());
//
//                    float distanceBetween = currentLocation.distanceTo(restaurantLocation); // distance in meters
//                    if(distanceBetween < 5000){
//                        Log.d("DISTANCE BETWEEN ", restaurant.getRestaurant_Name() + " and You is: " + distanceBetween);
//                    }
//
//
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }
}
