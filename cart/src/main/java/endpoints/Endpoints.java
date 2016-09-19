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
    @Path("/items")
    @Produces(MediaType.APPLICATION_JSON)
    public Cart list(@Context HttpHeaders hh){
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

    @POST
    @Path("/item/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cart add(@PathParam("id") Long id, @Context HttpHeaders hh){
        Cart toReturn = null;

        Cart cart = CartManager.addToCart(hh, user, id);

        if(cart != null){ toReturn = cart; }

        return toReturn;
    }

    @DELETE
    @Path("/item/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cart remove(@PathParam("id") Long id, @Context HttpHeaders hh){
        Cart toReturn = null;

        Cart cart = CartManager.removeFromCart(hh, user, id);

        if(cart != null){ toReturn = cart; }

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