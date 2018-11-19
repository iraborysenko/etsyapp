package borysenko.etsyapp.dagger.components;


import borysenko.etsyapp.dagger.PerActivity;
import borysenko.etsyapp.dagger.modules.screen.InfoScreenModule;
import borysenko.etsyapp.ui.info.InfoActivity;
import dagger.Component;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 17/11/18
 * Time: 15:39
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = InfoScreenModule.class)
public interface InfoScreenComponent {

    void inject(InfoActivity infoActivity);

}

