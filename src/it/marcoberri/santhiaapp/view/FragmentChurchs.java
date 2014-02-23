package it.marcoberri.santhiaapp.view;

import it.marcoberri.santhiaapp.R;
import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class FragmentChurchs extends Fragment {

	protected final static String TAG = FragmentChurchs.class.getName();

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.i(TAG, "onCreateView()");
		View v = inflater.inflate(R.layout.fragment_tour, container, false);

		Button b = (Button) v.findViewById(R.id.button_select_place);

		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String names[] ={"A","B","C","D"};
		        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
		       
		        View convertView = (View) View.inflate(alertDialog.getContext(), R.layout.tour_dialog_list,null);
		        alertDialog.setView(convertView);
		        alertDialog.setTitle("List");
		        final ListView lv = (ListView) convertView.findViewById(R.id.tour_place_livstview);
		        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(alertDialog.getContext(),android.R.layout.simple_list_item_1,names);
		        lv.setAdapter(adapter);
		        alertDialog.show();
			}
		});

		return v;
	}

}
