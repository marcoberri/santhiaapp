package it.marcoberri.santhiaapp.view;

import it.marcoberri.santhiaapp.R;
import it.marcoberri.santhiaapp.adapter.PlaceListAdapter;
import it.marcoberri.santhiaapp.model.PlaceModel;
import it.marcoberri.santhiaapp.task.LoadDataUrlTask;
import it.marcoberri.santhiaapp.wrapper.PlacesWrapper;

import java.util.concurrent.ExecutionException;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
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

import com.google.gson.Gson;

public class FragmentPlace extends Fragment implements OnScrollListener {

	protected final static String TAG = FragmentPlace.class.getName();
	private PlaceListAdapter adapter;
	private ListView listview;
	private EditText inputSearch;

	final static PlaceModel[] list = /*getData();*/{
		
			new PlaceModel("Chiesa di Sant'Agata e Giorgio","Parrochia di Santhi�", R.drawable.ic_church_item, 1),
			new PlaceModel("Chiesa Santissima Trinit�",	"da poco ristrutturata", 0, 2),
			
			new PlaceModel("Chiesa di San Grato", "Oratorio",  R.drawable.ic_church_item, 3),
			new PlaceModel("Galleria di Arte Moderna", "Premio nazionale",
					R.drawable.ic_galley_item, 4),
			new PlaceModel("Ostello della via Francigena", "Ospitalitaa", 0,
					5),
			new PlaceModel("Il Mulino Ugliengo", "ex Consorzio agrario", R.drawable.ic_windmill_item,
					6),
			new PlaceModel("Il Mulino Ugliengo", "ex Consorzio agrario", R.drawable.ic_windmill_item,
					7),
			new PlaceModel("Il Mulino Ugliengo", "ex Consorzio agrario", R.drawable.ic_windmill_item,
					8),
			new PlaceModel("Il Mulino Ugliengo", "ex Consorzio agrario", R.drawable.ic_windmill_item,
					9),
			new PlaceModel("Il Mulino Ugliengo", "ex Consorzio agrario", R.drawable.ic_windmill_item,
					10),
			new PlaceModel("Il Mulino Ugliengo", "ex Consorzio agrario", R.drawable.ic_windmill_item,
					11),
			new PlaceModel("Il Mulino Ugliengo", "ex Consorzio agrario", R.drawable.ic_windmill_item,
					12),
			new PlaceModel("Il Mulino Ugliengo", "ex Consorzio agrario", R.drawable.ic_windmill_item,
					13),
			new PlaceModel("Il Mulino Ugliengo", "ex Consorzio agrario", R.drawable.ic_windmill_item,
					14),
			new PlaceModel("Il Mulino Ugliengo", "ex Consorzio agrario", R.drawable.ic_windmill_item,
					15),
			new PlaceModel("Il Mulino Ugliengo", "ex Consorzio agrario", R.drawable.ic_windmill_item,
					16),
			new PlaceModel("Il Mulino Ugliengo", "ex Consorzio agrario", R.drawable.ic_windmill_item,
					17),
			new PlaceModel("Il Mulino Ugliengo", "ex Consorzio agrario", R.drawable.ic_windmill_item,
					18),
			new PlaceModel("Il Mulino Ugliengo", "ex Consorzio agrario", R.drawable.ic_windmill_item,
					19),
			new PlaceModel("Il Mulino Ugliengo", "ex Consorzio agrario", R.drawable.ic_windmill_item,
					20),
			new PlaceModel("Il Mulino Ugliengo", "ex Consorzio agrario", R.drawable.ic_windmill_item,
					21)

	};

	/*public static PlaceModel[] getData() {
		
		LoadDataUrlTask load = new LoadDataUrlTask();
		load.execute("http://www.marcoberri.it/santhiaapp/places.json");
		
		try {
			String  result = load.get();
			//Toast.makeText(this, result, Toast.LENGTH_LONG).show();
			 while(load.getStatus() == AsyncTask.Status.FINISHED){
					Log.d(TAG, "Loading data");
					
			 }
			 
			PlacesWrapper placesWrapper = new Gson().fromJson(result, PlacesWrapper.class);
			Log.d(TAG, "PlaceWrapper " + placesWrapper);
			
			return placesWrapper.getPlacesArray();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	*/
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
				
				final FragmentPlaceDetail detail = new FragmentPlaceDetail();
				detail.setPlaceModel(FragmentPlace.list[position]);
				ft.replace(R.id.content_frame, detail,"CENTER").addToBackStack(null);
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
