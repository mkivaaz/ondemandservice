package kivaaz.com.ondemandserviceslibrary;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import kivaaz.com.ondemandserviceslibrary.FirebaseChat.FirebaseConstants;

/**
 * Created by Muguntan on 2/16/2018.
 */

public class AddressConverter {
    Context context;
    Double lat;
    Double lng;
    LatLng point;

    public AddressConverter(Context context) {
        this.context = context;
    }

    public void setGeocode(LatLng point){
        this.point = point;
    }public LatLng getGeocode(){
        return point;
    }

    public void setAddress(String place) {

        Address location = null;
        fetchLatLongFromService fetch_latlng_from_service_abc = new fetchLatLongFromService(
                place);
        fetch_latlng_from_service_abc.execute();;
//        try {
//            Geocoder selected_place_geocoder = new Geocoder(context);
//            List<Address> address;
//
//            address = selected_place_geocoder.getFromLocationName(place, 5);
//
//            if (address == null) {
//                Toast.makeText(context,"Address is Empty",Toast.LENGTH_SHORT).show();
//            } else {
//                location = address.get(0);
//                lat= location.getLatitude();
//                lng = location.getLongitude();
//                LatLng point = new LatLng(lat,lng);
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            fetchLatLongFromService fetch_latlng_from_service_abc = new fetchLatLongFromService(
//                    place.replaceAll("\\s+", ""));
//            fetch_latlng_from_service_abc.execute();
//
//        }

    }


//Sometimes happens that device gives location = null

    public class fetchLatLongFromService extends AsyncTask<Void, Void, StringBuilder> {
        String place;

        public fetchLatLongFromService(String place) {
            super();
            this.place = place;

        }

        @Override
        protected void onCancelled() {
            // TODO Auto-generated method stub
            super.onCancelled();
            this.cancel(true);
        }

        @Override
        protected StringBuilder doInBackground(Void... params) {
            // TODO Auto-generated method stub
            try {
                HttpURLConnection conn = null;
                StringBuilder jsonResults = new StringBuilder();

                String googleMapUrl = "http://maps.googleapis.com/maps/api/geocode/json?address="+place.replace(" ","%20")+"&sensor=false";
                Log.d("ADDRESS: ", googleMapUrl);
                URL url = new URL(googleMapUrl);
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(15000);
                conn.setReadTimeout(15000);

                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                int read;
                char[] buff = new char[1024];
                while ((read = in.read(buff)) != -1) {
                    jsonResults.append(buff, 0, read);
                }
                String a = "";
                return jsonResults;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(StringBuilder result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            try {

                    JSONObject jsonObj = new JSONObject(result.toString());
                    JSONArray resultJsonArray = jsonObj.getJSONArray("results");
                    Log.d("JSON ARRAY: ", resultJsonArray.toString());

                    // Extract the Place descriptions from the results
                    // resultList = new ArrayList<String>(resultJsonArray.length());
                    if(resultJsonArray.length() > 0){
                    JSONObject before_geometry_jsonObj = resultJsonArray.getJSONObject(0);

                    JSONObject geometry_jsonObj = before_geometry_jsonObj.getJSONObject("geometry");

                    JSONObject location_jsonObj = geometry_jsonObj.getJSONObject("location");

                    String lat_helper = location_jsonObj.getString("lat");
                    lat = Double.valueOf(lat_helper);


                    String lng_helper = location_jsonObj.getString("lng");
                    lng = Double.valueOf(lng_helper);


                    LatLng point = new LatLng(lat, lng);
                    setGeocode(point);

                    Intent intent = new Intent(FirebaseConstants.FIREBASE_GEOCODE_INTENT);
                    intent.putExtra(DataIntent.LOCATION_LATITUDE,point.latitude);
                    intent.putExtra(DataIntent.LOCATION_LONGITUDE,point.longitude);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                    Log.d("ADDRESS TO GEOCODE: ", "Latitude - " + point.latitude + ", Longitude - " +point.longitude);
                }


            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
        }
    }
}
