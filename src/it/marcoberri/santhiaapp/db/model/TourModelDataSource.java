package it.marcoberri.santhiaapp.db.model;

import java.util.LinkedList;
import java.util.List;

import it.marcoberri.santhiaapp.db.helper.DatabaseHelper;
import it.marcoberri.santhiaapp.model.TourModel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

public class TourModelDataSource {

	private static final String TAG = TourModelDataSource.class.getName();
	

	DatabaseHelper helper = null;

	/* Inner class that defines the table contents */
	public static abstract class TourModelDBEntry implements BaseColumns {
		public static final String TABLE_NAME = "tour";
		public static final String COLUMN_NAME_ENTRY_ID = "id";
		public static final String COLUMN_NAME_TITLE = "title";
		public static final String COLUMN_NAME_VOTE = "vote";
	};

	private static final String[] ALLFIELD = {TourModelDBEntry.COLUMN_NAME_ENTRY_ID,TourModelDBEntry.COLUMN_NAME_TITLE,TourModelDBEntry.COLUMN_NAME_VOTE};

	
	public TourModelDataSource(Context context) {
		if (helper == null) {
			helper = new DatabaseHelper(context);
		}
	};
	

	public long insertTour(Integer id, String title, int vote) {

		final SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TourModelDBEntry.COLUMN_NAME_ENTRY_ID, id);
		values.put(TourModelDBEntry.COLUMN_NAME_TITLE, title);
		values.put(TourModelDBEntry.COLUMN_NAME_VOTE, vote);

		long newRowId = db.insert(TourModelDBEntry.TABLE_NAME, null, values);

		Log.d(TAG, "Insert tot element in "+TourModelDBEntry.TABLE_NAME+ " :" + newRowId);
		db.close();
		return newRowId;

	}

	public List<TourModel> getTours() {

		final SQLiteDatabase db = helper.getWritableDatabase();
		final Cursor c = db.query(TourModelDBEntry.TABLE_NAME, ALLFIELD, null, null, null, null, TourModelDBEntry.COLUMN_NAME_ENTRY_ID);

		
		final List<TourModel> tours = new LinkedList<TourModel>();

	       if (c.moveToFirst()) {
	           do {
	        	   final TourModel tour = new TourModel();
	        	   tour.setId(Integer.parseInt(c.getString(0)));
	        	   tour.setTitle(c.getString(1));
	        	   tour.setVote(c.getInt(2));
	        	   tours.add(tour);
	           } while (c.moveToNext());
	       }
	       
	       Log.d(TAG,"tours :" + tours );
	       db.close();
		return tours;

	}
}
