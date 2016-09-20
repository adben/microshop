package model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by Jean-Baptiste Clion on 18.09.2016.
 */
@Entity
public class Order {

    @Id
    private Long id;
    private String user;
    private String date;
    private Integer nbItems;
    private Integer total;

    public Order() {}

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getNbItems() {
        return nbItems;
    }

    public void setNbItems(Integer nbItems) {
        this.nbItems = nbItems;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
