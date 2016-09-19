package model;

import helper.Tools;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Apeser on 18.09.2016.
 */
public class Catalog extends HashMap<Long, Item> {

    public String toJsonList() {
        return Tools.GSON.toJson(new ArrayList<>(this.values()));
    }

}
