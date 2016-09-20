package endpoints;

import business.CartManager;
import dao.CartDao;
import model.Cart;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 * Created by Developer on 14.06.2016.
 */

@Path("/cart")
public class Endpoints {

    String user = "ggg";

    @GET
    @Path("/items/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cart list(@Context HttpHeaders hh, @PathParam("user") String user){
        Cart toReturn = null;

        Cart cart = CartDao.get(user);

        if(cart != null){ toReturn = cart; }

        return toReturn;
    }

    @GET
    @Path("/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cart add(@PathParam("user") String user){
        Cart toReturn = null;

        if(user != null){
            Cart cart = CartDao.get(user);
            if(cart != null){ toReturn = cart; }
        }

        return toReturn;
    }

    @GET
    @Path("/item/{user}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cart add(@Context HttpHeaders hh, @PathParam("id") Long id, @PathParam("user") String user){
        Cart toReturn = null;

        Cart cart = CartManager.addToCart(hh, user, id);

        if(cart != null){ toReturn = cart; }

        System.out.println("Added to cart" + cart);

        return toReturn;
    }

    @GET
    @Path("/item/delete/{user}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cart delete(@Context HttpHeaders hh, @PathParam("id") Long id, @PathParam("user") String user){
        Cart toReturn = null;

        System.out.println("Called removed from cart");

        Cart cart = CartManager.removeFromCart(hh, user, id);

        if(cart != null){ toReturn = cart; }

        System.out.println("Cart: " + cart);


        return toReturn;
    }

    @DELETE
    @Path("/clear")
    @Produces(MediaType.APPLICATION_JSON)
    public Cart remove(){
        Cart toReturn = null;

        Cart cart = CartManager.clearCart(user);

        if(cart != null){ toReturn = cart; }

        return toReturn;
    }

    @DELETE
    @Path("/clear/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cart remove(@PathParam("user") String user){
        Cart toReturn = null;

        if(user != null){
            Cart cart = CartManager.clearCart(user);
            if(cart != null){
                toReturn = cart;
            }
        }

        return toReturn;
    }

}