package it.marcoberri.santhiaapp.view;

import it.marcoberri.santhiaapp.R;
import it.marcoberri.santhiaapp.adapter.PlaceListAdapter;
import it.marcoberri.santhiaapp.db.datasource.PlaceGpsModelDataSource;
import it.marcoberri.santhiaapp.db.datasource.PlaceImageModelDataSource;
import it.marcoberri.santhiaapp.db.datasource.PlaceModelDataSource;
import it.marcoberri.santhiaapp.model.PlaceImageModel;
import it.marcoberri.santhiaapp.model.PlaceModel;

import java.util.List;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnKeyListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

/**
 * @author Marco Berri - marcoberri@gmail.com
 * 
 */
public class FragmentPlaceList extends Fragment implements OnScrollListener {

    protected final static String TAG = FragmentPlaceList.class.getName();
    private PlaceListAdapter adapter;
    private ListView listview;
    private EditText inputSearch;

    private PlaceModel[] placeModelArray;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	Log.i(TAG, "onCreateView()");

	final PlaceModelDataSource dsPlace = new PlaceModelDataSource(getActivity().getApplicationContext());

	final List<PlaceModel> tmp_list = dsPlace.getPlaces();
	placeModelArray = (PlaceModel[]) dsPlace.getPlaces().toArray(new PlaceModel[tmp_list.size()]);
	Log.i(TAG, "list" + placeModelArray);
	final View v = inflater.inflate(R.layout.fragment_place_list, container, false);

	
	
	listview = (ListView) v.findViewById(R.id.listViewPlace);

	adapter = new PlaceListAdapter(v.getContext(), R.layout.fragment_place_list, placeModelArray);

	listview.setOnScrollListener(this);
	listview.setAdapter(adapter);

	inputSearch = (EditText) v.findViewById(R.id.listViewPlace_search);

	inputSearch.addTextChangedListener(new TextWatcher() {

	    @Override
	    public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

		adapter.getFilter().filter(cs);
	    }

	    @Override
	    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

	    }

	    @Override
	    public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub

	    }
	});

	// listview.addFooterView(R.layout.fragment_place_list_bottom);
	listview.setOnItemClickListener(new OnItemClickListener() {

	    @Override
	    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

		final FragmentTransaction ft = getFragmentManager().beginTransaction();

		final FragmentPlaceDetail detail = new FragmentPlaceDetail();

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

	    }

	});

	/*listview.setFocusableInTouchMode(true);
	listview.requestFocus();*/
	v.setOnKeyListener(new OnKeyListener() {

	    @Override
	    public boolean onKey(View v, int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
		    Log.i(TAG, "keyCode: " + keyCode);
		    final FragmentTransaction ft = getFragmentManager().beginTransaction();
		    ft.replace(R.id.content_frame, new FragmentHome()).addToBackStack(null);
		    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		    ft.commit();
		}
		return true;
	    }
	});

	return v;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
	// TODO Auto-generated method stub

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
	// TODO Auto-generated method stub

    }

}
