package borysenko.etsyapp.ui;

import android.support.annotation.NonNull;
import android.util.Log;

import javax.inject.Inject;

import borysenko.etsyapp.adapter.MainRecyclerAdapter;
import borysenko.etsyapp.model.Category;
import borysenko.etsyapp.model.Merchandise;
import borysenko.etsyapp.model.SearchCategories;
import borysenko.etsyapp.model.SearchMerchandise;
import borysenko.etsyapp.retrofit.API;
import borysenko.etsyapp.retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 14:48
 */
public class MainPresenter implements MainScreen.Presenter {

    private MainScreen.View mView;

    @Inject
    ApiInterface apiInterface;

    @Inject
    MainPresenter(MainScreen.View mView) {
        this.mView = mView;
    }

    @Override
    public void loadCategories() {
        Call<SearchCategories> call = apiInterface.getTopCategories(API.KEY);
        call.enqueue(new Callback<SearchCategories>() {
            @Override
            public void onResponse(@NonNull Call<SearchCategories>call, @NonNull Response<SearchCategories> response) {
                SearchCategories result = response.body();
                assert result != null;
                Category[] search = result.getResults();
                mView.setSearchCategoryResult(search);
            }

            @Override
            public void onFailure(@NonNull Call<SearchCategories>call, @NonNull Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }

    public void loadSearchResult(String categoryQuery, String productQuery, int offsetpoint) {
        offsetpoint = offsetpoint*5;
        Call<SearchMerchandise> call =
                apiInterface.getMerchandiseList(API.KEY, categoryQuery, productQuery, 5, offsetpoint);
        call.enqueue(new Callback<SearchMerchandise>() {
            @Override
            public void onResponse(@NonNull Call<SearchMerchandise>call, @NonNull Response<SearchMerchandise> response) {
                SearchMerchandise merchandiseList = response.body();
                Merchandise[] merchandises = merchandiseList.getResults();
                mView.resultWithNoPic(merchandises);
            }

            @Override
            public void onFailure(@NonNull Call<SearchMerchandise>call, @NonNull Throwable t) {
                Log.e("error in retrofit", t.toString());
            }
        });
    }


    @Override
    public void getImageForTheMerchandise(final Merchandise merchandise, final MainRecyclerAdapter adapter) {
        Call<String> call = apiInterface.getImage(merchandise.getListingId(), API.KEY);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String>call, @NonNull Response<String> response) {
                String urlImage = response.body();
                merchandise.setImageUrl(urlImage);
                adapter.add(merchandise);
            }

            @Override
            public void onFailure(@NonNull Call<String>call, @NonNull Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }
}
