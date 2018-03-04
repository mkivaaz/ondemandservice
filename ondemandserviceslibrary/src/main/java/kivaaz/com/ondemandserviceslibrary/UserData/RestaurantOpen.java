package kivaaz.com.ondemandserviceslibrary.UserData;

import java.util.List;

/**
 * Created by Muguntan on 3/4/2018.
 */

public class RestaurantOpen {
    String restaurant_Name;
    String restaurant_Address;
    String restaurant_Id;
    String seller_email;
    List<String> category;
    String delivery_Time;
    String promotion;
    Float ratings;
    String restaurant_ImgURl;
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

    public String getSeller_email() {
        return seller_email;
    }

    public void setSeller_email(String seller_email) {
        this.seller_email = seller_email;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public String getDelivery_Time() {
        return delivery_Time;
    }

    public void setDelivery_Time(String delivery_Time) {
        this.delivery_Time = delivery_Time;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public Float getRatings() {
        return ratings;
    }

    public void setRatings(Float ratings) {
        this.ratings = ratings;
    }

    public String getRestaurant_ImgURl() {
        return restaurant_ImgURl;
    }

    public void setRestaurant_ImgURl(String restaurant_ImgURl) {
        this.restaurant_ImgURl = restaurant_ImgURl;
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
