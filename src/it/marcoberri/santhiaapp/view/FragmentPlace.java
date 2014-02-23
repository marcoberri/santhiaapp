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
	final static PlaceListModel[] list = {
			new PlaceListModel("Chiesa di Sant'Agata e Giorgio",
					"Parrochia di Santhià", 0, 1),
			new PlaceListModel("Chiesa Santissima Trinità", "da poco ristrutturata", 0, 2),
			new PlaceListModel("Chiesa di San Grato", "Oratorio", 0, 3),
			new PlaceListModel("Galleria di Arte Moderna", "Premio nazionale", 0, 4),
			new PlaceListModel("Ostello della via Francigena", "Ospitalità", 0, 5),
			new PlaceListModel("Il Mulino Ugliengo", "ex Consorzio agrario", 0, 6)
	};

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.i(TAG, "onCreateView()");
		View v = inflater.inflate(R.layout.fragment_place_list, container,
				false);

		final ListView listview = (ListView) v.findViewById(R.id.listViewPlace);

		final PlaceListAdapter adapter = new PlaceListAdapter(v.getContext(),
				R.layout.fragment_place_list, list);

		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				final FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				FragmentPlaceDetail detail = new FragmentPlaceDetail();
				detail.setPlaceModel(FragmentPlace.list[position]);
				ft.replace(R.id.content_frame, detail);
				
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();

			}

		});
		return v;
	}

}
