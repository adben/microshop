package business;

import dao.OrderDao;
import helper.Fetcher;
import model.Cart;
import model.Order;

import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JB on 18.09.2016.
 */
public class OrderManager {

    public static Order createOrder(HttpHeaders hh, String user){
        Order toReturn = null;
        Cart cart = null;

        if(user != null){

            try {
                cart = Fetcher.getCart(hh, user);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(cart != null){
                Order order = toOrder(cart);

                if(order != null){

                    Order savedOrder = OrderDao.save(order);

                    if(savedOrder != null){
                        toReturn = order;
                    }
                }

                try {
                    Fetcher.clearCart(hh, user);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

        return toReturn;
    }

    public static List<Order> list(String user){
        List<Order> toReturn = new ArrayList<>();

        if(user != null){
            List<Order> orders = OrderDao.get(user);
            if(orders != null){
                toReturn.addAll(orders);
            }
        }

        return toReturn;

    }

    private static Order toOrder(Cart cart){
        Order toReturn = null;

        if(cart != null){
            toReturn = new Order();
            toReturn.setUser(cart.getUser());
            toReturn.setDate(new Date().toString());
            toReturn.setNbItems(cart.getItems().size());
            toReturn.setTotal(cart.getTotal());
        }

        return toReturn;
    }
}
