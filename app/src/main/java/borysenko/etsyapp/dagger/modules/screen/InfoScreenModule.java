package borysenko.etsyapp.dagger.modules.screen;

import android.content.Context;

import borysenko.etsyapp.dagger.ActivityContext;
import borysenko.etsyapp.ui.info.InfoActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 17/11/18
 * Time: 15:41
 */
@Module
public class InfoScreenModule {

    private InfoActivity mActivity;

    public InfoScreenModule(InfoActivity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    InfoActivity provideActivity() {
        return mActivity;
    }
}