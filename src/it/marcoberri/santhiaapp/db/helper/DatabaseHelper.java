package it.marcoberri.santhiaapp.db.helper;

import it.marcoberri.santhiaapp.db.datasource.ExtraModelDataSource.ExtraModelDBEntry;
import it.marcoberri.santhiaapp.db.datasource.PlaceBookmarkModelDataSource.PlaceBookmarkModelDBEntry;
import it.marcoberri.santhiaapp.db.datasource.PlaceGpsModelDataSource.PlaceGpsModelDBEntry;
import it.marcoberri.santhiaapp.db.datasource.PlaceImageModelDataSource.PlaceImageModelDBEntry;
import it.marcoberri.santhiaapp.db.datasource.PlaceModelDataSource.PlaceModelDBEntry;
import it.marcoberri.santhiaapp.db.datasource.TourModelDataSource.TourModelDBEntry;
import it.marcoberri.santhiaapp.db.datasource.TourPlaceModelDataSource.TourPlaceModelDBEntry;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author Marco Berri - marcoberri@gmail.com
 * 
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = SQLiteOpenHelper.class.getName();
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SanthiaApp.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String DOUBLE_TYPE = " DOUBLE";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String NOTNULL_TYPE = " NOT NULL";
    private static final String COMMA_SEP = ",";
    private static final String AUTOINCREMENT = " AUTOINCREMENT ";

    private static final String SQL_CREATE_PLACES = "CREATE TABLE " + PlaceModelDBEntry.TABLE_NAME + " (" + PlaceModelDBEntry.COLUMN_NAME_ENTRY_ID + INTEGER_TYPE + NOTNULL_TYPE + COMMA_SEP + PlaceModelDBEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP + PlaceModelDBEntry.COLUMN_NAME_SUBTITLE + TEXT_TYPE + COMMA_SEP + PlaceModelDBEntry.COLUMN_NAME_TEXT + TEXT_TYPE + COMMA_SEP + PlaceModelDBEntry.COLUMN_NAME_IMAGE + TEXT_TYPE + COMMA_SEP + PlaceModelDBEntry.COLUMN_NAME_ADDRESS + TEXT_TYPE + COMMA_SEP + PlaceModelDBEntry.COLUMN_NAME_LOCALE + TEXT_TYPE + "  NOT NULL" + COMMA_SEP + " PRIMARY KEY ( " + PlaceModelDBEntry.COLUMN_NAME_ENTRY_ID + COMMA_SEP + PlaceModelDBEntry.COLUMN_NAME_LOCALE + ")" + ")";
    private static final String SQL_CREATE_EXTRA = "CREATE TABLE " + ExtraModelDBEntry.TABLE_NAME + " (" + ExtraModelDBEntry.COLUMN_NAME_NAME + TEXT_TYPE + NOTNULL_TYPE + COMMA_SEP + ExtraModelDBEntry.COLUMN_NAME_VALUESTRING + TEXT_TYPE + COMMA_SEP + ExtraModelDBEntry.COLUMN_NAME_VALUEINT + INTEGER_TYPE + COMMA_SEP + ExtraModelDBEntry.COLUMN_NAME_TS + TEXT_TYPE + ExtraModelDBEntry.COLUMN_NAME_CATEGORY + TEXT_TYPE + COMMA_SEP + " PRIMARY KEY ( " + ExtraModelDBEntry.COLUMN_NAME_NAME + ")" + ")";
    private static final String SQL_CREATE_IMAGES = "CREATE TABLE " + PlaceImageModelDBEntry.TABLE_NAME + " (" + PlaceImageModelDBEntry.COLUMN_NAME_ENTRY_ID + INTEGER_TYPE + NOTNULL_TYPE + COMMA_SEP + PlaceImageModelDBEntry.COLUMN_NAME_PLACE_ID + INTEGER_TYPE + NOTNULL_TYPE + COMMA_SEP + PlaceImageModelDBEntry.COLUMN_NAME_URL + TEXT_TYPE + COMMA_SEP + PlaceImageModelDBEntry.COLUMN_NAME_DISCLAMER + TEXT_TYPE + COMMA_SEP + PlaceImageModelDBEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP + PlaceImageModelDBEntry.COLUMN_NAME_TEXT + TEXT_TYPE + COMMA_SEP + " PRIMARY KEY ( " + PlaceImageModelDBEntry.COLUMN_NAME_ENTRY_ID + COMMA_SEP + PlaceImageModelDBEntry.COLUMN_NAME_PLACE_ID + ")" + COMMA_SEP + " FOREIGN KEY(" + PlaceImageModelDBEntry.COLUMN_NAME_PLACE_ID + ") REFERENCES " + PlaceModelDBEntry.TABLE_NAME + "(" + PlaceModelDBEntry.COLUMN_NAME_ENTRY_ID + ") )";
    private static final String SQL_CREATE_GPS = "CREATE TABLE " + PlaceGpsModelDBEntry.TABLE_NAME + " (" + PlaceGpsModelDBEntry.COLUMN_NAME_PLACE_ID + INTEGER_TYPE + NOTNULL_TYPE + COMMA_SEP + PlaceGpsModelDBEntry.COLUMN_NAME_LAT + DOUBLE_TYPE + NOTNULL_TYPE + COMMA_SEP + PlaceGpsModelDBEntry.COLUMN_NAME_LNG + DOUBLE_TYPE + NOTNULL_TYPE + COMMA_SEP + " PRIMARY KEY ( " + PlaceGpsModelDBEntry.COLUMN_NAME_PLACE_ID + ")" + COMMA_SEP + " FOREIGN KEY(" + PlaceGpsModelDBEntry.COLUMN_NAME_PLACE_ID + ") REFERENCES " + PlaceModelDBEntry.TABLE_NAME + "(" + PlaceModelDBEntry.COLUMN_NAME_ENTRY_ID + ") )";
    private static final String SQL_CREATE_BOOKMARK = "CREATE TABLE " + PlaceBookmarkModelDBEntry.TABLE_NAME + " (" + PlaceBookmarkModelDBEntry.COLUMN_NAME_PLACE_ID + INTEGER_TYPE + NOTNULL_TYPE + COMMA_SEP + PlaceBookmarkModelDBEntry.COLUMN_NAME_TS + TEXT_TYPE + COMMA_SEP + " PRIMARY KEY ( " + PlaceBookmarkModelDBEntry.COLUMN_NAME_PLACE_ID + ")" + " FOREIGN KEY(" + PlaceBookmarkModelDBEntry.COLUMN_NAME_PLACE_ID + ") REFERENCES " + PlaceModelDBEntry.TABLE_NAME + "(" + PlaceModelDBEntry.COLUMN_NAME_ENTRY_ID + ") )";
    private static final String SQL_CREATE_TOUR = "CREATE TABLE " + TourModelDBEntry.TABLE_NAME + " (" + TourModelDBEntry.COLUMN_NAME_ENTRY_ID + INTEGER_TYPE + " PRIMARY KEY " + AUTOINCREMENT + COMMA_SEP + TourModelDBEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP + TourModelDBEntry.COLUMN_NAME_VOTE + INTEGER_TYPE + COMMA_SEP + TourModelDBEntry.COLUMN_NAME_COMMUNITY + INTEGER_TYPE + ")";
    private static final String SQL_CREATE_TOURPLACE = "CREATE TABLE " + TourPlaceModelDBEntry.TABLE_NAME + " (" + TourPlaceModelDBEntry.COLUMN_NAME_TOUR_ID + INTEGER_TYPE + NOTNULL_TYPE + COMMA_SEP + TourPlaceModelDBEntry.COLUMN_NAME_PLACE_ID + INTEGER_TYPE + NOTNULL_TYPE + COMMA_SEP + " PRIMARY KEY ( " + TourPlaceModelDBEntry.COLUMN_NAME_TOUR_ID + COMMA_SEP + TourPlaceModelDBEntry.COLUMN_NAME_PLACE_ID + ")" + COMMA_SEP + " FOREIGN KEY(" + TourPlaceModelDBEntry.COLUMN_NAME_PLACE_ID + ") REFERENCES " + PlaceModelDBEntry.TABLE_NAME + "(" + PlaceModelDBEntry.COLUMN_NAME_ENTRY_ID + ") " + COMMA_SEP + " FOREIGN KEY(" + TourPlaceModelDBEntry.COLUMN_NAME_TOUR_ID + ") REFERENCES " + TourModelDBEntry.TABLE_NAME + "(" + TourModelDBEntry.COLUMN_NAME_ENTRY_ID + "))";

    private static final String SQL_DELETE_PLACES = "DROP TABLE IF EXISTS " + PlaceModelDBEntry.TABLE_NAME;
    private static final String SQL_DELETE_IMAGES = "DROP TABLE IF EXISTS " + PlaceImageModelDBEntry.TABLE_NAME;
    private static final String SQL_DELETE_GPS = "DROP TABLE IF EXISTS " + PlaceGpsModelDBEntry.TABLE_NAME;
    private static final String SQL_DELETE_BOOKMARK = "DROP TABLE IF EXISTS " + PlaceBookmarkModelDBEntry.TABLE_NAME;
    private static final String SQL_DELETE_TOUR = "DROP TABLE IF EXISTS " + TourModelDBEntry.TABLE_NAME;
    private static final String SQL_DELETE_TOURPLACE = "DROP TABLE IF EXISTS " + TourPlaceModelDBEntry.TABLE_NAME;
    private static final String SQL_DELETE_EXTRA = "DROP TABLE IF EXISTS " + ExtraModelDBEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
	Log.i(TAG, "onCreateView()");
	db.execSQL(SQL_CREATE_PLACES);
	db.execSQL(SQL_CREATE_IMAGES);
	db.execSQL(SQL_CREATE_GPS);
	db.execSQL(SQL_CREATE_BOOKMARK);
	db.execSQL(SQL_CREATE_TOUR);
	db.execSQL(SQL_CREATE_TOURPLACE);
	db.execSQL(SQL_CREATE_EXTRA);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	Log.d(TAG, "onUpgrade");
	db.execSQL(SQL_DELETE_BOOKMARK);
	db.execSQL(SQL_DELETE_TOURPLACE);
	db.execSQL(SQL_DELETE_GPS);
	db.execSQL(SQL_DELETE_TOUR);
	db.execSQL(SQL_DELETE_IMAGES);
	db.execSQL(SQL_DELETE_PLACES);
	db.execSQL(SQL_DELETE_EXTRA);
	onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	Log.d(TAG, "onDowngrade");
	onUpgrade(db, oldVersion, newVersion);
    }

    public void clean() {
	final SQLiteDatabase db = this.getWritableDatabase();
	Log.d(TAG, "clean");
	onUpgrade(db, 1, 2);
    }

}