package kivaaz.com.ondemandserviceslibrary;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Muguntan on 12/19/2017.
 */

public class LocationUpdateService extends Service {

    private final static String TAG = "LOCATIONSERVICE";

    private FusedLocationProviderClient mFusedLocationClient;

    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;

    // Handler
    private Handler mServiceHandler;

    private String intent_update;
    private Class<?> notificationContext;

    // location data
    private final static int LOCATION_INTERVAL = 4000; // 2 second
    private final static int LOCATION_FASTEST_INTERVAL = LOCATION_INTERVAL / 2; // 1 second

    // geocoder
    private final static int MAX_ADDRESS_GEOCODE = 1;

    Location prev_location = null;
    public static boolean IS_SERVICE_RUNNING = false;
    IBinder mBinder;

    String userId, userCountry;

    @Override
    public void onCreate() {
        super.onCreate();

//        showNotification();

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        createLocationCallback();
        createLocationRequest();

        HandlerThread handler = new HandlerThread(TAG);
        handler.start();

        mServiceHandler = new Handler(handler.getLooper());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(LOCATION_INTERVAL);
        mLocationRequest.setFastestInterval(LOCATION_FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void onGetLocation(Location locations) {
        if (locations != null){

            Intent intent = new Intent(DataIntent.CURRENT_GEOCODE_INTENT);
            intent.putExtra(DataIntent.LOCATION_LATITUDE,locations.getLatitude());
            intent.putExtra(DataIntent.LOCATION_LONGITUDE,locations.getLongitude());

            if(prev_location != null){

                intent.putExtra(DataIntent.LOCATION_BEARING,prev_location.bearingTo(locations));
                intent.putExtra(DataIntent.LOCATION_STRINGFY,getLocationCode(locations,prev_location));
                prev_location = locations;


            }else {
                prev_location = locations;
            }

            LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(intent);
            Log.d("SERVICE: ", "IS RUNNING");

//            sendLocationReq(userId,userCountry,locations.getLatitude(),locations.getLongitude());
        }
    }

    private String getLocationCode(Location location, Location prev_location) {
        String addrString = "";
        Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());

        List<Address> addresses = new ArrayList<>();
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),MAX_ADDRESS_GEOCODE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addresses.size()!= 0){
            Address address = addresses.get(0);
            if(!address.getFeatureName().isEmpty()){
                addrString = parseAddr(addrString, address.getFeatureName());
            }else{
                addrString = parseAddr(addrString, address.getThoroughfare());
                addrString = parseAddr(addrString, address.getLocality());
                
            }
        }

        if (addrString.isEmpty()){
            addrString = String.format(Locale.getDefault(),"%.6f, %.6f, %.6f",location.getLatitude(),location.getLatitude(),prev_location.bearingTo(location));
        }

        return addrString;
    }


    private String parseAddr(String addrString, String featureName) {
        if(!featureName.isEmpty()){
            if(!addrString.isEmpty()){
                addrString += ", ";
            }
            addrString = featureName;
        }

        return addrString;
    }

    private void createLocationCallback() {
        mLocationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                onGetLocation(locationResult.getLastLocation());
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Requesting location updates");

//        userId = intent.getStringExtra(DataIntent.USERID_LABEL);
//        userCountry = intent.getStringExtra(DataIntent.COUNTRYID_LABEL);
//        intent_update = intent.getStringExtra(DataIntent.LOCATION_INTENT);

        startLocationService();

        return START_STICKY;
    }

    private void startLocationService() {
        Log.d(TAG, "Starting location updates");

        try{
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, mServiceHandler.getLooper());
        }catch (SecurityException e){
            Log.d(TAG,"Permission Problem: " + e.getMessage());
        }

    }

    @Override
    public void onDestroy() {
        stopLocationService();
        mServiceHandler.removeCallbacksAndMessages(null);
        stopSelf();
        Log.d("SERVICE: ", "IS STOPPED");
    }

    private void stopLocationService() {
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        stopSelf();
    }

    public void showNotification(){
        Intent notificationIntent = null;
        notificationIntent = new Intent(this,getClass()); //add class here
        notificationIntent.setAction(getPackageName() + ".Dashboard");
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,0);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("You are Online")
                .setTicker("You are Online")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .build();
        startForeground(1992,notification);
    }


}
