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

public class ExtraModelDataSource {

    private static final String TAG = ExtraModelDataSource.class.getName();

    DatabaseHelper helper = null;

    public static abstract class ExtraModelDBEntry implements BaseColumns {
	public static final String TABLE_NAME = "extra";
	public static final String COLUMN_NAME_NAME = "name";
	public static final String COLUMN_NAME_VALUESTRING = "string_value";
	public static final String COLUMN_NAME_VALUEINT = "int_value";
	public static final String COLUMN_NAME_CATEGORY = "category";
	public static final String COLUMN_NAME_TS = "ts";
    };

    /**
	 * 
	 */
    private static final String[] ALLFIELD = { ExtraModelDBEntry.COLUMN_NAME_NAME, ExtraModelDBEntry.COLUMN_NAME_VALUESTRING, ExtraModelDBEntry.COLUMN_NAME_VALUEINT, ExtraModelDBEntry.COLUMN_NAME_CATEGORY,ExtraModelDBEntry.COLUMN_NAME_TS };

    /**
     * @param context
     */
    public ExtraModelDataSource(Context context) {
	if (helper == null) {
	    helper = new DatabaseHelper(context);
	}
    };

    /**
     * @param id
     * @param title
     * @param vote
     * @param community
     * @return
     */
    public long inserExtra(String name, String string_value, String category, int int_value) {
	final SQLiteDatabase db = helper.getWritableDatabase();
	ContentValues values = new ContentValues();
	values.put(ExtraModelDBEntry.COLUMN_NAME_NAME, name);
	values.put(ExtraModelDBEntry.COLUMN_NAME_VALUEINT, int_value);
	values.put(ExtraModelDBEntry.COLUMN_NAME_VALUESTRING, string_value);
	values.put(ExtraModelDBEntry.COLUMN_NAME_CATEGORY, category);
	long newRowId = db.insert(ExtraModelDBEntry.TABLE_NAME, null, values);
	Log.d(TAG, "Insert tot element in " + ExtraModelDBEntry.TABLE_NAME + " :" + newRowId);
	db.close();
	return newRowId;
    }

    /*
     * public List<TourModel> getTours(int community) {
     * 
     * final SQLiteDatabase db = helper.getWritableDatabase(); final Cursor c =
     * db.query(TourModelDBEntry.TABLE_NAME, ALLFIELD,
     * TourModelDBEntry.COLUMN_NAME_COMMUNITY + " = ?", new String[] {
     * (community + "") }, null, null, TourModelDBEntry.COLUMN_NAME_ENTRY_ID);
     * 
     * final List<TourModel> tours = new LinkedList<TourModel>();
     * 
     * if (c.moveToFirst()) { do { final TourModel tour = new TourModel();
     * tour.setId
     * (Integer.parseInt(c.getString(c.getColumnIndex(TourModelDBEntry.
     * COLUMN_NAME_ENTRY_ID))));
     * tour.setTitle(c.getString(c.getColumnIndex(TourModelDBEntry
     * .COLUMN_NAME_TITLE)));
     * tour.setVote(c.getInt(c.getColumnIndex(TourModelDBEntry
     * .COLUMN_NAME_VOTE)));
     * tour.setCommunity(c.getInt(c.getColumnIndex(TourModelDBEntry
     * .COLUMN_NAME_COMMUNITY))); tours.add(tour); } while (c.moveToNext()); }
     * 
     * Log.d(TAG, "tours :" + tours); db.close(); return tours;
     * 
     * }
     */
    public void deleteExtra(String name, String category) {
	final SQLiteDatabase db = helper.getWritableDatabase();
	int deleted = db.delete(ExtraModelDBEntry.TABLE_NAME, ExtraModelDBEntry.COLUMN_NAME_NAME + " = ? AND " + ExtraModelDBEntry.COLUMN_NAME_CATEGORY + " = ?", new String[] { name,category });
	Log.d(TAG, "delete entry extra :" + deleted);
	db.close();
    }
}
