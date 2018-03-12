package kivaaz.com.ondemandserviceslibrary.Model.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by Muguntan on 3/11/2018.
 */

public class OrderModel {
    List<Map<String,String>> food;
    String orderStatus;
    String rider;
    String buyer;
    String delivery_date;
    String delivery_time;
    String payment_type;
    String receipt_img_URL;
    Double totalPrice;

    public OrderModel() {
    }

    public OrderModel(List<Map<String, String>> food, String orderStatus, String rider, Double totalPrice) {
        this.food = food;
        this.orderStatus = orderStatus;
        this.rider = rider;
        this.totalPrice = totalPrice;
    }

    public List<Map<String, String>> getFood() {
        return food;
    }

    public void setFood(List<Map<String, String>> food) {
        this.food = food;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRider() {
        return rider;
    }

    public void setRider(String rider) {
        this.rider = rider;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getReceipt_img_URL() {
        return receipt_img_URL;
    }

    public void setReceipt_img_URL(String receipt_img_URL) {
        this.receipt_img_URL = receipt_img_URL;
    }
}
