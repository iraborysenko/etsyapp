package borysenko.etsyapp.dagger;

import javax.inject.Singleton;

import borysenko.etsyapp.App;
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
}
