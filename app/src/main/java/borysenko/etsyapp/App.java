package borysenko.etsyapp;

import android.app.Application;

import borysenko.etsyapp.dagger.AppComponent;
import borysenko.etsyapp.dagger.DaggerAppComponent;


/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 20:45
 */
public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .build();
    }

    public AppComponent getApplicationComponent() {
        return mAppComponent;
    }
}
