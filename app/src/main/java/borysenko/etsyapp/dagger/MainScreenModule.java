package borysenko.etsyapp.dagger;

import borysenko.etsyapp.ui.MainScreen;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 14/11/18
 * Time: 21:07
 */
@Module(includes = NetModule.class)
public class MainScreenModule {
    private final MainScreen.View mView;

    public MainScreenModule(MainScreen.View mView) {
        this.mView = mView;
    }

    @Provides
    MainScreen.View providesMainScreenView() {
        return mView;
    }
}
