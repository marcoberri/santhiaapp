package it.marcoberri.santhiaapp.db.model;

import it.marcoberri.santhiaapp.db.helper.DatabaseHelper;
import android.content.ContentValues;
import android.content.Context;
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
	

	public long insertPlaceGps(Integer place_id, Double lat, Double lng) {
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
