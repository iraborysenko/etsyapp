package borysenko.etsyapp.dagger;

import javax.inject.Singleton;

import borysenko.etsyapp.ui.TabFragments.SearchFragment;
import dagger.Component;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 21:00
 */

@Singleton
@Component(modules = MainScreenModule.class)
public interface MainScreenComponent {
    void inject(SearchFragment fragment);
}
