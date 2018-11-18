package borysenko.etsyapp.dagger.components;

import javax.inject.Singleton;

import borysenko.etsyapp.dagger.modules.screen.SearchFragmentScreenModule;
import borysenko.etsyapp.ui.main.searchtab.SearchFragment;
import dagger.Component;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 21:00
 */

@Singleton
@Component(modules = SearchFragmentScreenModule.class)
public interface SearchFragmentScreenComponent {
    void inject(SearchFragment fragment);
}
