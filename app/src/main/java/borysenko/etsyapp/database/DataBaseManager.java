package borysenko.etsyapp.database;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import borysenko.etsyapp.dagger.ApplicationContext;
import borysenko.etsyapp.model.Merchandise;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 18/11/18
 * Time: 21:33
 */
@Singleton
public class DataBaseManager {

    private Context mContext;
    private DB mDb;

    @Inject
    public DataBaseManager(@ApplicationContext Context context,
                       DB db ) {
        mContext = context;
        mDb = db;
    }

    public List<Merchandise> loadAllData() {
        return mDb.getAllMerchandises();
    }

    public void saveMerchandise(Merchandise merchandise) {
        mDb.addMerchandise(merchandise);
    }

    public void removeMerchandise(Merchandise selectedMerchandise) {
        mDb.deleteMerchandise(selectedMerchandise);
    }
}
