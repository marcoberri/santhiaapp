package it.marcoberri.santhiaapp.view;

import it.marcoberri.santhiaapp.R;
import it.marcoberri.santhiaapp.adapter.PlaceListAdapter;
import it.marcoberri.santhiaapp.model.PlaceListModel;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentPlace extends Fragment {

	protected final static String TAG = FragmentPlace.class.getName();
	PlaceListModel[] list = {new PlaceListModel("Chiesa di Sant'Agata e Gioegio","Parrochia di Santhià",0, 1),new PlaceListModel("B","b",0,2),new PlaceListModel("C","c",0,3)};
	

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.i(TAG, "onCreateView()");
		View v = inflater.inflate(R.layout.fragment_place_list, container,
				false);

		final ListView listview = (ListView) v.findViewById(R.id.listViewPlace);
		
		
		


		
		 final PlaceListAdapter adapter = new PlaceListAdapter(v.getContext(), R.layout.fragment_place_list,list);
		
		 listview.setAdapter(adapter);
		
		 listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				Toast.makeText(getActivity(), "" + arg2, Toast.LENGTH_SHORT).show();
				
				
				final FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.replace(R.id.content_frame, new FragmentPlaceDetail());
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();

				
			}
		
		 
		 });
		 return v;
	}

	
}
