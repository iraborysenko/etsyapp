package borysenko.etsyapp.dagger.components;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import borysenko.etsyapp.App;
import borysenko.etsyapp.dagger.ApplicationContext;
import borysenko.etsyapp.database.DB;
import borysenko.etsyapp.dagger.modules.AppModule;
import borysenko.etsyapp.database.DataBaseManager;
import dagger.Component;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 20:52
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(App app);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    DataBaseManager getDataManager();

    DB getDB();

}