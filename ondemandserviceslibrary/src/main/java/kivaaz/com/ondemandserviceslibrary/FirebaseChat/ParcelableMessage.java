package kivaaz.com.ondemandserviceslibrary.FirebaseChat;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Muguntan on 1/30/2018.
 */

public class ParcelableMessage implements Parcelable {
    String message;
    String messageSender;
    String messageReceiver;
    String timeStamp;

    public ParcelableMessage(String message, String messageSender, String messageReceiver, long timeStamp) {
        this.message = message;
        this.messageSender = messageSender;
        this.messageReceiver = messageReceiver;
        this.timeStamp = String.valueOf(timeStamp);
    }
    public ParcelableMessage(Parcel in) {
        String[] data = new String[4];

        in.readStringArray(data);
        this.message = data[0];
        this.messageSender = data[1];
        this.messageReceiver = data[2];
        this.timeStamp = data[3];
    }

    public static final Creator<ParcelableMessage> CREATOR = new Creator<ParcelableMessage>() {
        @Override
        public ParcelableMessage createFromParcel(Parcel in) {
            return new ParcelableMessage(in);
        }

        @Override
        public ParcelableMessage[] newArray(int size) {
            return new ParcelableMessage[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
