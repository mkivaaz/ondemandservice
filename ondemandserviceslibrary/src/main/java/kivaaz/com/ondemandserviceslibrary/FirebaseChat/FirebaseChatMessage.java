package kivaaz.com.ondemandserviceslibrary.FirebaseChat;

/**
 * Created by Muguntan on 1/29/2018.
 */

public class FirebaseChatMessage {
    String message;
    String messageSender;
    long timeStamp;
    String messageID;
    String isNotified;

    public FirebaseChatMessage() {
    }

    public FirebaseChatMessage(String message, String messageSender, long timeStamp, String isNotified) {
        this.message = message;
        this.messageSender = messageSender;
        this.timeStamp = timeStamp;
        this.isNotified = isNotified;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(String messageSender) {
        this.messageSender = messageSender;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getIsNotified() {
        return isNotified;
    }

    public void setIsNotified(String isNotified) {
        this.isNotified = isNotified;
    }
}
