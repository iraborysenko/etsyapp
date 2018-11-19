package borysenko.etsyapp.dagger.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import borysenko.etsyapp.App;
import borysenko.etsyapp.dagger.ApplicationContext;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 21:01
 */
@Module
public class AppModule {

    private final App mApp;

    public AppModule(App mApp) {
        this.mApp = mApp;
    }

    @Provides
    @Singleton
    public App app() {
        return mApp;
    }

    @Provides
    @Singleton
    @ApplicationContext
    Context applicationContext() {
        return mApp.getApplicationContext();
    }

    @Provides
    Application provideApplication() {
        return mApp;
    }
}
