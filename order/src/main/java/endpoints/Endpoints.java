package endpoints;

import business.OrderManager;
import dao.OrderDao;
import model.Cart;
import model.Order;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Developer on 14.06.2016.
 */

@Path("/orders")
public class Endpoints {

    @GET
    @Path("/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> list(@PathParam("user") String user){
        List<Order> toReturn = new ArrayList<>();

        if(user != null) {
            List<Order> orders = OrderDao.get(user);
            if(orders != null){ toReturn = orders; }
        }

        return toReturn;
    }

    @GET
    @Path("/checkout/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> add(@PathParam("user") String user, @Context HttpHeaders hh){
        List<Order> toReturn = null;

        if(user != null){
            Order order = OrderManager.createOrder(hh, user);
            if(order != null){
                toReturn = OrderManager.list(user);
            }
        }

        return toReturn;
    }
}