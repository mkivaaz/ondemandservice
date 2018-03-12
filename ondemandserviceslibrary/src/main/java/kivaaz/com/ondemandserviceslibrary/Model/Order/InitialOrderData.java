package kivaaz.com.ondemandserviceslibrary.Model.Order;

import kivaaz.com.ondemandserviceslibrary.Model.Restaurant.MainDish;

/**
 * Created by Muguntan on 3/9/2018.
 */

public class InitialOrderData {
    MainDish dish;
    int quantity;
    String buyerInstruction;
    String seller_email;
    String rider_email;
    String isRiderConfirmed;
    String orderStatus;
    double food_price;
    double tot_Price;

    public InitialOrderData() {
    }

    public InitialOrderData(int quantity, String buyerInstruction) {
        this.quantity = quantity;
        this.buyerInstruction = buyerInstruction;
    }

    public InitialOrderData(MainDish dish, int quantity, String buyerInstruction) {
        this.dish = dish;
        this.quantity = quantity;
        this.buyerInstruction = buyerInstruction;
    }

    public MainDish getDish() {
        return dish;
    }

    public void setDish(MainDish dish) {
        this.dish = dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBuyerInstruction() {
        return buyerInstruction;
    }

    public void setBuyerInstruction(String buyerInstruction) {
        this.buyerInstruction = buyerInstruction;
    }

    public double getTot_Price() {
        return tot_Price;
    }

    public void setTot_Price(double tot_Price) {
        this.tot_Price = tot_Price;
    }

    public String getSeller_email() {
        return seller_email;
    }

    public void setSeller_email(String seller_email) {
        this.seller_email = seller_email;
    }

    public String getRider_email() {
        return rider_email;
    }

    public void setRider_email(String rider_email) {
        this.rider_email = rider_email;
    }

    public String getIsRiderConfirmed() {
        return isRiderConfirmed;
    }

    public void setIsRiderConfirmed(String isRiderConfirmed) {
        this.isRiderConfirmed = isRiderConfirmed;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getFood_price() {
        return food_price;
    }

    public void setFood_price(double food_price) {
        this.food_price = food_price;
    }
}
