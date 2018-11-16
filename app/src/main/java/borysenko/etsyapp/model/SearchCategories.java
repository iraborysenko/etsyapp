package borysenko.etsyapp.model;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 20:14
 */
public class SearchCategories {
    private String count;
    private Category[] results;

    public SearchCategories(String count, Category[] results) {
        this.count = count;
        this.results = results;
    }

    public String getTotalResults() {
        return count;
    }

    public Category[] getResults() {
        return results;
    }

}
