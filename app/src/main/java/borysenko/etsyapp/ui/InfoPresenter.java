package borysenko.etsyapp.ui;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import javax.inject.Inject;

import borysenko.etsyapp.dagger.DB;
import borysenko.etsyapp.model.Merchandise;

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

    @Override
    public void saveButtonClicked(Merchandise merchandise, Context context) {

        DB db = new DB(context);

//            db.execSQL("DROP TABLE IF EXISTS " + "movies");
//            db1.onCreate(db);
        db.addMerchandise(merchandise);
    }
}
