package kivaaz.com.ondemandserviceslibrary.Model.Rider;

import java.util.List;

/**
 * Created by Muguntan on 3/7/2018.
 */

public class AvailableRider {
    String rider_firstname;
    String rider_lastname;
    String rider_email;
    String g;
    List<Double> l;

    public AvailableRider() {
    }

    public AvailableRider(String rider_firstname, String rider_lastname, String rider_email, String g, List<Double> l) {
        this.rider_firstname = rider_firstname;
        this.rider_lastname = rider_lastname;
        this.rider_email = rider_email;
        this.g = g;
        this.l = l;
    }

    public String getRider_firstname() {
        return rider_firstname;
    }

    public void setRider_firstname(String rider_firstname) {
        this.rider_firstname = rider_firstname;
    }

    public String getRider_lastname() {
        return rider_lastname;
    }

    public void setRider_lastname(String rider_lastname) {
        this.rider_lastname = rider_lastname;
    }

    public String getRider_email() {
        return rider_email;
    }

    public void setRider_email(String rider_email) {
        this.rider_email = rider_email;
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
