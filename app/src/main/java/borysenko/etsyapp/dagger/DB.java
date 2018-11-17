package borysenko.etsyapp.dagger;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import borysenko.etsyapp.model.Merchandise;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 17/11/18
 * Time: 18:31
 */
public class DB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ListOfMerchandises";
    private static final String TABLE_MERCHANDISES = "merchandises";

    private static final String KEY_LISTING_ID = "listing_id";
    private static final String KEY_CATEGORY_ID = "category_id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PRICE = "price";
    private static final String KEY_CURRENCY_CODE = "currency_code";
    private static final String KEY_IMAGE_URL = "image_url";

    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MERCHANDISE_TABLE = "CREATE TABLE " + TABLE_MERCHANDISES + "("
                + KEY_LISTING_ID + " TEXT PRIMARY KEY," + KEY_CATEGORY_ID + " TEXT,"
                + KEY_TITLE + " TEXT," + KEY_DESCRIPTION + " TEXT," + KEY_PRICE + " TEXT,"
                + KEY_CURRENCY_CODE + " TEXT," + KEY_IMAGE_URL + " TEXT" + ")";
        db.execSQL(CREATE_MERCHANDISE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MERCHANDISES);
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    public void addMerchandise(Merchandise merchandise) {
        SQLiteDatabase db = this.getWritableDatabase();

        Log.e("Something", merchandise.getTitle());
        ContentValues values = new ContentValues();
        values.put(KEY_LISTING_ID, merchandise.getListingId());
        values.put(KEY_CATEGORY_ID, merchandise.getCategoryId());
        values.put(KEY_TITLE, merchandise.getTitle());
        values.put(KEY_DESCRIPTION, merchandise.getDescription());
        values.put(KEY_PRICE, merchandise.getPrice());
        values.put(KEY_CURRENCY_CODE, merchandise.getCurrencyCode());
        values.put(KEY_IMAGE_URL, merchandise.getImageUrl());

        db.insert(TABLE_MERCHANDISES, null, values);
        db.close();
    }

//    Merchandise getMerchandise(String id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_MERCHANDISES, new String[] { KEY_ID,
//                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//        return merchandise;
//    }

    public List<Merchandise> getAllMerchandises() {
        List<Merchandise> contactList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_MERCHANDISES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Merchandise merchandise = new Merchandise();
                merchandise.setListingId(cursor.getString(0));
                merchandise.setCategoryId(cursor.getString(1));
                merchandise.setTitle(cursor.getString(2));
                merchandise.setDescription(cursor.getString(3));
                merchandise.setPrice(cursor.getString(4));
                merchandise.setCurrencyCode(cursor.getString(5));
                merchandise.setImageUrl(cursor.getString(6));

                contactList.add(merchandise);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public int getMerchandisesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MERCHANDISES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

}
