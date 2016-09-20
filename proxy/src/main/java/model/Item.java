package model;

/**
 * Created by JB on 18.09.2016.
 */
public class Item {

    private Long id;
    private String title;
    private String description;
    private String img;
    private Integer price;
    private Integer stock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Item)) {
            return false;
        }
        Item other = (Item) o;
        return id.equals(other.id) && id.equals(other.id);
    }

    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return
                "Id         : " + id + System.lineSeparator() +
                "Title      : " + title + System.lineSeparator() +
                "Description: " + description + System.lineSeparator() +
                "Image      : " + img + System.lineSeparator() +
                "Price      : " + price + System.lineSeparator() +
                "Stock      : " + stock ;
    }
}
