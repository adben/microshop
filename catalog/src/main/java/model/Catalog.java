package model;

import com.googlecode.objectify.annotation.Entity;
import helper.Tools;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by JB on 18.09.2016.
 */
@Entity
public class Catalog extends HashMap<Long, Item> {

    public String toJsonList() {
        return Tools.GSON.toJson(new ArrayList<>(this.values()));
    }

}
