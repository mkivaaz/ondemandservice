package kivaaz.com.ondemandserviceslibrary.Model.Restaurant;

import java.util.List;

/**
 * Created by Muguntan on 3/4/2018.
 */
public class RestaurantInfo {

    String restaurant_Name;
    String restaurant_ImgURl;
    List<String> category;
    String gstNo;
    String business_Hours;
    String delivery_Time;
    String promotion;
    Float ratigns;

    public RestaurantInfo() {
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getRestaurant_Name() {
        return restaurant_Name;
    }

    public void setRestaurant_Name(String restaurant_Name) {
        this.restaurant_Name = restaurant_Name;
    }

    public String getRestaurant_ImgURl() {
        return restaurant_ImgURl;
    }

    public void setRestaurant_ImgURl(String restaurant_ImgURl) {
        this.restaurant_ImgURl = restaurant_ImgURl;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public String getBusiness_Hours() {
        return business_Hours;
    }

    public void setBusiness_Hours(String business_Hours) {
        this.business_Hours = business_Hours;
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

    public Float getRatigns() {
        return ratigns;
    }

    public void setRatigns(Float ratigns) {
        this.ratigns = ratigns;
    }
}