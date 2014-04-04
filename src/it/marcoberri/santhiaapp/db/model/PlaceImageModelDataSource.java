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

public class PlaceImageModelDataSource {

	private static final String TAG = PlaceImageModelDataSource.class.getName();
	
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
		values.put(PlaceImageModelDBEntry.COLUMN_NAME_ENTRY_ID, place_id);
		values.put(PlaceImageModelDBEntry.COLUMN_NAME_TITLE, title);
		values.put(PlaceImageModelDBEntry.COLUMN_NAME_TEXT, text);
		values.put(PlaceImageModelDBEntry.COLUMN_NAME_URL, url);
		values.put(PlaceImageModelDBEntry.COLUMN_NAME_DISCLAMER, disclamer);

		long newRowId = db.insert(PlaceImageModelDBEntry.TABLE_NAME, null, values);

		Log.d(TAG, "Insert tot element in "+PlaceImageModelDBEntry.TABLE_NAME+ " :" + newRowId);
		return newRowId;

	}

	
}
