package borysenko.etsyapp.dagger.modules.screen;

import borysenko.etsyapp.ui.info.InfoScreen;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 17/11/18
 * Time: 15:41
 */
@Module()
public class InfoScreenModule {
    private final InfoScreen.View mView;

    public InfoScreenModule(InfoScreen.View mView) {
        this.mView = mView;
    }

    @Provides
    InfoScreen.View providesMovieScreenView() {
        return mView;
    }
}