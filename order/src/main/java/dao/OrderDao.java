package dao;

import model.Cart;
import model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Apeser on 18.09.2016.
 */
public class OrderDao {

    private static HashMap<String, List<Order>> ORDERS = new HashMap<>();

    public static List<Order> get(String user){
        List<Order> toReturn = new ArrayList<>();

        if(ORDERS != null && user!= null && ORDERS.containsKey(user)){
            List<Order> orders = ORDERS.get(user);
            if(orders != null){ toReturn.addAll(orders); }
        }

        return toReturn;
    }

    public static Order save(Order order){
        Order toReturn = null;

        if(ORDERS != null && order!= null){

            if(order.getCart() != null && order.getCart().getUser() != null){

                if(ORDERS.containsKey(order.getCart().getUser())){
                    ORDERS.get(order.getCart().getUser()).add(order);
                }else{
                    List<Order> orders = new ArrayList<>();
                    orders.add(order);
                    ORDERS.put(order.getCart().getUser(), orders);
                }

                toReturn = order;
            }

        }

        return toReturn;
    }

}
