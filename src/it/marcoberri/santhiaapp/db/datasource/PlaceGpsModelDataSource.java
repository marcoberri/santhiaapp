package it.marcoberri.santhiaapp.db.datasource;

import it.marcoberri.santhiaapp.db.helper.DatabaseHelper;
import it.marcoberri.santhiaapp.model.PlaceGpsModel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;
/**
 * @author Marco Berri - marcoberri@gmail.com
 *
 */

public class PlaceGpsModelDataSource {

	private static final String TAG = PlaceGpsModelDataSource.class.getName();

	DatabaseHelper helper = null;

	public static abstract class PlaceGpsModelDBEntry implements BaseColumns {
		public static final String TABLE_NAME = "gps";
		public static final String COLUMN_NAME_PLACE_ID = "place_id";
		public static final String COLUMN_NAME_LAT = "lat";
		public static final String COLUMN_NAME_LNG = "lng";
	};

	public static final String[] ALLFIELDS = new String[] {
			PlaceGpsModelDBEntry.COLUMN_NAME_PLACE_ID,
			PlaceGpsModelDBEntry.COLUMN_NAME_LAT,
			PlaceGpsModelDBEntry.COLUMN_NAME_LNG };

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
		long newRowId = db
				.insert(PlaceGpsModelDBEntry.TABLE_NAME, null, values);
		Log.d(TAG, "Insert tot element in " + PlaceGpsModelDBEntry.TABLE_NAME
				+ " :" + newRowId);
		db.close();
		return newRowId;

	}

	public PlaceGpsModel getGpsByPlaceId(Integer placeId) {

		final SQLiteDatabase db = helper.getWritableDatabase();
		final Cursor c = db.query(PlaceGpsModelDBEntry.TABLE_NAME, ALLFIELDS,
				PlaceGpsModelDBEntry.COLUMN_NAME_PLACE_ID + " = ?",
				new String[] { placeId.toString() }, null, null, null);

		final PlaceGpsModel gps = new PlaceGpsModel();

		if (c.moveToFirst()) {
			gps.setPlace_id(c.getInt(c
					.getColumnIndex(PlaceGpsModelDBEntry.COLUMN_NAME_PLACE_ID)));
			gps.setLat((c.getFloat(c
					.getColumnIndex(PlaceGpsModelDBEntry.COLUMN_NAME_LAT))));
			gps.setLng((c.getFloat(c
					.getColumnIndex(PlaceGpsModelDBEntry.COLUMN_NAME_LNG))));
		}

		Log.d(TAG, "gps :" + gps);
		db.close();
		return gps;
	}

}
