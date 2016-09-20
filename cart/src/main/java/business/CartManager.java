package business;

import dao.CartDao;
import helper.Fetcher;
import model.Cart;
import model.Item;

import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;

/**
 * Created by JB on 18.09.2016.
 */
public class CartManager {

    public static Cart addToCart(HttpHeaders hh, String user, Long itemId){
        Cart toReturn = null;

        if(itemId != null && user != null){

            Cart cart = CartDao.get(user);

            if(cart == null){ cart = new Cart(user); }

            Item validItem = retrieveItem(hh, itemId);

            if(validItem != null){

                cart.addItem(validItem);

                Cart savedCart = CartDao.save(cart);

                if(savedCart != null){
                    toReturn = savedCart;
                }
            }

        }

        return toReturn;
    }

    public static Cart removeFromCart(HttpHeaders hh, String user, Long itemId){
        Cart toReturn = null;

        if(itemId != null && user != null){

            Cart cart = CartDao.get(user);

            if(cart != null) {

                Item validItem = retrieveItem(hh, itemId);

                if(validItem != null){

                    cart.removeItem(validItem);
                    Cart savedCart = CartDao.save(cart);

                    if (savedCart != null) {
                        toReturn = savedCart;
                    }
                }
            }
        }

        return toReturn;
    }

    public static Cart clearCart(String user){
        Cart toReturn = null;

        if(user != null){

            Cart cart = CartDao.get(user);

            if(cart != null) {

                cart.clear();
                Cart savedCart = CartDao.save(cart);

                if (savedCart != null) {
                    toReturn = savedCart;
                }
            }
        }

        return toReturn;
    }

    private static Item retrieveItem(HttpHeaders hh, Long itemId){
        Item toReturn = null;

        if(itemId != null){

            try {
                Item item = Fetcher.getItem(hh, itemId);
                if(item != null){ toReturn = item; }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return toReturn;
    }

}
