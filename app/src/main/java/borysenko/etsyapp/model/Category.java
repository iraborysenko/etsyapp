package borysenko.etsyapp.model;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 18:39
 */
public class Category {

    private String categoryId;
    private String categoryName;
    private String shortName;

    public Category(String categoryId, String categoryName, String shortName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.shortName = shortName;
    }

    public Category () {}

    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getShortName() {
        return shortName;
    }

}