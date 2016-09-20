package helper;

import model.Cart;
import model.Item;
import model.Order;

import java.util.List;

/**
 * Created by JB on 20.09.2016.
 */
public class FrontBuilder {

    public static String buildCatalog(List<Item> items){
        String toReturn = "";

        if(items != null){

            for (Item item : items) {

                toReturn = toReturn +
                        "<div class='col-xs-6 col-sm-4 col-lg-3'>" +
                        "<div class='thumbnail'>" +
                        "<img src='" + item.getImg() + "'>" +
                        "<div class='caption' style='text-align:center'>" +
                        "<h3>" + item.getTitle() + "</h2>" +
                        "<p class='flex-text text-muted'>" + item.getDescription() + "</p>" +
                        "<p>" +
                        "<p class='price'><a class='btn btn-primary' href='#' onclick='addToCart(" + item.getId() + ", \"" + item.getTitle() + " added to cart\");'>" + item.getPrice() + "$</a></p>" +
                        "</p>" +
                        "</div>" +
                        "</div>" +
                        "</div>";
            }

        }

        return toReturn;
    }

    public static String buildCart(Cart cart){
        String toReturn = "";

        if(cart != null) {
            for (Item item : cart.getItems()) {
                toReturn = toReturn +
                        "<tr style='border-bottom:1px solid grey'>" +
                        "<td style='width:5%;'><img src='" + item.getImg() + "'></td>" +
                        "<td style='width:45%;text-align:center'><h2>" + item.getTitle() + "</h2></td>" +
                        "<td style='width:45%;text-align:center'><h2>" + item.getPrice() + "$</h2></td>" +
                        "<td style='width:5%%;'><a class='btn btn-primary' href='#' onclick='removeFromCart(" + item.getId() + ")'>Remove</a></td>" +
                        "</tr>";
            }
        }

        toReturn = toReturn +
            "<tr style='border-bottom:1px solid grey;background:#D6FFD6'>" +
            "<td style='width:5%;text-align:center'><h2>Total</h2></td>" +
            "<td style='width:45%;text-align:center'></td>" +
            "<td style='width:45%;text-align:center'><h2>" + cart.getTotal() + "$</h2></td>" +
            "<td style='width:5%%;'></td>" +
            "</tr>";

        return toReturn;
    }

    public static String buildOrders(List<Order> orders){
        String toReturn = "";

        if(orders != null) {
            for (Order order : orders) {
                toReturn = toReturn +
                        "<tr style='border-bottom:1px solid grey'>" +
                        "<td style='width:40%;'><h5>" + order.getDate() + "</h5></td>" +
                        "<td style='width:20%;'><h5>" + order.getNbItems() + " Items</h5></td>" +
                        "<td style='width:20%;'><h5>" + order.getTotal() + "$</h5></td>" +
                        "</tr>";
            }
        }

        return toReturn;
    }
}
