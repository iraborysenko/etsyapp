package borysenko.etsyapp.model;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 18:54
 */
public class Merchandise {
    private String listingId;
    private String categoryId;
    private String title;
    private String description;
    private String price;
    private String currencyCode;

    public Merchandise(String listingId, String categoryId, String title, String description,
                       String price, String currencyCode) {

        this.listingId = listingId;
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.currencyCode = currencyCode;
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
}
