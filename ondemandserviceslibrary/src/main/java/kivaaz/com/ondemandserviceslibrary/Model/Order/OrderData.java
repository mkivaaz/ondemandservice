package kivaaz.com.ondemandserviceslibrary.Model.Order;

import kivaaz.com.ondemandserviceslibrary.Model.Restaurant.Menu;

/**
 * Created by Muguntan on 3/11/2018.
 */

public class OrderData {
    Menu dish;
    int quantity;
    String buyerInstruction;
    String seller_email;
    String rider_email;
    String isRiderConfirmed;
    String orderStatus;
    double tot_Price;
}
