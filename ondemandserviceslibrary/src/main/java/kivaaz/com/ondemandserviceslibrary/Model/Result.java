package kivaaz.com.ondemandserviceslibrary.Model;

/**
 * Created by Muguntan on 3/11/2018.
 */

public class Result {
    public String message_id;

    public Result() {
    }

    public Result(String message_id) {
        this.message_id = message_id;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }
}
