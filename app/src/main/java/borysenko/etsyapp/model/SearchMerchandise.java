package borysenko.etsyapp.model;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 19:04
 */
public class SearchMerchandise {
    private String count;
    private Merchandise[] results;

    public SearchMerchandise(String count, Merchandise[] results) {
        this.count = count;
        this.results = results;
    }

    public String getTotalResults() {
        return count;
    }

    public Merchandise[] getResults() {
        return results;
    }
}
