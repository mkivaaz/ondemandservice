package kivaaz.com.ondemandserviceslibrary.Utils;


import kivaaz.com.ondemandserviceslibrary.Remote.APIService;
import kivaaz.com.ondemandserviceslibrary.Remote.RetrofitClient;

/**
 * Created by Muguntan on 3/11/2018.
 */

public class Common {
    public static String INSTANCE_ID = "";
    public static String BASE_URL = "https://fcm.googleapis.com/";

    public static APIService getFCMClient(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
