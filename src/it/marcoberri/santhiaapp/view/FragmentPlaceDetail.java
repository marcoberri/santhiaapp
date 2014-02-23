package it.marcoberri.santhiaapp.view;


import it.marcoberri.santhiaapp.R;
import it.marcoberri.santhiaapp.model.PlaceListModel;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class FragmentPlaceDetail extends Fragment {

	protected final static String TAG = FragmentPlaceDetail.class.getName();

	
	private int position;
	private PlaceListModel placeListModel;
    
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		Log.i(TAG, "onCreateView()");
		final View v = inflater.inflate(R.layout.fragment_place_detail, container,
				false);

		final TextView title = (TextView)v.findViewById(R.id.place_detail_title);
		title.setText(placeListModel.getTitle());
		
		TabHost mTabHost = (TabHost) v.findViewById(android.R.id.tabhost);
		setupTabs(mTabHost);
		return v;
	}

	private void setupTabs(TabHost mTabHost) {

		mTabHost.setup(); 
		final TabSpec tabSpec1 = mTabHost.newTabSpec("Story");
		tabSpec1.setContent(R.id.tab_1);
		tabSpec1.setIndicator("Storia");

		final TabSpec tabSpec2 = mTabHost.newTabSpec("Near");
		tabSpec2.setContent(R.id.tab_2);
		tabSpec2.setIndicator("Foto");
		
		final TabSpec tabSpec3 = mTabHost.newTabSpec("Map");
		tabSpec3.setContent(R.id.tab_3);
		tabSpec3.setIndicator("Map");


	    
		mTabHost.addTab(tabSpec1);
		mTabHost.addTab(tabSpec2);
		mTabHost.addTab(tabSpec3);
		

		mTabHost.getTabWidget().getChildAt(0).getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.tab_height);
		mTabHost.getTabWidget().getChildAt(1).getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.tab_height);
		mTabHost.getTabWidget().getChildAt(2).getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.tab_height);

	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setPlaceModel(PlaceListModel placeListModel) {
		this.placeListModel=placeListModel;
		
	}

}