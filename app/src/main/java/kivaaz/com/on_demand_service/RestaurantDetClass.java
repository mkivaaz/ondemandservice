package kivaaz.com.on_demand_service;

import java.util.List;

/**
 * Created by Muguntan on 2/16/2018.
 */

public class RestaurantDetClass {
    String restaurant_Name;
    String restaurant_Address;
//    Double latitude;
//    Double longitude;
    String restaurant_Id;

    String g;
    List<Double> l;

    public RestaurantDetClass() {
    }

    public RestaurantDetClass(String restaurant_Name, String restaurant_Address, String g, List<Double> l) {
        this.restaurant_Name = restaurant_Name;
        this.restaurant_Address = restaurant_Address;
        this.g = g;
        this.l = l;
    }

    //    public RestaurantDetClass(String restaurant_Name, String restaurant_Address, Double latitude, Double longitude) {
//        this.restaurant_Name = restaurant_Name;
//        this.restaurant_Address = restaurant_Address;
//        this.latitude = latitude;
//        this.longitude = longitude;
//    }

    public String getRestaurant_Name() {
        return restaurant_Name;
    }

    public void setRestaurant_Name(String restaurant_Name) {
        this.restaurant_Name = restaurant_Name;
    }

    public String getRestaurant_Address() {
        return restaurant_Address;
    }

    public void setRestaurant_Address(String restaurant_Address) {
        this.restaurant_Address = restaurant_Address;
    }
//
//    public Double getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(Double latitude) {
//        this.latitude = latitude;
//    }
//
//    public Double getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(Double longitude) {
//        this.longitude = longitude;
//    }

    public String getRestaurant_Id() {
        return restaurant_Id;
    }

    public void setRestaurant_Id(String restaurant_Id) {
        this.restaurant_Id = restaurant_Id;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public List<Double> getL() {
        return l;
    }

    public void setL(List<Double> l) {
        this.l = l;
    }
}
