package it.marcoberri.santhiaapp.adapter;

import it.marcoberri.santhiaapp.R;
import it.marcoberri.santhiaapp.model.PlaceListModel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PlaceListAdapter extends ArrayAdapter<PlaceListModel>  {
	private final Context context;
	private final PlaceListModel[] data;
	//private int count = 5;

	public PlaceListAdapter(Context context, int textViewResourceId,
			PlaceListModel[] data) {
		super(context, textViewResourceId, data);
		this.context = context;
		this.data = data;
	}

	/*public int getCount() {
		return count;
	}
*/
	public PlaceListModel getItem(int pos) {
		return this.getItem(pos);
	}
/*
	public long getItemId(int pos) {
		return pos;
	}
*/
	
	
	 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		final View rowView = inflater.inflate(
				R.layout.fragment_place_list_item_left, parent, false);

		final TextView textViewTop = (TextView) rowView
				.findViewById(R.id.place_list_item_top_text_view);

		textViewTop.setText(data[position].getTitle());

		final TextView textViewBottom = (TextView) rowView
				.findViewById(R.id.place_list_item_bottom_text_view);
		textViewBottom.setText(data[position].getSubtitle());

		return rowView;
	}

	/*public void setCount(int count) {
		this.count = count;
	}*/


	 
}
