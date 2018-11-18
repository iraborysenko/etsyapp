package borysenko.etsyapp.dagger.modules.screen;

import borysenko.etsyapp.ui.main.selectedtab.SelectedFragmentScreen;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 18/11/18
 * Time: 13:13
 */
@Module()
public class SelectedFragmentScreenModule {
    private final SelectedFragmentScreen.View mView;

    public SelectedFragmentScreenModule(SelectedFragmentScreen.View mView) {
        this.mView = mView;
    }

    @Provides
    SelectedFragmentScreen.View providesSelectedFragmentScreenView() {
        return mView;
    }
}