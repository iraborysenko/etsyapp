package borysenko.etsyapp.database;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import borysenko.etsyapp.dagger.ApplicationContext;
import borysenko.etsyapp.model.Merchandise;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 17/11/18
 * Time: 18:31
 */
@Singleton
public class DB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ListOfMerchandises";
    private static final String TABLE_MERCHANDISES = "merchandises";

    private static final String KEY_ID = "id";
    private static final String KEY_LISTING_ID = "listing_id";
    private static final String KEY_CATEGORY_ID = "category_id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PRICE = "price";
    private static final String KEY_CURRENCY_CODE = "currency_code";
    private static final String KEY_IMAGE_URL = "image_url";

    @Inject
    DB(@ApplicationContext Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        tableCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MERCHANDISES);
        onCreate(db);
    }

    private void tableCreate(SQLiteDatabase db) {
        try {
            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS "
                            + TABLE_MERCHANDISES + "("
                            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + KEY_LISTING_ID + " TEXT,"
                            + KEY_CATEGORY_ID + " TEXT,"
                            + KEY_TITLE + " TEXT,"
                            + KEY_DESCRIPTION + " TEXT,"
                            + KEY_PRICE + " TEXT,"
                            + KEY_CURRENCY_CODE + " TEXT,"
                            + KEY_IMAGE_URL + " TEXT" + ")"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Merchandise getMerchandise(int merchId) throws Resources.NotFoundException, NullPointerException {
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(
                    "SELECT * FROM "
                            + TABLE_MERCHANDISES
                            + " WHERE "
                            + KEY_ID
                            + " = ? ",
                    new String[]{merchId + ""});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                Merchandise merch = new Merchandise();
                merch.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                merch.setListingId(cursor.getString(cursor.getColumnIndex(KEY_LISTING_ID)));
                merch.setCategoryId(cursor.getString(cursor.getColumnIndex(KEY_CATEGORY_ID)));
                merch.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
                merch.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
                merch.setPrice(cursor.getString(cursor.getColumnIndex(KEY_PRICE)));
                merch.setCurrencyCode(cursor.getString(cursor.getColumnIndex(KEY_CURRENCY_CODE)));
                merch.setImageUrl(cursor.getString(cursor.getColumnIndex(KEY_IMAGE_URL)));
                return merch;
            } else {
                throw new Resources.NotFoundException("User with id " + merchId + " does not exists");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }

    List<Merchandise> getAllMerchandises() {
        List<Merchandise> contactList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_MERCHANDISES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Merchandise merchandise = new Merchandise();
                merchandise.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                merchandise.setListingId(cursor.getString(cursor.getColumnIndex(KEY_LISTING_ID)));
                merchandise.setCategoryId(cursor.getString(cursor.getColumnIndex(KEY_CATEGORY_ID)));
                merchandise.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
                merchandise.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
                merchandise.setPrice(cursor.getString(cursor.getColumnIndex(KEY_PRICE)));
                merchandise.setCurrencyCode(cursor.getString(cursor.getColumnIndex(KEY_CURRENCY_CODE)));
                merchandise.setImageUrl(cursor.getString(cursor.getColumnIndex(KEY_IMAGE_URL)));
                contactList.add(merchandise);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    void addMerchandise(Merchandise merchandise) {

        Log.e("tag", "message2");
        SQLiteDatabase db = this.getWritableDatabase();

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
    public int getMerchandisesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MERCHANDISES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    void deleteMerchandise(Merchandise selectedMerchandise) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_MERCHANDISES, KEY_ID + "=" + selectedMerchandise.getId(), null);

    }
}