package dao;

import model.Cart;

import java.util.HashMap;

/**
 * Created by Apeser on 18.09.2016.
 */
public class CartDao {

    private static HashMap<String, Cart> CARTS = new HashMap<>();

    public static Cart get(String user){
        Cart toReturn = null;

        if(CARTS != null && user!= null){

            Cart cart = CARTS.get(user);

            if(cart == null){
                cart = new Cart(user);
            }

            toReturn = cart;
        }

        return toReturn;
    }

    public static Cart save(Cart cart){
        Cart toReturn = null;

        if(CARTS != null && cart!= null){
            CARTS.put(cart.getUser(), cart);
            toReturn = cart;
        }

        return toReturn;
    }

}
