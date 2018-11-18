package borysenko.etsyapp.ui.main.selectedtab;

import javax.inject.Inject;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 18/11/18
 * Time: 13:12
 */
public class SelectedPresenter implements SelectedFragmentScreen.Presenter{
    private SelectedFragmentScreen.View mView;

    @Inject
    SelectedPresenter(SelectedFragmentScreen.View mView) {
        this.mView = mView;
    }
}
