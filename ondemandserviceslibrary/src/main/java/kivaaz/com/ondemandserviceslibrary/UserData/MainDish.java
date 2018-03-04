package kivaaz.com.ondemandserviceslibrary.UserData;

import java.util.List;
import java.util.Map;

/**
 * Created by Muguntan on 3/4/2018.
 */

public class MainDish {
    String food_Name;
    List<String> food_Category;
    String price;
    List<Map<String,String>> addOns;
    String food_ImgURL;
    String available;

    public MainDish() {
    }

    public String getFood_Name() {
        return food_Name;
    }

    public void setFood_Name(String food_Name) {
        this.food_Name = food_Name;
    }

    public List<String> getFood_Category() {
        return food_Category;
    }

    public void setFood_Category(List<String> food_Category) {
        this.food_Category = food_Category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Map<String, String>> getAddOns() {
        return addOns;
    }

    public void setAddOns(List<Map<String, String>> addOns) {
        this.addOns = addOns;
    }

    public String getFood_ImgURL() {
        return food_ImgURL;
    }

    public void setFood_ImgURL(String food_ImgURL) {
        this.food_ImgURL = food_ImgURL;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
