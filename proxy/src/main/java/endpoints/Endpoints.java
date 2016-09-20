package endpoints;

import helper.Fetcher;
import helper.FrontBuilder;
import helper.UserManager;
import model.Cart;
import model.Item;
import model.Order;
import org.apache.commons.io.IOUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Developer on 14.06.2016.
 */

@Path("/")
public class Endpoints {

    String user = UserManager.getCurrentUser();

    @GET
    @Path("/home")
    @Produces(MediaType.TEXT_HTML)
    public String page(@Context HttpHeaders hh){
        String toReturn = "";
        List<Item> catalog = new ArrayList<>();
        String catalogHtml;

        try {
            List<Item> items = Fetcher.getCatalog(hh);
            if(items != null){ catalog.addAll(items); }
        } catch (IOException e) {
            e.printStackTrace();
        }

        catalogHtml = FrontBuilder.buildCatalog(catalog);

        try {
            toReturn = IOUtils.toString(Endpoints.class.getResourceAsStream("/index.html"), Charset.forName("UTF-8")).replace("###CATALOG###", catalogHtml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toReturn;

    }

    @GET
    @Path("/cart")
    @Produces(MediaType.TEXT_HTML)
    public String getCart(@Context HttpHeaders hh){
        String toReturn = "";

        if(user != null){
            try {
                Cart cart = Fetcher.getCart(hh, user);
                toReturn = FrontBuilder.buildCart(cart);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return toReturn;
    }

    @GET
    @Path("/orders")
    @Produces(MediaType.TEXT_HTML)
    public String getOrders(@Context HttpHeaders hh) {
        String toReturn = "";

        if (user != null) {
            try {
                List<Order> orders = Fetcher.getOrders(hh, user);
                if (orders != null) {
                    toReturn = FrontBuilder.buildOrders(orders);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return toReturn;
    }

    @GET
    @Path("/cart/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void add(@PathParam("itemId") Long itemId, @Context HttpHeaders hh){

        if(user != null && itemId != null){
            try {
                Fetcher.addToCart(hh, user, itemId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GET
    @Path("/cart/delete/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("itemId") Long itemId, @Context HttpHeaders hh){

        if(user != null && itemId != null){
            try {
                Fetcher.removeFromCart(hh, user, itemId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GET
    @Path("/checkout")
    @Produces(MediaType.TEXT_HTML)
    public String checkout(@Context HttpHeaders hh){
        String toReturn = "";

        if(user != null){
            try {
                List<Order> orders = Fetcher.checkout(hh, user);
                if(orders != null){
                    toReturn = FrontBuilder.buildOrders(orders);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return toReturn;
    }
}