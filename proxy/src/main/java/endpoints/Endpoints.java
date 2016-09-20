package endpoints;

import helper.Fetcher;
import helper.FrontBuilder;
import model.Item;
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

    String user = "ggg";

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



/*
    @GET
    @Path("/catalog")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> list(){
        List<Order> toReturn = new ArrayList<>();

        if(user != null) {
            List<Order> orders = OrderDao.get(user);
            if(orders != null){ toReturn = orders; }
        }

        return toReturn;
    }

    @GET
    @Path("/cart")
    @Produces(MediaType.APPLICATION_JSON)
    public Cart cart(){
        Cart toReturn = null;

        if(user != null) {
            // Fetcher get cart
            List<Order> orders = OrderDao.get(user);
            if(orders != null){ toReturn = orders; }
        }

        return toReturn;
    }

    @POST
    @Path("/cart/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order add(@PathParam("user") String user, @Context HttpHeaders hh){
        Order toReturn = null;

        if(user != null){
            Order order = OrderManager.createOrder(hh, user);
            if(order != null){ toReturn = order; }
        }

        return toReturn;
    }

    @POST
    @Path("/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order add(@PathParam("user") String user, @Context HttpHeaders hh){
        Order toReturn = null;

        if(user != null){
            Order order = OrderManager.createOrder(hh, user);
            if(order != null){ toReturn = order; }
        }

        return toReturn;
    }*/
}