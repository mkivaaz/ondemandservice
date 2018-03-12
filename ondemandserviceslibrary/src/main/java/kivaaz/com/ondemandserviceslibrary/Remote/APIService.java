package kivaaz.com.ondemandserviceslibrary.Remote;


import kivaaz.com.ondemandserviceslibrary.Model.MyResponse;
import kivaaz.com.ondemandserviceslibrary.Model.Sender;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Muguntan on 3/11/2018.
 */

public interface APIService {
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAol3gc9A:APA91bEZfpBMuiYmQDprRCnilnY1IS8CCXWrNRY1Lq3qL5LaZjIDOzGe626CYqbYVBVl9c3sJgav92mSOejbI3kTwOw-O5cCiFZfOdJp0XN4mP_Y3dUj8Z9Eyo_xBTnpGlp_nViRmCJX"
    })
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
