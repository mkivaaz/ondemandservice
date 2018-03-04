package kivaaz.com.ondemandserviceslibrary;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import kivaaz.com.ondemandserviceslibrary.FirebaseChat.FirebaseConstants;

/**
 * Created by Muguntan on 3/3/2018.
 */

public class GeoCodeConverter {
    Context context;
    LatLng currentGeoCode;

    public GeoCodeConverter(Context context, LatLng currentGeoCode) {
        this.context = context;
        this.currentGeoCode = currentGeoCode;
    }

    public void getAddress() {
        parser_Json parser_json = new parser_Json(context, "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + currentGeoCode.latitude + ","
                + currentGeoCode.longitude + "&sensor=true");
        parser_json.execute();

    }

    public static class parser_Json extends AsyncTask<Void,Void,StringBuilder> {
        Context context;
        String Stringurl;

        public parser_Json(Context context, String stringurl) {
            this.context = context;
            Stringurl = stringurl;
        }

        @Override
        protected StringBuilder doInBackground(Void... voids) {
            // initialize
            InputStream is = null;
            String result = "";
            JSONObject jObject = null;
            HttpURLConnection conn = null;
            StringBuilder jsonResults = new StringBuilder();
            // http post
            URL url = null;
            try {
                url = new URL(Stringurl);

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
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonResults;
        }


        @Override
        protected void onPostExecute(StringBuilder jsonObject) {
            super.onPostExecute(jsonObject);

            processJsonObject(jsonObject);
        }


        public void processJsonObject(StringBuilder result){
            String Address1 = "", Address2 = "", City = "", County = "", State = "", Country = "",PIN = "";
            String fullAddress = "";
            try {
                Intent intent = new Intent(FirebaseConstants.ADDRESS_GEOCODE_INTENT);
                JSONObject jsonObj = null;
                jsonObj = new JSONObject(result.toString());
                JSONArray resultJsonArray = jsonObj.getJSONArray("results");
                Log.d("JSON ARRAY: ", resultJsonArray.toString());

                if(resultJsonArray.length() > 0){
                    JSONObject address_components_jsonObj = resultJsonArray.getJSONObject(0);
                    JSONArray zero2 = address_components_jsonObj.getJSONArray("address_components");
                    for (int i = 0; i < address_components_jsonObj.length(); i++) {
                        JSONObject zero3 = zero2.getJSONObject(i);
                        String long_name = zero3.getString("long_name");
                        JSONArray mtypes = zero3.getJSONArray("types");
                        String Type = mtypes.getString(0);

                        if (TextUtils.isEmpty(long_name) == false || !long_name.equals(null) || long_name.length() > 0 || long_name != "") {
                            if (Type.equalsIgnoreCase("street_number")) {
                                Address1 = long_name + " ";
                                if(i != (address_components_jsonObj.length() - 1)){
                                    fullAddress = fullAddress + Address1 + ", ";
                                }else{
                                    fullAddress = fullAddress + Address1;
                                }
                            } else if (Type.equalsIgnoreCase("route")) {
                                Address1 = Address1 + long_name;
                                if(i != (address_components_jsonObj.length() - 1)){
                                    fullAddress = fullAddress + Address1 + ", ";
                                }else{
                                    fullAddress = fullAddress + Address1;
                                }
                            } else if (Type.equalsIgnoreCase("sublocality")) {
                                Address2 = long_name;
                                if(i != (address_components_jsonObj.length() - 1)){
                                    fullAddress = fullAddress + Address2 + ", ";
                                }else{
                                    fullAddress = fullAddress + Address2;
                                }
                            } else if (Type.equalsIgnoreCase("locality")) {
                                // Address2 = Address2 + long_name + ", ";
                                City = long_name;
                                if(i != (address_components_jsonObj.length() - 1)){
                                    fullAddress = fullAddress + City + ", ";
                                }else{
                                    fullAddress = fullAddress + City;
                                }
                            } else if (Type.equalsIgnoreCase("administrative_area_level_2")) {
                                County = long_name;
                                if(i != (address_components_jsonObj.length() - 1)){
                                    fullAddress = fullAddress + County + ", ";
                                }else{
                                    fullAddress = fullAddress + County;
                                }
                            } else if (Type.equalsIgnoreCase("administrative_area_level_1")) {
                                State = long_name;
                                if(i != (address_components_jsonObj.length() - 1)){
                                    fullAddress = fullAddress + State + ", ";
                                }else{
                                    fullAddress = fullAddress + State;
                                }
                            } else if (Type.equalsIgnoreCase("country")) {
                                Country = long_name;
                                if(i != (address_components_jsonObj.length() - 1)){
                                    fullAddress = fullAddress + Country + ", ";
                                }else{
                                    fullAddress = fullAddress + Country;
                                }
                            } else if (Type.equalsIgnoreCase("postal_code")) {
                                PIN = long_name;
                                if(i != (address_components_jsonObj.length() - 1)){
                                    fullAddress = fullAddress + PIN + " ";
                                }else{
                                    fullAddress = fullAddress + PIN;
                                }
                            }
                        }
                    }
                    intent.putExtra(DataIntent.LOCATION_STRINGFY,fullAddress);
                    Log.d("JSON_DATA: ",fullAddress);
                }
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
