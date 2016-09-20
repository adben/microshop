package dao;

import com.google.appengine.repackaged.com.google.gson.reflect.TypeToken;
import helper.Tools;
import model.Catalog;
import model.Item;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JB on 18.09.2016.
 */
public class CatalogDoa {

    private static final String DATA_FILE = "Catalog.json";
    private static final Catalog CATALOG = loadCatalog(); ;

    public static Catalog list(){ return CATALOG; }

    public static Item get(Long id){
        Item toReturn = null;

        if(id != null){
            Item item = CATALOG.get(id);

            if(item != null){
                toReturn = item;
            }
        }

        return toReturn;
    }


    private static Catalog loadCatalog(){
        Catalog catalog = new Catalog();

        String json = loadJson();

        if(json != null && !json.isEmpty()){

            ArrayList<Item> items = Tools.GSON.fromJson(json, new TypeToken<List<Item>>() {}.getType());

            if(items != null){
                for (Item item : items) { catalog.put(item.getId(), item); }
            }

        }

        return catalog;
    }

    private static String loadJson() {
        String toReturn = "";

        try {
            String json = IOUtils.toString(CatalogDoa.class.getResourceAsStream("/" + DATA_FILE), Charset.forName("UTF-8"));

            if(json != null && !json.isEmpty()){
                toReturn = json;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return toReturn;
    }

}
