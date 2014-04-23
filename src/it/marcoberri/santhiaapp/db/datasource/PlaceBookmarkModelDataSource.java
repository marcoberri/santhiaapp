package it.marcoberri.santhiaapp.db.datasource;

import it.marcoberri.santhiaapp.db.helper.DatabaseHelper;
import it.marcoberri.santhiaapp.model.PlaceBookmarkModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class PlaceBookmarkModelDataSource {

	private static final String TAG = PlaceBookmarkModelDataSource.class
			.getName();

	private static final String[] ALLFIELD = {
		PlaceBookmarkModelDBEntry.COLUMN_NAME_PLACE_ID,
		PlaceBookmarkModelDBEntry.COLUMN_NAME_TS };

	private static final String FORMAT_DATE = "yyyy-MM-dd HH:mm:ss.sss";

	DatabaseHelper helper = null;

	public static abstract class PlaceBookmarkModelDBEntry implements
			BaseColumns {
		public static final String TABLE_NAME = "bookmark";
		public static final String COLUMN_NAME_PLACE_ID = "place_id";
		public static final String COLUMN_NAME_TS = "ts";
	};

	public PlaceBookmarkModelDataSource(Context context) {
		if (helper == null) {
			helper = new DatabaseHelper(context);
		}
	};

	public long insertPlaceBookmark(Integer place_id) {

		final SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(PlaceBookmarkModelDBEntry.COLUMN_NAME_PLACE_ID, place_id);
		values.put(PlaceBookmarkModelDBEntry.COLUMN_NAME_TS,
				new SimpleDateFormat(FORMAT_DATE).format(new Date()));

		long newRowId = db.insert(PlaceBookmarkModelDBEntry.TABLE_NAME, null,
				values);

		Log.d(TAG, "Insert tot element in "
				+ PlaceBookmarkModelDBEntry.TABLE_NAME + " :" + newRowId);
		db.close();
		return newRowId;

	}

	public PlaceBookmarkModel getPlaceBookmarkByPlaceId(Integer placeId) {

		final SQLiteDatabase db = helper.getWritableDatabase();
		final Cursor c = db.query(PlaceBookmarkModelDBEntry.TABLE_NAME,
				ALLFIELD, PlaceBookmarkModelDBEntry.COLUMN_NAME_PLACE_ID
						+ " = ?", new String[] { placeId.toString() }, null,
				null, PlaceBookmarkModelDBEntry.COLUMN_NAME_TS);
		
		final PlaceBookmarkModel bookmark = new PlaceBookmarkModel();

		if (c.moveToFirst()) {

			bookmark.setPlaceId(c.getInt(c
					.getColumnIndex(PlaceBookmarkModelDBEntry.COLUMN_NAME_PLACE_ID)));
			try {
				bookmark.setTs(new SimpleDateFormat(FORMAT_DATE).parse(c.getString(c
						.getColumnIndex(PlaceBookmarkModelDBEntry.COLUMN_NAME_TS))));
			} catch (final ParseException e) {
				Log.e(TAG, e.getMessage());
			}
		}

		Log.d(TAG, "bookmark :" + bookmark);
		db.close();
		return bookmark;
	}

	

	public int deletePlaceBookmarkByPlaceId(Integer placeId) {

		final SQLiteDatabase db = helper.getWritableDatabase();
		int tot_delete = db.delete(PlaceBookmarkModelDBEntry.TABLE_NAME, PlaceBookmarkModelDBEntry.COLUMN_NAME_PLACE_ID
						+ " = ?", new String[] { placeId.toString() });
		Log.d(TAG, "bookmark deleted:" + tot_delete);
		db.close();
		return tot_delete;
	}
	
	
}
