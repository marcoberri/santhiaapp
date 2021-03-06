package it.marcoberri.santhiaapp.adapter;

import it.marcoberri.santhiaapp.R;
import it.marcoberri.santhiaapp.model.LeftListModel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Marco Berri - marcoberri@gmail.com
 * 
 */

public class LeftListAdapter extends ArrayAdapter<LeftListModel> {
    private final Context context;
    private final LeftListModel[] data;

    public LeftListAdapter(Context context, int textViewResourceId, LeftListModel[] data) {
	super(context, textViewResourceId, data);
	this.context = context;
	this.data = data;
    }

    public LeftListModel getItem(int pos) {
	return this.getItem(pos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	final View rowView = inflater.inflate(R.layout.left_list_item, parent, false);

	final TextView text = (TextView) rowView.findViewById(R.id.left_list_item_text_view);

	if (data[position].getImage() != 0) {
	    final ImageView image = (ImageView) rowView.findViewById(R.id.left_list_item_image_view);
	    image.setBackgroundResource(data[position].getImage());
	}

	text.setText(data[position].getTitle());

	return rowView;
    }

}
