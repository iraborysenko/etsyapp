package borysenko.etsyapp;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import borysenko.etsyapp.dagger.components.AppComponent;
import borysenko.etsyapp.dagger.components.DaggerAppComponent;
import borysenko.etsyapp.dagger.modules.AppModule;
import borysenko.etsyapp.database.DataBaseManager;


/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 20:45
 */
public class App extends Application {

    private AppComponent mAppComponent;

    @Inject
    DataBaseManager dataManager;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        mAppComponent.inject(this);
    }

    public AppComponent getApplicationComponent() {
        return mAppComponent;
    }
}
