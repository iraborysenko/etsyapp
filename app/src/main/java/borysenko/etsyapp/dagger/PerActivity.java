package borysenko.etsyapp.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 17/11/18
 * Time: 19:27
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}