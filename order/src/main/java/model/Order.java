package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jean-Baptiste Clion on 18.09.2016.
 */
public class Order {

    private Long id;
    private Cart cart;
    private String status;

    public Order() {}

    public Order(Cart cart) {
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
