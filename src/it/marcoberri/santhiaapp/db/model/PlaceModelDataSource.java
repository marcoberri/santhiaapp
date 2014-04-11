package it.marcoberri.santhiaapp.db.model;

import java.util.LinkedList;
import java.util.List;

import it.marcoberri.santhiaapp.db.helper.DatabaseHelper;
import it.marcoberri.santhiaapp.model.PlaceModel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

public class PlaceModelDataSource {

	private static final String TAG = PlaceModelDataSource.class.getName();
	
	DatabaseHelper helper = null;

	/* Inner class that defines the table contents */
	public static abstract class PlaceModelDBEntry implements BaseColumns {
		public static final String TABLE_NAME = "places";
		public static final String COLUMN_NAME_ENTRY_ID = "id";
		public static final String COLUMN_NAME_TITLE = "title";
		public static final String COLUMN_NAME_SUBTITLE = "subtitle";
		public static final String COLUMN_NAME_IMAGE = "image";
		public static final String COLUMN_NAME_TEXT = "text";
		/*
		 * private GpsData gps; private List<Image> images;
		 */
	};

	public PlaceModelDataSource(Context context) {
		if (helper == null) {
			helper = new DatabaseHelper(context);
		}
	};
	

	public long insertPlace(Integer id, String title, String subtitle,String text) {

		final SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(PlaceModelDBEntry.COLUMN_NAME_ENTRY_ID, id);
		values.put(PlaceModelDBEntry.COLUMN_NAME_TITLE, title);
		values.put(PlaceModelDBEntry.COLUMN_NAME_TEXT, text);
		values.put(PlaceModelDBEntry.COLUMN_NAME_SUBTITLE, subtitle);

		long newRowId = db.insert(PlaceModelDBEntry.TABLE_NAME, null, values);

		Log.d(TAG, "Insert tot element in "+PlaceModelDBEntry.TABLE_NAME+ " :" + newRowId);
		return newRowId;

	}

	public List<PlaceModel> getPlaces() {

		final SQLiteDatabase db = helper.getWritableDatabase();
		final Cursor c = db.query(PlaceModelDBEntry.TABLE_NAME, new String[] {PlaceModelDBEntry.COLUMN_NAME_ENTRY_ID,PlaceModelDBEntry.COLUMN_NAME_TITLE,PlaceModelDBEntry.COLUMN_NAME_TEXT,PlaceModelDBEntry.COLUMN_NAME_SUBTITLE}, null, null, null, null, PlaceModelDBEntry.COLUMN_NAME_ENTRY_ID);

		
		final List<PlaceModel> places = new LinkedList<PlaceModel>();

	       if (c.moveToFirst()) {
	           do {
	        	   final PlaceModel place = new PlaceModel();
	        	   place.setId(Integer.parseInt(c.getString(0)));
	        	   place.setTitle(c.getString(1));
	        	   place.setText(c.getString(2));
	        	   place.setSubtitle(c.getString(3));
	        	   places.add(place);
	           } while (c.moveToNext());
	       }
	       
	       Log.d(TAG,"places :" + places );
	       
		return places;

	}

	
}