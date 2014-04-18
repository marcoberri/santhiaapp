package it.marcoberri.santhiaapp.view;

import java.util.List;

import it.marcoberri.santhiaapp.R;
import it.marcoberri.santhiaapp.adapter.PlaceListAdapter;
import it.marcoberri.santhiaapp.adapter.TourListAdapter;
import it.marcoberri.santhiaapp.db.model.PlaceGpsModelDataSource;
import it.marcoberri.santhiaapp.db.model.PlaceImageModelDataSource;
import it.marcoberri.santhiaapp.db.model.PlaceModelDataSource;
import it.marcoberri.santhiaapp.db.model.TourModelDataSource;
import it.marcoberri.santhiaapp.model.PlaceImageModel;
import it.marcoberri.santhiaapp.model.PlaceModel;
import it.marcoberri.santhiaapp.model.TourModel;
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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;

public class FragmentYourTourList extends Fragment implements OnScrollListener{

	protected final static String TAG = FragmentYourTourList.class.getName();

	private TourListAdapter adapter;
	private ListView listview;
	private EditText inputSearch;

	private TourModel[] tourModelArray;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final TourModelDataSource dsTour = new TourModelDataSource(getActivity().getApplicationContext());

		
		final View v = inflater.inflate(R.layout.fragment_yourtour_list, container,
				false);
		
		
		final List<TourModel> tmp_list = dsTour.getTours();
		
		tourModelArray = (TourModel[]) dsTour.getTours().toArray(
				new TourModel[tmp_list.size()]);
		Log.i(TAG, "list" + tourModelArray);


		listview = (ListView) v.findViewById(R.id.listViewTour);

		adapter = new TourListAdapter(v.getContext(),
				R.layout.fragment_yourtour_list, tourModelArray);

		listview.setOnScrollListener(this);
		listview.setAdapter(adapter);

		inputSearch = (EditText) v.findViewById(R.id.listViewTour_search);

		inputSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {

				adapter.getFilter().filter(cs);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		
		listview.setOnScrollListener(new OnScrollListener() {
		    @Override
		    public void onScrollStateChanged(AbsListView view, int scrollState) {
		    }

		    @Override
		    public void onScroll(AbsListView view, int firstVisibleItem,
		                int visibleItemCount, int totalItemCount) {
		    	Log.d(TAG, "firstVisibleItem: " + firstVisibleItem);
		    	Log.d(TAG, "visibleItemCount: " + visibleItemCount);
		    	Log.d(TAG, "totalItemCount: " + totalItemCount);
		       final int lastItem = firstVisibleItem + visibleItemCount;
		       if(lastItem == totalItemCount) {
		           //load more data
		       }
		    }
		});
		
		// listview.addFooterView(R.layout.fragment_place_list_bottom);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				final FragmentTransaction ft = getFragmentManager().beginTransaction();
				
			/*	final FragmentPlaceDetail detail = new FragmentPlaceDetail();

				final PlaceModel placeModel = placeModelArray[position];

				final PlaceImageModelDataSource dsImage = new PlaceImageModelDataSource(getActivity().getApplicationContext());
				
				final List<PlaceImageModel> placeImageModelList = dsImage.getImagesByPlaceId(placeModelArray[position].getId());
				placeModel.setImages(placeImageModelList);

				final PlaceGpsModelDataSource dsGps = new PlaceGpsModelDataSource(getActivity().getApplicationContext());
				
				placeModel.setGps(dsGps.getGpsByPlaceId(placeModel.getId()));
				
				detail.setPlaceModel(placeModel);
				ft.replace(R.id.content_frame, detail, "CENTER").addToBackStack(null);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
*/
			}

		});

		return v;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}


}
