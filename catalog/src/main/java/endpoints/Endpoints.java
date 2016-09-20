package endpoints;

import dao.CatalogDoa;
import model.Catalog;
import model.Item;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Developer on 14.06.2016.
 */

@Path("/catalog")
public class Endpoints {

    @GET
    @Path("/items")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> list(){
        List<Item> toReturn = null;

        Catalog catalog = CatalogDoa.list();

        if(catalog != null && catalog.values() != null){
            toReturn = new ArrayList<>(catalog.values());
        }

        return toReturn;
    }

    @GET
    @Path("/item/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Item list(@PathParam("id") Long id){
        Item toReturn = null;

        Item item = CatalogDoa.get(id);

        if(item != null){
            toReturn = item;
        }

        return toReturn;
    }

}