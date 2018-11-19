package borysenko.etsyapp.dagger.modules.screen;

import borysenko.etsyapp.ui.main.selectedtab.SelectedFragment;
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
    private SelectedFragment mFragment;

    public SelectedFragmentScreenModule(SelectedFragment activity) {
        mFragment = activity;
    }

    @Provides
    SelectedFragment provideActivity() {
        return mFragment;
    }
}