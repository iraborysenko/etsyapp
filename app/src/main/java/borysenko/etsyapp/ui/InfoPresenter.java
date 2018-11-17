package borysenko.etsyapp.ui;

import javax.inject.Inject;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 17/11/18
 * Time: 15:32
 */
public class InfoPresenter implements InfoScreen.Presenter {
    private InfoScreen.View mView;

    @Inject
    InfoPresenter(InfoScreen.View mView) {
        this.mView = mView;
    }
}
