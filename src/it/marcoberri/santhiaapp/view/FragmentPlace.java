package it.marcoberri.santhiaapp.view;

import it.marcoberri.santhiaapp.R;
import it.marcoberri.santhiaapp.adapter.PlaceListAdapter;
import it.marcoberri.santhiaapp.model.PlaceListModel;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentPlace extends Fragment {

	protected final static String TAG = FragmentPlace.class.getName();

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.i(TAG, "onCreateView()");
		View v = inflater.inflate(R.layout.fragment_place_list, container,
				false);

		final ListView listview = (ListView) v.findViewById(R.id.listViewPlace);
		
		
		
		//String[] list = getResources().getStringArray(R.array.placelist); 
		PlaceListModel[] list = {new PlaceListModel("A","a",0),new PlaceListModel("B","b",0),new PlaceListModel("C","c",0)};
	
		
		 final PlaceListAdapter adapter = new PlaceListAdapter(v.getContext(), R.layout.fragment_place_list,list);
		
		 listview.setAdapter(adapter);
		return v;
	}

	
}
