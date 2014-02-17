package it.marcoberri.santhiaapp.adapter;

import it.marcoberri.santhiaapp.R;
import it.marcoberri.santhiaapp.model.LeftItemLayoutModel;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class LeftItemLayoutAdapter extends ArrayAdapter<LeftItemLayoutModel> {


    Context context;

    public LeftItemLayoutAdapter(Context context, int textViewResourceId, List<LeftItemLayoutModel> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;

	     LayoutInflater mInflater = (LayoutInflater) context
	                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			
			view = mInflater.inflate(R.layout.fragment_left_item, parent, false);
		} else {
			view = convertView;
		}

		LeftItemLayoutModel item = getItem(position);

		/*((TextView) view.findViewById(R.id.item_left_label)).setText(item
				.getName());
		((TextView) view.findViewById(R.id.item_left_label)).setText(item
				.getId());
*/
		return view;
	}

}
