package it.marcoberri.santhiaapp.adapter;


import it.marcoberri.santhiaapp.R;
import it.marcoberri.santhiaapp.model.PlaceListModel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PlaceListAdapter extends ArrayAdapter<PlaceListModel> {
	  private final Context context;
	  private final PlaceListModel[] data;

	   public PlaceListAdapter(Context context,  int textViewResourceId, PlaceListModel[] data ) {
	    super(context, textViewResourceId, data);
	    this.context = context;
	    this.data = data;
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	   // View rowView = inflater.inflate(((position & 1) == 0) ? R.layout.fragment_place_list_item_left : R.layout.fragment_place_list_item_right, parent, false);
	    
	    final View rowView = inflater.inflate( R.layout.fragment_place_list_item_left, parent, false);
	    
	    
	    final TextView textViewTop = (TextView) rowView.findViewById( R.id.place_list_item_top_text_view );
	   // ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
	    
	    textViewTop.setText(data[position].getTitle());

	    final TextView textViewBottom = (TextView) rowView.findViewById( R.id.place_list_item_bottom_text_view );
	    textViewBottom.setText(data[position].getSubtitle());
  
	    // change the icon for Windows and iPhone
	    //String s = values[position];
	 
	    return rowView;
	  }
}
