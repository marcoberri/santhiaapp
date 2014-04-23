package it.marcoberri.santhiaapp.db.model;

import java.util.LinkedList;
import java.util.List;

import it.marcoberri.santhiaapp.db.helper.DatabaseHelper;
import it.marcoberri.santhiaapp.db.model.PlaceGpsModelDataSource.PlaceGpsModelDBEntry;
import it.marcoberri.santhiaapp.model.PlaceGpsModel;
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
		public static final String COLUMN_NAME_ADDRESS = "address";
		public static final String COLUMN_NAME_LOCALE = "locale";
	};

	private static final String[] ALLFIELD = {PlaceModelDBEntry.COLUMN_NAME_ENTRY_ID,PlaceModelDBEntry.COLUMN_NAME_TITLE,PlaceModelDBEntry.COLUMN_NAME_SUBTITLE,PlaceModelDBEntry.COLUMN_NAME_TEXT,PlaceModelDBEntry.COLUMN_NAME_ADDRESS,PlaceModelDBEntry.COLUMN_NAME_LOCALE	};

	
	/**
	 * @param context
	 */
	public PlaceModelDataSource(Context context) {
		if (helper == null) {
			helper = new DatabaseHelper(context);
		}
	};
	

	/**
	 * @param id
	 * @param title
	 * @param subtitle
	 * @param text
	 * @param address
	 * @param locale
	 * @return
	 */
	public long insertPlace(Integer id, String title, String subtitle,String text, String address,String locale) {

		final SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(PlaceModelDBEntry.COLUMN_NAME_ENTRY_ID, id);
		values.put(PlaceModelDBEntry.COLUMN_NAME_TITLE, title);
		values.put(PlaceModelDBEntry.COLUMN_NAME_SUBTITLE, subtitle);
		values.put(PlaceModelDBEntry.COLUMN_NAME_TEXT, text);
		values.put(PlaceModelDBEntry.COLUMN_NAME_ADDRESS, address);
		values.put(PlaceModelDBEntry.COLUMN_NAME_LOCALE, locale);

		long newRowId = db.insert(PlaceModelDBEntry.TABLE_NAME, null, values);

		Log.d(TAG, "Insert tot element in "+PlaceModelDBEntry.TABLE_NAME+ " :" + newRowId);
		db.close();
		return newRowId;

	}

	/**
	 * @return
	 */
	public List<PlaceModel> getPlaces() {
		final SQLiteDatabase db = helper.getWritableDatabase();
		final Cursor c = db.query(PlaceModelDBEntry.TABLE_NAME, ALLFIELD, null, null, null, null, PlaceModelDBEntry.COLUMN_NAME_ENTRY_ID);
		final List<PlaceModel> places = new LinkedList<PlaceModel>();

	       if (c.moveToFirst()) {
	           do {
	        	   final PlaceModel place = new PlaceModel();
	        	   place.setId(Integer.parseInt(c.getString(0)));
	        	   place.setTitle(c.getString(1));
	        	   place.setSubtitle(c.getString(2));
	        	   place.setText(c.getString(3));
	        	   place.setAddress(c.getString(4));
	        	   place.setLocale(c.getString(5));
	        	   places.add(place);
	           } while (c.moveToNext());
	       }
	       
	       Log.d(TAG,"places :" + places );
	       db.close();
		return places;

	}
	
	
	public PlaceModel getPlaceByPlaceId(Integer placeId) {

		final SQLiteDatabase db = helper.getWritableDatabase();
		final Cursor c = db.query(PlaceModelDBEntry.TABLE_NAME, ALLFIELD,
				PlaceModelDBEntry.COLUMN_NAME_ENTRY_ID+ " = ?",
				new String[] { placeId.toString() }, null, null, null);

		final PlaceModel place = new PlaceModel();

		if (c.moveToFirst()) {
        	   place.setId(Integer.parseInt(c.getString(0)));
        	   place.setTitle(c.getString(1));
        	   place.setSubtitle(c.getString(2));
        	   place.setText(c.getString(3));
        	   place.setAddress(c.getString(4));
        	   place.setLocale(c.getString(5));
		}

		Log.d(TAG, "place :" + place);
		db.close();
		return place;
	}
	
	
}
