package borysenko.etsyapp.dagger.components;

import javax.inject.Singleton;

import borysenko.etsyapp.dagger.modules.screen.SelectedFragmentScreenModule;
import borysenko.etsyapp.ui.main.selectedtab.SelectedFragment;
import dagger.Component;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 18/11/18
 * Time: 13:13
 */
@Singleton
@Component(modules = SelectedFragmentScreenModule.class)
public interface SelectedFragmentScreenComponent {
    void inject(SelectedFragment fragment);
}
