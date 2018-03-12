package kivaaz.com.ondemandserviceslibrary.Model.Order;

import kivaaz.com.ondemandserviceslibrary.Model.Restaurant.MainDish;

/**
 * Created by Muguntan on 3/11/2018.
 */

public class OrderData {
    MainDish dish;
    int quantity;
    String buyerInstruction;
    String seller_email;
    String rider_email;
    String isRiderConfirmed;
    String orderStatus;
    double tot_Price;
}
