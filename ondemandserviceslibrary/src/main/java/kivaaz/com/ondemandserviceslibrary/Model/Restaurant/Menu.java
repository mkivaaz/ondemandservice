package kivaaz.com.ondemandserviceslibrary.Model.Restaurant;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.List;
import java.util.Map;

/**
 * Created by Muguntan on 3/4/2018.
 */
@IgnoreExtraProperties
public class Menu {

    private String name;
    private String by;
    private List<String> categoriess;
    private double price;
    private List<Map<String,String>> addOns;
    private String imgURL;
    private boolean soldout;
    private MenuType type;

    public Menu() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCategoriess() {
        return categoriess;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public void setCategoriess(List<String> categoriess) {
        this.categoriess = categoriess;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Map<String, String>> getAddOns() {
        return addOns;
    }

    public void setAddOns(List<Map<String, String>> addOns) {
        this.addOns = addOns;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public boolean isSoldout() {
        return soldout;
    }

    public void setSoldout(boolean soldout) {
        this.soldout = soldout;
    }

    @Exclude
    public MenuType getTypeVal() {
        return type;
    }

    @Exclude
    public void setTypeVal(MenuType type) {
        this.type = type;
    }

    public String getType() {

        if(type == null) {
            return null;
        }

        return type.name();
    }

    public void setType(String type) {

        if(type == null) {
            this.type = null;
        } else {
            this.type = MenuType.valueOf(type);
        }
    }
}
