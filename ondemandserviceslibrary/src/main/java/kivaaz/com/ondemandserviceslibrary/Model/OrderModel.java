package kivaaz.com.ondemandserviceslibrary.Model;

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
}
