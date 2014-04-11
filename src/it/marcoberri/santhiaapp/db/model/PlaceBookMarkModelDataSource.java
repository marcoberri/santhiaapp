package it.marcoberri.santhiaapp.db.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import it.marcoberri.santhiaapp.db.helper.DatabaseHelper;
import it.marcoberri.santhiaapp.db.model.PlaceModelDataSource.PlaceModelDBEntry;
import it.marcoberri.santhiaapp.model.PlaceImageModel;
import it.marcoberri.santhiaapp.model.PlaceModel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

public class PlaceBookMarkModelDataSource {

	private static final String TAG = PlaceBookMarkModelDataSource.class.getName();
	
	private static final String[] ALLFIELD = {PlaceBookMarkModelDBEntry.COLUMN_NAME_PLACE_ID, PlaceBookMarkModelDBEntry.COLUMN_NAME_TS};
	
	DatabaseHelper helper = null;

	public static abstract class PlaceBookMarkModelDBEntry implements BaseColumns {
		public static final String TABLE_NAME = "bookmark";
		public static final String COLUMN_NAME_PLACE_ID = "place_id";
		public static final String COLUMN_NAME_TS = "ts";
	};

	

	public PlaceBookMarkModelDataSource(Context context) {
		if (helper == null) {
			helper = new DatabaseHelper(context);
		}
	};
	

	public long insertPlaceBookmark(Integer place_id) {

		final SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(PlaceBookMarkModelDBEntry.COLUMN_NAME_PLACE_ID, place_id);
		values.put(PlaceBookMarkModelDBEntry.COLUMN_NAME_TS, new SimpleDateFormat("YYYY-MM-DD HH:MM:SS.SSS").format(new Date()));

		long newRowId = db.insert(PlaceBookMarkModelDBEntry.TABLE_NAME, null, values);

		Log.d(TAG, "Insert tot element in "+PlaceBookMarkModelDBEntry.TABLE_NAME+ " :" + newRowId);
		return newRowId;

	}
	
	
}
