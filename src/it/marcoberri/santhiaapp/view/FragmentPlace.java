package it.marcoberri.santhiaapp.view;

import it.marcoberri.santhiaapp.R;
import it.marcoberri.santhiaapp.adapter.PlaceListAdapter;
import it.marcoberri.santhiaapp.model.PlaceListModel;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

public class FragmentPlace extends Fragment implements OnScrollListener {

	protected final static String TAG = FragmentPlace.class.getName();
	private PlaceListAdapter adapter;
	private ListView listview;
	private EditText inputSearch;

	final static PlaceListModel[] list = {
			new PlaceListModel("Chiesa di Sant'Agata e Giorgio",
					"Parrochia di Santhi�", 0, 1),
			new PlaceListModel("Chiesa Santissima Trinit�",
					"da poco ristrutturata", 0, 2),
			new PlaceListModel("Chiesa di San Grato", "Oratorio", 0, 3),
			new PlaceListModel("Galleria di Arte Moderna", "Premio nazionale",
					0, 4),
			new PlaceListModel("Ostello della via Francigena", "Ospitalitaa", 0,
					5),
			new PlaceListModel("Il Mulino Ugliengo", "ex Consorzio agrario", 0,
					6),
			new PlaceListModel("Il Mulino Ugliengo", "ex Consorzio agrario", 0,
					7),
			new PlaceListModel("Il Mulino Ugliengo", "ex Consorzio agrario", 0,
					8),
			new PlaceListModel("Il Mulino Ugliengo", "ex Consorzio agrario", 0,
					9),
			new PlaceListModel("Il Mulino Ugliengo", "ex Consorzio agrario", 0,
					10),
			new PlaceListModel("Il Mulino Ugliengo", "ex Consorzio agrario", 0,
					11),
			new PlaceListModel("Il Mulino Ugliengo", "ex Consorzio agrario", 0,
					12),
			new PlaceListModel("Il Mulino Ugliengo", "ex Consorzio agrario", 0,
					13),
			new PlaceListModel("Il Mulino Ugliengo", "ex Consorzio agrario", 0,
					14),
			new PlaceListModel("Il Mulino Ugliengo", "ex Consorzio agrario", 0,
					15),
			new PlaceListModel("Il Mulino Ugliengo", "ex Consorzio agrario", 0,
					16),
			new PlaceListModel("Il Mulino Ugliengo", "ex Consorzio agrario", 0,
					17),
			new PlaceListModel("Il Mulino Ugliengo", "ex Consorzio agrario", 0,
					18),
			new PlaceListModel("Il Mulino Ugliengo", "ex Consorzio agrario", 0,
					19),
			new PlaceListModel("Il Mulino Ugliengo", "ex Consorzio agrario", 0,
					20),
			new PlaceListModel("Il Mulino Ugliengo", "ex Consorzio agrario", 0,
					21)

	};

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.i(TAG, "onCreateView()");
		View v = inflater.inflate(R.layout.fragment_place_list, container,
				false);

		listview = (ListView) v.findViewById(R.id.listViewPlace);

		adapter = new PlaceListAdapter(v.getContext(),
				R.layout.fragment_place_list, list);

		listview.setOnScrollListener(this);
		listview.setAdapter(adapter);

		inputSearch = (EditText) v.findViewById(R.id.listViewPlace_search);

		inputSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				adapter.getFilter().filter(cs);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		// listview.addFooterView(R.layout.fragment_place_list_bottom);
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

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		/*
		 * boolean loadMore = (firstVisibleItem + visibleItemCount <=
		 * totalItemCount);
		 * 
		 * if (loadMore) { adapter.setCount(adapter.getCount() +
		 * visibleItemCount); adapter.notifyDataSetChanged(); }
		 */
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

	}

}
