package helper;

import model.Item;

import java.util.List;

/**
 * Created by Apeser on 20.09.2016.
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
                        "<p class='price'><a class='btn btn-primary' href='#'>" + item.getPrice() + "$</a></p>" +
                        "</p>" +
                        "</div>" +
                        "</div>" +
                        "</div>";
            }

        }

        return toReturn;
    }
}
