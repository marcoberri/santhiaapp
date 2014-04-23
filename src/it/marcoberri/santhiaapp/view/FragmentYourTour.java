package it.marcoberri.santhiaapp.view;

import java.util.List;

import it.marcoberri.santhiaapp.R;
import it.marcoberri.santhiaapp.adapter.TourListAdapter;
import it.marcoberri.santhiaapp.db.model.TourModelDataSource;
import it.marcoberri.santhiaapp.model.TourModel;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class FragmentYourTour extends Fragment implements OnScrollListener {

	protected final static String TAG = FragmentYourTour.class.getName();

	private TourModel[] tourModelArray;
	private TourListAdapter adapter;
	private EditText inputSearchCommunity;

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		final View view = inflater.inflate(R.layout.fragment_yourtour, container,
				false);
		
		final TabHost mTabHost = (TabHost) view
				.findViewById(android.R.id.tabhost);
		setupTabs(mTabHost);
		
		final ListView listviewCommunity = (ListView) view.findViewById(R.id.listViewTourCommunity);
		final TourModelDataSource dsTour = new TourModelDataSource(getActivity().getApplicationContext());
		final List<TourModel> tmp_list = dsTour.getCommunityTours();
		tourModelArray = (TourModel[]) dsTour.getCommunityTours().toArray(
				new TourModel[tmp_list.size()]);
		Log.i(TAG, "list" + tourModelArray);

		adapter = new TourListAdapter(view.getContext(),
				R.layout.fragment_yourtour_list_item_left, tourModelArray);


		listviewCommunity.setOnScrollListener(this);
		listviewCommunity.setAdapter(adapter);
		
		inputSearchCommunity = (EditText) view.findViewById(R.id.listViewTourCommunity_search);
		
		
		final ListView listviewYour = (ListView) view.findViewById(R.id.listViewTour);
		listviewYour.setOnScrollListener(this);
		listviewYour.setAdapter(adapter);
		
	//	final Button btnAddMore = new Button(this.getActivity().getApplicationContext());
//		btnAddMore.setText("Add new");
		
	//	listviewYour.addFooterView(btnAddMore);
		return view;
	}

	private void setupTabs(TabHost mTabHost) {

		mTabHost.setup();
		final TabSpec tabSpec1 = mTabHost.newTabSpec("Your");
		tabSpec1.setContent(R.id.tab_yours);
		tabSpec1.setIndicator("Your");

		final TabSpec tabSpec2 = mTabHost.newTabSpec("Community");
		tabSpec2.setContent(R.id.tab_community);
		tabSpec2.setIndicator("Community");
		
		mTabHost.addTab(tabSpec1);
		mTabHost.addTab(tabSpec2);

		mTabHost.getTabWidget().getChildAt(0).getLayoutParams().height = getResources()
				.getDimensionPixelSize(R.dimen.tab_height);
		mTabHost.getTabWidget().getChildAt(1).getLayoutParams().height = getResources()
				.getDimensionPixelSize(R.dimen.tab_height);

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
	}


}
