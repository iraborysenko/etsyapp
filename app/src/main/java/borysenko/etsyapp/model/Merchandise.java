package borysenko.etsyapp.model;


import java.io.Serializable;
/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 18:54
 */
public class Merchandise implements Serializable {
    private int id;
    private String listingId;
    private String categoryId;
    private String title;
    private String description;
    private String price;
    private String currencyCode;
    private String imageUrl;

    public Merchandise(String listingId, String categoryId, String title, String description,
                       String price, String currencyCode) {

        this.listingId = listingId;
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.currencyCode = currencyCode;
        this.imageUrl = "none";
    }

    public Merchandise () {}

    public int getId() {
        return id;
    }

    public String getListingId() {
        return listingId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}