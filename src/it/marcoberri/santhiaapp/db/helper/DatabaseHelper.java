package it.marcoberri.santhiaapp.db.helper;

import it.marcoberri.santhiaapp.db.model.PlaceBookMarkModelDataSource.PlaceBookMarkModelDBEntry;
import it.marcoberri.santhiaapp.db.model.PlaceGpsModelDataSource.PlaceGpsModelDBEntry;
import it.marcoberri.santhiaapp.db.model.PlaceImageModelDataSource.PlaceImageModelDBEntry;
import it.marcoberri.santhiaapp.db.model.PlaceModelDataSource.PlaceModelDBEntry;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "SanthiaApp.db";
	private static final String TAG = SQLiteOpenHelper.class.getName();
	private static final String TEXT_TYPE = " TEXT";
	private static final String DOUBLE_TYPE = " DOUBLE";
	private static final String INTEGER_TYPE = " INTEGER";
	private static final String NOTNULL_TYPE = " NOT NULL";
	private static final String COMMA_SEP = ",";
	
	private static final String SQL_CREATE_PLACES = "CREATE TABLE "
			+ PlaceModelDBEntry.TABLE_NAME + " (" + 
			PlaceModelDBEntry.COLUMN_NAME_ENTRY_ID + INTEGER_TYPE + NOTNULL_TYPE  + COMMA_SEP + 
			PlaceModelDBEntry.COLUMN_NAME_TITLE	+ TEXT_TYPE + COMMA_SEP + 
			PlaceModelDBEntry.COLUMN_NAME_TEXT	+ TEXT_TYPE + COMMA_SEP + 
			PlaceModelDBEntry.COLUMN_NAME_SUBTITLE	+ TEXT_TYPE + COMMA_SEP +
			PlaceModelDBEntry.COLUMN_NAME_IMAGE	+ TEXT_TYPE+ COMMA_SEP +
			PlaceModelDBEntry.COLUMN_NAME_LOCALE	+ TEXT_TYPE + "  NOT NULL" + COMMA_SEP +
			" PRIMARY KEY ( "+PlaceModelDBEntry.COLUMN_NAME_ENTRY_ID+ COMMA_SEP + PlaceModelDBEntry.COLUMN_NAME_LOCALE + ")" +
			")";

	private static final String SQL_CREATE_IMAGES = "CREATE TABLE "
			+ PlaceImageModelDBEntry.TABLE_NAME + " (" + 
			PlaceImageModelDBEntry.COLUMN_NAME_ENTRY_ID + INTEGER_TYPE + NOTNULL_TYPE + COMMA_SEP + 
			PlaceImageModelDBEntry.COLUMN_NAME_PLACE_ID + INTEGER_TYPE +NOTNULL_TYPE+ COMMA_SEP +
			PlaceImageModelDBEntry.COLUMN_NAME_URL	+ TEXT_TYPE + COMMA_SEP +
			PlaceImageModelDBEntry.COLUMN_NAME_DISCLAMER	+ TEXT_TYPE + COMMA_SEP +
			PlaceImageModelDBEntry.COLUMN_NAME_TITLE	+ TEXT_TYPE + COMMA_SEP + 
			PlaceImageModelDBEntry.COLUMN_NAME_TEXT	+ TEXT_TYPE  + COMMA_SEP +
			" PRIMARY KEY ( "+PlaceImageModelDBEntry.COLUMN_NAME_ENTRY_ID+ COMMA_SEP + PlaceImageModelDBEntry.COLUMN_NAME_PLACE_ID + ")" + COMMA_SEP +
			" FOREIGN KEY("+PlaceImageModelDBEntry.COLUMN_NAME_PLACE_ID+") REFERENCES "+PlaceModelDBEntry.TABLE_NAME+"("+PlaceModelDBEntry.COLUMN_NAME_ENTRY_ID +") )";

	private static final String SQL_CREATE_GPS = "CREATE TABLE "
			+ PlaceGpsModelDBEntry.TABLE_NAME + " (" + 
			PlaceGpsModelDBEntry.COLUMN_NAME_PLACE_ID + INTEGER_TYPE + NOTNULL_TYPE + COMMA_SEP +
			PlaceGpsModelDBEntry.COLUMN_NAME_LAT	+ DOUBLE_TYPE + NOTNULL_TYPE + COMMA_SEP +
			PlaceGpsModelDBEntry.COLUMN_NAME_LNG	+ DOUBLE_TYPE + NOTNULL_TYPE + COMMA_SEP +
			" PRIMARY KEY ( "+PlaceGpsModelDBEntry.COLUMN_NAME_PLACE_ID + ")" + COMMA_SEP +
			" FOREIGN KEY("+PlaceGpsModelDBEntry.COLUMN_NAME_PLACE_ID+") REFERENCES "+PlaceModelDBEntry.TABLE_NAME+"("+PlaceModelDBEntry.COLUMN_NAME_ENTRY_ID +") )";

	private static final String SQL_CREATE_BOOKMARK = "CREATE TABLE "
			+ PlaceBookMarkModelDBEntry.TABLE_NAME + " (" + 
			PlaceBookMarkModelDBEntry.COLUMN_NAME_PLACE_ID + INTEGER_TYPE + NOTNULL_TYPE + COMMA_SEP +
			PlaceBookMarkModelDBEntry.COLUMN_NAME_TS	+ TEXT_TYPE + COMMA_SEP + 
			" PRIMARY KEY ( "+PlaceBookMarkModelDBEntry.COLUMN_NAME_PLACE_ID + ")" +
			" FOREIGN KEY("+ PlaceBookMarkModelDBEntry.COLUMN_NAME_PLACE_ID+") REFERENCES "+PlaceModelDBEntry.TABLE_NAME+"("+PlaceModelDBEntry.COLUMN_NAME_ENTRY_ID +") )";
	
	private static final String SQL_DELETE_PLACES = "DROP TABLE IF EXISTS " + PlaceModelDBEntry.TABLE_NAME;
	private static final String SQL_DELETE_IMAGES = "DROP TABLE IF EXISTS " + PlaceImageModelDBEntry.TABLE_NAME;
	private static final String SQL_DELETE_GPS = "DROP TABLE IF EXISTS " + PlaceGpsModelDBEntry.TABLE_NAME;
	private static final String SQL_DELETE_BOOKMARK = "DROP TABLE IF EXISTS " + PlaceBookMarkModelDBEntry.TABLE_NAME;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		Log.d(TAG, "onCreate");
		db.execSQL(SQL_CREATE_PLACES);
		db.execSQL(SQL_CREATE_IMAGES);
		db.execSQL(SQL_CREATE_GPS);
		db.execSQL(SQL_CREATE_BOOKMARK);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d(TAG, "onUpgrade");
		db.execSQL(SQL_DELETE_BOOKMARK);
		db.execSQL(SQL_DELETE_GPS);
		db.execSQL(SQL_DELETE_IMAGES);
		db.execSQL(SQL_DELETE_PLACES);
		
		
		onCreate(db);
	}

	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d(TAG, "onDowngrade");
		onUpgrade(db, oldVersion, newVersion);
	}
	
	public void clean() {
		final SQLiteDatabase db = this.getWritableDatabase();
		Log.d(TAG, "clean");
		onUpgrade(db, 1,2);
	}
	
	

	
}