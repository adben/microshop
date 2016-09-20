package model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jean-Baptiste Clion on 18.09.2016.
 */
@Entity
public class Cart {

    @Id
    private Long id;
    private String user;
    private List<Item> items;
    private Integer total = 0;

    public Cart() {}

    public Cart(String user) {
        this.user = user;
        this.items = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getTotal() {
        return total;
    }

    public void addItem(Item item){

        if(item != null){
            boolean added = this.items.add(item);
            if(added) { this.total += item.getPrice(); }
        }

    }

    public void removeItem(Item item){

        if(item != null) {
            boolean removed = this.items.remove(item);
            if(removed) { this.total -= item.getPrice(); }
        }

    }

    public void clear(){

        this.items.clear();
        this.total = 0;

    }
}
