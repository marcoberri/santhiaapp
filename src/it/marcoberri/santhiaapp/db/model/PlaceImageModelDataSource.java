package it.marcoberri.santhiaapp.db.model;

import java.util.LinkedList;
import java.util.List;

import it.marcoberri.santhiaapp.db.helper.DatabaseHelper;
import it.marcoberri.santhiaapp.model.PlaceImageModel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

public class PlaceImageModelDataSource {

	private static final String TAG = PlaceImageModelDataSource.class.getName();
	
	private static final String[] ALLFIELD = {PlaceImageModelDBEntry.COLUMN_NAME_ENTRY_ID,PlaceImageModelDBEntry.COLUMN_NAME_PLACE_ID, PlaceImageModelDBEntry.COLUMN_NAME_URL, PlaceImageModelDBEntry.COLUMN_NAME_DISCLAMER, PlaceImageModelDBEntry.COLUMN_NAME_TEXT, PlaceImageModelDBEntry.COLUMN_NAME_TITLE};
	
	DatabaseHelper helper = null;

	public static abstract class PlaceImageModelDBEntry implements BaseColumns {
		public static final String TABLE_NAME = "images";
		public static final String COLUMN_NAME_ENTRY_ID = "id";
		public static final String COLUMN_NAME_PLACE_ID = "place_id";
		public static final String COLUMN_NAME_URL = "url";
		public static final String COLUMN_NAME_DISCLAMER = "disclamer";
		public static final String COLUMN_NAME_TEXT = "text";
		public static final String COLUMN_NAME_TITLE = "title";
	};

	

	public PlaceImageModelDataSource(Context context) {
		if (helper == null) {
			helper = new DatabaseHelper(context);
		}
	};
	

	public long insertPlaceImage(Integer id, Integer place_id, String url, String disclamer, String title, String text) {

		final SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(PlaceImageModelDBEntry.COLUMN_NAME_ENTRY_ID, id);
		values.put(PlaceImageModelDBEntry.COLUMN_NAME_PLACE_ID, place_id);
		values.put(PlaceImageModelDBEntry.COLUMN_NAME_TITLE, title);
		values.put(PlaceImageModelDBEntry.COLUMN_NAME_TEXT, text);
		values.put(PlaceImageModelDBEntry.COLUMN_NAME_URL, url);
		values.put(PlaceImageModelDBEntry.COLUMN_NAME_DISCLAMER, disclamer);

		long newRowId = db.insert(PlaceImageModelDBEntry.TABLE_NAME, null, values);

		Log.d(TAG, "Insert tot element in "+PlaceImageModelDBEntry.TABLE_NAME+ " :" + newRowId);
		return newRowId;

	}

	
	public List<PlaceImageModel> getImagesByPlaceId(Integer placeId) {

		final SQLiteDatabase db = helper.getWritableDatabase();
		final Cursor c = db.query(PlaceImageModelDBEntry.TABLE_NAME, ALLFIELD, PlaceImageModelDBEntry.COLUMN_NAME_PLACE_ID + " = ?", new String[]{placeId.toString()}, null, null, PlaceImageModelDBEntry.COLUMN_NAME_ENTRY_ID);

		
		final List<PlaceImageModel> images = new LinkedList<PlaceImageModel>();

	       if (c.moveToFirst()) {
	           do {
	        	   final PlaceImageModel image = new PlaceImageModel();
	        	   image.setId(c.getInt(c.getColumnIndex(PlaceImageModelDBEntry.COLUMN_NAME_ENTRY_ID)));
	        	   image.setDisclamer(c.getString(c.getColumnIndex(PlaceImageModelDBEntry.COLUMN_NAME_DISCLAMER)));
	        	   image.setPlaceId(c.getInt(c.getColumnIndex(PlaceImageModelDBEntry.COLUMN_NAME_PLACE_ID)));
	        	   image.setUrl(c.getString(c.getColumnIndex(PlaceImageModelDBEntry.COLUMN_NAME_URL)));
	        	   image.setTitle(c.getString(c.getColumnIndex(PlaceImageModelDBEntry.COLUMN_NAME_TITLE)));
	        	   image.setText(c.getString(c.getColumnIndex(PlaceImageModelDBEntry.COLUMN_NAME_TEXT)));
	        	   images.add(image);
	           } while (c.moveToNext());
	       }
	       
	       Log.d(TAG,"images :" + images );
	       
		return images;
	}
	
}
