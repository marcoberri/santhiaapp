package it.marcoberri.santhiaapp.db.datasource;

import it.marcoberri.santhiaapp.db.helper.DatabaseHelper;
import it.marcoberri.santhiaapp.model.PlaceModel;

import java.util.LinkedList;
import java.util.List;

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

public class TourPlaceModelDataSource {

	private static final String TAG = TourPlaceModelDataSource.class.getName();
	private Context context;

	DatabaseHelper helper = null;

	/* Inner class that defines the table contents */
	public static abstract class TourPlaceModelDBEntry implements BaseColumns {
		public static final String TABLE_NAME = "tour_place";
		public static final String COLUMN_NAME_TOUR_ID = "tour_id";
		public static final String COLUMN_NAME_PLACE_ID = "place_id";
	};

	private static final String[] ALLFIELD = {TourPlaceModelDBEntry.COLUMN_NAME_TOUR_ID,TourPlaceModelDBEntry.COLUMN_NAME_PLACE_ID};

	
	public TourPlaceModelDataSource(Context context) {
		this.context = context;
		if (helper == null) {
			helper = new DatabaseHelper(context);
		}
	};
	

	public long insertPlace(Integer tourid, Integer placeId) {

		final SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TourPlaceModelDBEntry.COLUMN_NAME_TOUR_ID, tourid);
		values.put(TourPlaceModelDBEntry.COLUMN_NAME_PLACE_ID, placeId);

		long newRowId = db.insert(TourPlaceModelDBEntry.TABLE_NAME, null, values);

		Log.d(TAG, "Insert tot element in "+TourPlaceModelDBEntry.TABLE_NAME+ " :" + newRowId);
		db.close();
		return newRowId;

	}

	public List<PlaceModel> getPlacesByTourId(Integer tourId) {

		final SQLiteDatabase db = helper.getWritableDatabase();
		final Cursor c = db.query(TourPlaceModelDBEntry.TABLE_NAME, ALLFIELD, TourPlaceModelDBEntry.COLUMN_NAME_TOUR_ID + " = ?", new String[] { ""+tourId }, null, null, TourPlaceModelDBEntry.COLUMN_NAME_PLACE_ID);
		
		final List<PlaceModel> places = new LinkedList<PlaceModel>();

			PlaceModelDataSource placeDS = new PlaceModelDataSource(context);
			
	       if (c.moveToFirst()) {
	           do {
	        	   places.add(placeDS.getPlaceByPlaceId(c.getInt(c.getColumnIndex(TourPlaceModelDBEntry.COLUMN_NAME_PLACE_ID))));
	           } while (c.moveToNext());
	       }
	       
	       Log.d(TAG,"tour place for tour :" + places );
	       db.close();
		return places;

	}

}
