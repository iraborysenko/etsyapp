package borysenko.etsyapp.ui.info;


import android.content.Context;

import borysenko.etsyapp.model.Merchandise;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 17/11/18
 * Time: 15:31
 */
public interface InfoScreen {
    interface View {
    }

    interface Presenter {
        void saveButtonClicked(Merchandise merchandise, Context context);
    }
}
