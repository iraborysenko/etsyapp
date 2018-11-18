package borysenko.etsyapp.dagger.modules.screen;

import borysenko.etsyapp.dagger.modules.NetModule;
import borysenko.etsyapp.ui.main.searchtab.SearchFragmentScreen;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 21:07
 */
@Module(includes = NetModule.class)
public class SearchFragmentScreenModule {
    private final SearchFragmentScreen.View mView;

    public SearchFragmentScreenModule(SearchFragmentScreen.View mView) {
        this.mView = mView;
    }

    @Provides
    SearchFragmentScreen.View providesSearchFragmentScreenView() {
        return mView;
    }
}
