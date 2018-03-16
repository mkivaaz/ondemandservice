package kivaaz.com.ondemandserviceslibrary.Model.Order;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import kivaaz.com.ondemandserviceslibrary.Model.Restaurant.MainDish;

/**
 * Created by Muguntan on 3/11/2018.
 */
@IgnoreExtraProperties
public class OrderModel {

    private String id;
    private ArrayList<MainDish> orderedFoods;
    private OrderStatus orderStatus;
    private String riderId;
    private String buyerId;
    private String deliveryAddress;
    private double deliveryLat;
    private double deliveryLon;
    private long deliveryTimestamp;
    private PaymentType paymentType;
    private String receiptImgURL;
    private double totalPrice;
    private long timestamp;

    public OrderModel() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<MainDish> getOrderedFoods() {
        return orderedFoods;
    }

    public void setOrderedFoods(ArrayList<MainDish> orderedFoods) {
        this.orderedFoods = orderedFoods;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRiderId() {
        return riderId;
    }

    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public double getDeliveryLat() {
        return deliveryLat;
    }

    public void setDeliveryLat(double deliveryLat) {
        this.deliveryLat = deliveryLat;
    }

    public double getDeliveryLon() {
        return deliveryLon;
    }

    public void setDeliveryLon(double deliveryLon) {
        this.deliveryLon = deliveryLon;
    }

    public long getDeliveryTimestamp() {
        return deliveryTimestamp;
    }

    public void setDeliveryTimestamp(long deliveryTimestamp) {
        this.deliveryTimestamp = deliveryTimestamp;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getReceiptImgURL() {
        return receiptImgURL;
    }

    public void setReceiptImgURL(String receiptImgURL) {
        this.receiptImgURL = receiptImgURL;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
