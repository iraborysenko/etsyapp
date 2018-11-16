package borysenko.etsyapp.model;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 17/11/18
 * Time: 00:15
 */
public class Image {

    private String listingId;
    private String imageUrl;

    public Image(String listingId, String imageUrl) {

        this.listingId = listingId;
        this.imageUrl = imageUrl;
    }

    public Image () {}

    public String getListingId() {
        return listingId;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
