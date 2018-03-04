package kivaaz.com.ondemandserviceslibrary.UserData;

import java.util.List;

/**
 * Created by Muguntan on 3/4/2018.
 */

public class RestaurantOpen {
    String restaurant_Name;
    String restaurant_Address;
    String restaurant_Id;

    String g;
    List<Double> l;

    public RestaurantOpen() {
    }

    public RestaurantOpen(String restaurant_Name, String restaurant_Address, String g, List<Double> l) {
        this.restaurant_Name = restaurant_Name;
        this.restaurant_Address = restaurant_Address;
        this.g = g;
        this.l = l;
    }

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
