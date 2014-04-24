package it.marcoberri.santhiaapp.adapter;

import it.marcoberri.santhiaapp.R;
import it.marcoberri.santhiaapp.model.TourModel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

/**
 * @author Marco Berri - marcoberri@gmail.com
 * 
 */
public class TourListAdapter extends ArrayAdapter<TourModel> {

    private final Context context;
    private final TourModel[] data;

    /**
     * @param context
     * @param textViewResourceId
     * @param data
     */
    public TourListAdapter(Context context, int textViewResourceId, TourModel[] data) {
	super(context, textViewResourceId, data);
	this.context = context;
	this.data = data;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.widget.ArrayAdapter#getItem(int)
     */
    public TourModel getItem(int pos) {
	return this.getItem(pos);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.widget.ArrayAdapter#getView(int, android.view.View,
     * android.view.ViewGroup)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	final View rowView = inflater.inflate(R.layout.fragment_yourtour_list_item_left, parent, false);

	final TextView textViewTop = (TextView) rowView.findViewById(R.id.tour_list_item_top_text_view);

	textViewTop.setText(data[position].getTitle());

	return rowView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.widget.ArrayAdapter#getFilter()
     */
    @Override
    public Filter getFilter() {
	return super.getFilter();
    }
}
