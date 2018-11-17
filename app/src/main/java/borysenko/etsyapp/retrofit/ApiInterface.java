package borysenko.etsyapp.retrofit;

import borysenko.etsyapp.model.SearchCategories;
import borysenko.etsyapp.model.SearchMerchandise;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 17:56
 */
public interface ApiInterface {
    @GET("taxonomy/categories")
    Call<SearchCategories> getTopCategories(@Query("api_key") String apiKey);

    @GET("listings/active")
    Call<SearchMerchandise> getMerchandiseList(@Query("api_key") String apiKey,
                                               @Query("category") String categoryQuery,
                                               @Query("keywords") String productQuery,
                                               @Query("limit") int limit,
                                               @Query("offset") int offset);

    @GET("listings/{listing_id}/images")
    Call<String> getImage(@Path("listing_id") String listingId,
                         @Query("api_key") String key);
}
