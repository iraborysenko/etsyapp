package borysenko.etsyapp.model;


import java.io.Serializable;
/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 18:54
 */
public class Merchandise implements Serializable {
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

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}