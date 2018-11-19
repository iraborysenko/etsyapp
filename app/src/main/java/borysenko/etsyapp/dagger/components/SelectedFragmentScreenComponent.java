package borysenko.etsyapp.dagger.components;


import borysenko.etsyapp.dagger.PerActivity;
import borysenko.etsyapp.dagger.modules.screen.SelectedFragmentScreenModule;
import borysenko.etsyapp.ui.main.selectedtab.SelectedFragment;
import dagger.Component;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 18/11/18
 * Time: 13:13
 */
@PerActivity
@Component(modules = SelectedFragmentScreenModule.class, dependencies = AppComponent.class)
public interface SelectedFragmentScreenComponent {
    void inject(SelectedFragment fragment);
}