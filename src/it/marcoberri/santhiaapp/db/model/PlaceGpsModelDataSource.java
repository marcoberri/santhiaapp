package it.marcoberri.santhiaapp.db.model;

import java.util.LinkedList;
import java.util.List;

import it.marcoberri.santhiaapp.db.helper.DatabaseHelper;
import it.marcoberri.santhiaapp.db.model.PlaceModelDataSource.PlaceModelDBEntry;
import it.marcoberri.santhiaapp.model.PlaceModel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

public class PlaceGpsModelDataSource {

	private static final String TAG = PlaceGpsModelDataSource.class.getName();
	
	DatabaseHelper helper = null;

	public static abstract class PlaceGpsModelDBEntry implements BaseColumns {
		public static final String TABLE_NAME = "gps";
		public static final String COLUMN_NAME_PLACE_ID = "place_id";
		public static final String COLUMN_NAME_LAT = "lat";
		public static final String COLUMN_NAME_LNG = "lng";
	};

	

	public PlaceGpsModelDataSource(Context context) {
		if (helper == null) {
			helper = new DatabaseHelper(context);
		}
	};
	

	public long insertPlaceImage(Integer id, Integer place_id, Float lat, Float lng) {

		final SQLiteDatabase db = helper.getWritableDatabase();
		final ContentValues values = new ContentValues();
		values.put(PlaceGpsModelDBEntry.COLUMN_NAME_PLACE_ID, place_id);
		values.put(PlaceGpsModelDBEntry.COLUMN_NAME_LAT, lat);
		values.put(PlaceGpsModelDBEntry.COLUMN_NAME_LNG, lng);
		long newRowId = db.insert(PlaceGpsModelDBEntry.TABLE_NAME, null, values);
		Log.d(TAG, "Insert tot element in "+PlaceGpsModelDBEntry.TABLE_NAME+ " :" + newRowId);
		return newRowId;

	}

	
}
