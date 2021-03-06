package kivaaz.com.ondemandserviceslibrary.Model.Seller;

/**
 * Created by Muguntan on 2/21/2018.
 */

public class SellerData {

    private String company_Name;
    private String company_imgURL;
    private String company_address;
    private String company_About;
    private String ssm_RegisterNo;
    private String gstNo;
    private String ssm_imgURL;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;
    private String dateOfBirth;
    private String ic_No;
    private String ic_imgURL;
    private String icSelfie_imgURL;

    private String token;

    public SellerData() {
    }

    public SellerData(String company_Name, String company_address, String company_About, String company_imgURL, String ssm_RegisterNo, String firstName, String lastName, String mobileNo, String dateOfBirth, String ic_No) {
        this.company_Name = company_Name;
        this.company_address = company_address;
        this.company_About = company_About;
        this.company_imgURL = company_imgURL;
        this.ssm_RegisterNo = ssm_RegisterNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNo = mobileNo;
        this.dateOfBirth = dateOfBirth;
        this.ic_No = ic_No;
    }

    public String getCompany_Name() {
        return company_Name;
    }

    public void setCompany_Name(String company_Name) {
        this.company_Name = company_Name;
    }

    public String getCompany_imgURL() {
        return company_imgURL;
    }

    public void setCompany_imgURL(String company_imgURL) {
        this.company_imgURL = company_imgURL;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getCompany_About() {
        return company_About;
    }

    public void setCompany_About(String company_About) {
        this.company_About = company_About;
    }

    public String getSsm_RegisterNo() {
        return ssm_RegisterNo;
    }

    public void setSsm_RegisterNo(String ssm_RegisterNo) {
        this.ssm_RegisterNo = ssm_RegisterNo;
    }

    public String getSsm_imgURL() {
        return ssm_imgURL;
    }

    public void setSsm_imgURL(String ssm_imgURL) {
        this.ssm_imgURL = ssm_imgURL;
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

    public String getIc_No() {
        return ic_No;
    }

    public void setIc_No(String ic_No) {
        this.ic_No = ic_No;
    }

    public String getIc_imgURL() {
        return ic_imgURL;
    }

    public void setIc_imgURL(String ic_imgURL) {
        this.ic_imgURL = ic_imgURL;
    }

    public String getIcSelfie_imgURL() {
        return icSelfie_imgURL;
    }

    public void setIcSelfie_imgURL(String icSelfie_imgURL) {
        this.icSelfie_imgURL = icSelfie_imgURL;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
