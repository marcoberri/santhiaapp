package it.marcoberri.santhiaapp.db.helper;

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
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_PLACES = "CREATE TABLE "
			+ PlaceModelDBEntry.TABLE_NAME + " (" + 
			PlaceModelDBEntry.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY" + COMMA_SEP + 
			PlaceModelDBEntry.COLUMN_NAME_TITLE	+ TEXT_TYPE + COMMA_SEP + 
			PlaceModelDBEntry.COLUMN_NAME_TEXT	+ TEXT_TYPE + COMMA_SEP + 
			PlaceModelDBEntry.COLUMN_NAME_SUBTITLE	+ TEXT_TYPE + COMMA_SEP +
			PlaceModelDBEntry.COLUMN_NAME_IMAGE	+ TEXT_TYPE +
			")";

	private static final String SQL_CREATE_IMAGES = "CREATE TABLE "
			+ PlaceImageModelDBEntry.TABLE_NAME + " (" + 
			PlaceImageModelDBEntry.COLUMN_NAME_ENTRY_ID + " INTEGER" + COMMA_SEP + 
			PlaceImageModelDBEntry.COLUMN_NAME_PLACE_ID + " INTEGER" + COMMA_SEP +
			PlaceImageModelDBEntry.COLUMN_NAME_URL	+ TEXT_TYPE + COMMA_SEP +
			PlaceImageModelDBEntry.COLUMN_NAME_DISCLAMER	+ TEXT_TYPE + COMMA_SEP +
			PlaceImageModelDBEntry.COLUMN_NAME_TITLE	+ TEXT_TYPE + COMMA_SEP + 
			PlaceImageModelDBEntry.COLUMN_NAME_TEXT	+ TEXT_TYPE + ", PRIMARY KEY ( "+PlaceImageModelDBEntry.COLUMN_NAME_ENTRY_ID+ COMMA_SEP + PlaceImageModelDBEntry.COLUMN_NAME_PLACE_ID + ")" + COMMA_SEP +
			" FOREIGN KEY("+PlaceImageModelDBEntry.COLUMN_NAME_PLACE_ID+") REFERENCES "+PlaceModelDBEntry.TABLE_NAME+"("+PlaceModelDBEntry.COLUMN_NAME_ENTRY_ID +") )";
	
	private static final String SQL_DELETE_PLACES = "DROP TABLE IF EXISTS " + PlaceModelDBEntry.TABLE_NAME;
	private static final String SQL_DELETE_IMAGES = "DROP TABLE IF EXISTS " + PlaceImageModelDBEntry.TABLE_NAME;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		Log.d(TAG, "onCreate");
		db.execSQL(SQL_CREATE_PLACES);
		db.execSQL(SQL_CREATE_IMAGES);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d(TAG, "onUpgrade");
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