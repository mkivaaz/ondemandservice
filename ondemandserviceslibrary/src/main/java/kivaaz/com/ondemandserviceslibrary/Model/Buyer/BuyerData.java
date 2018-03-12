package kivaaz.com.ondemandserviceslibrary.Model.Buyer;

import java.util.List;
import java.util.Map;

/**
 * Created by Muguntan on 3/3/2018.
 */

public class BuyerData {
    String profileImg_URL;
    String firstName;
    String lastName;
    String email;
    String mobileNo;
    String dateOfBirth;
    String preferred_food;
    List<Map<String,String>> addressList;

    public BuyerData() {
    }

    public BuyerData(String profileImg_URL, String firstName, String lastName, String email, String mobileNo, String dateOfBirth, String preferred_food, List<Map<String, String>> addressList) {
        this.profileImg_URL = profileImg_URL;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.dateOfBirth = dateOfBirth;
        this.preferred_food = preferred_food;
        this.addressList = addressList;
    }

    public String getProfileImg_URL() {
        return profileImg_URL;
    }

    public void setProfileImg_URL(String profileImg_URL) {
        this.profileImg_URL = profileImg_URL;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPreferred_food() {
        return preferred_food;
    }

    public void setPreferred_food(String preferred_food) {
        this.preferred_food = preferred_food;
    }

    public List<Map<String, String>> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Map<String, String>> addressList) {
        this.addressList = addressList;
    }
}
