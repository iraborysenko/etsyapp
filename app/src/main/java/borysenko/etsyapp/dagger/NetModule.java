package borysenko.etsyapp.dagger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import borysenko.etsyapp.deserializers.CategoryDeserializer;
import borysenko.etsyapp.deserializers.ImageDeserializer;
import borysenko.etsyapp.deserializers.MerchandiseDeserializer;
import borysenko.etsyapp.deserializers.SearchCategoriesDeserializer;
import borysenko.etsyapp.deserializers.SearchMerchandiseDeserializer;
import borysenko.etsyapp.model.Category;
import borysenko.etsyapp.model.Merchandise;
import borysenko.etsyapp.model.SearchCategories;
import borysenko.etsyapp.model.SearchMerchandise;
import borysenko.etsyapp.retrofit.ApiInterface;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static borysenko.etsyapp.retrofit.API.ETSY_BASE;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 21:02
 */
@Module
public class NetModule {
    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(SearchCategories.class, new SearchCategoriesDeserializer())
                .registerTypeAdapter(Category.class, new CategoryDeserializer())
                .registerTypeAdapter(SearchMerchandise.class, new SearchMerchandiseDeserializer())
                .registerTypeAdapter(Merchandise.class, new MerchandiseDeserializer())
                .registerTypeAdapter(String.class, new ImageDeserializer())
                .create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(ETSY_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    ApiInterface provideApiInterface(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }
}
