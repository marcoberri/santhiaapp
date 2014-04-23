package it.marcoberri.santhiaapp.db.datasource;

import it.marcoberri.santhiaapp.db.helper.DatabaseHelper;
import it.marcoberri.santhiaapp.model.TourModel;

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

public class TourModelDataSource {

	private static final String TAG = TourModelDataSource.class.getName();

	DatabaseHelper helper = null;

	/**
	 * @author berri
	 * 
	 */
	public static abstract class TourModelDBEntry implements BaseColumns {
		public static final String TABLE_NAME = "tour";
		public static final String COLUMN_NAME_ENTRY_ID = "id";
		public static final String COLUMN_NAME_TITLE = "title";
		public static final String COLUMN_NAME_VOTE = "vote";
		public static final String COLUMN_NAME_COMMUNITY = "community";
	};

	/**
	 * 
	 */
	private static final String[] ALLFIELD = {
			TourModelDBEntry.COLUMN_NAME_ENTRY_ID,
			TourModelDBEntry.COLUMN_NAME_TITLE,
			TourModelDBEntry.COLUMN_NAME_VOTE,
			TourModelDBEntry.COLUMN_NAME_COMMUNITY };

	/**
	 * @param context
	 */
	public TourModelDataSource(Context context) {
		if (helper == null) {
			helper = new DatabaseHelper(context);
		}
	};

	/**
	 * @param id
	 * @param title
	 * @param vote
	 * @return
	 */
	public long insertCommunityTour(Integer id, String title, int vote) {
		return insertTour(id, title, vote, 1);
	}

	/**
	 * @param title
	 * @return
	 */
	public long insertCommunityTour(String title) {
		return insertTour(null, title, 0, 1);
	}

	/**
	 * @param id
	 * @param title
	 * @param vote
	 * @return
	 */
	public long insertYouserTour(Integer id, String title, int vote) {
		return insertTour(id, title, vote, 0);
	}

	/**
	 * @param id
	 * @param title
	 * @return
	 */
	public long insertYouserTour(Integer id, String title) {
		return insertTour(id, title, 0, 0);
	}

	/**
	 * @param title
	 * @return
	 */
	public long insertYouserTour(String title) {
		return insertTour(null, title, 0, 0);
	}

	/**
	 * @param id
	 * @param title
	 * @param vote
	 * @param community
	 * @return
	 */
	public long insertTour(Integer id, String title, int vote, int community) {

		final SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		if (id != null) {
			values.put(TourModelDBEntry.COLUMN_NAME_ENTRY_ID, id);
		}
		values.put(TourModelDBEntry.COLUMN_NAME_TITLE, title);
		values.put(TourModelDBEntry.COLUMN_NAME_VOTE, vote);
		values.put(TourModelDBEntry.COLUMN_NAME_COMMUNITY, community);

		long newRowId = db.insert(TourModelDBEntry.TABLE_NAME, null, values);

		Log.d(TAG, "Insert tot element in " + TourModelDBEntry.TABLE_NAME
				+ " :" + newRowId);
		db.close();
		return newRowId;

	}

	/**
	 * @return
	 */
	public List<TourModel> getCommunityTours() {
		return getTours(1);
	}

	/**
	 * @return
	 */
	public List<TourModel> getYourTours() {
		return getTours(0);
	}

	/**
	 * @param community
	 * @return
	 */
	public List<TourModel> getTours(int community) {

		final SQLiteDatabase db = helper.getWritableDatabase();
		final Cursor c = db.query(TourModelDBEntry.TABLE_NAME, ALLFIELD,
				TourModelDBEntry.COLUMN_NAME_COMMUNITY + " = ?",
				new String[] { (community + "") }, null, null,
				TourModelDBEntry.COLUMN_NAME_ENTRY_ID);

		final List<TourModel> tours = new LinkedList<TourModel>();

		if (c.moveToFirst()) {
			do {
				final TourModel tour = new TourModel();
				tour.setId(Integer.parseInt(c.getString(c
						.getColumnIndex(TourModelDBEntry.COLUMN_NAME_ENTRY_ID))));
				tour.setTitle(c.getString(c
						.getColumnIndex(TourModelDBEntry.COLUMN_NAME_TITLE)));
				tour.setVote(c.getInt(c
						.getColumnIndex(TourModelDBEntry.COLUMN_NAME_VOTE)));
				tour.setCommunity(c.getInt(c
						.getColumnIndex(TourModelDBEntry.COLUMN_NAME_COMMUNITY)));
				tours.add(tour);
			} while (c.moveToNext());
		}

		Log.d(TAG, "tours :" + tours);
		db.close();
		return tours;

	}
}
