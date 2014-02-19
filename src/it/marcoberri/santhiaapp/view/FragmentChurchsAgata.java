package it.marcoberri.santhiaapp.view;


import it.marcoberri.santhiaapp.R;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class FragmentChurchsAgata extends Fragment {

	protected final static String TAG = FragmentChurchsAgata.class.getName();

	private TabHost mTabHost;
    
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		
		Log.i(TAG, "onCreateView()");
		View v = inflater.inflate(R.layout.fragment_churchs_agata, container,
				false);

		mTabHost = (TabHost) v.findViewById(android.R.id.tabhost);
		setupTabs();
		return v;
	}

	private void setupTabs() {

		mTabHost.setup(); // you must call this before adding your tabs!

		final TabSpec tabSpec1 = mTabHost.newTabSpec("Story");
		tabSpec1.setContent(R.id.tab_1);
		tabSpec1.setIndicator("Story");

		final TabSpec tabSpec2 = mTabHost.newTabSpec("Near");
		tabSpec2.setContent(R.id.tab_2);
		tabSpec2.setIndicator("Nei Dintorini");
		
		final TabSpec tabSpec3 = mTabHost.newTabSpec("Map");
		tabSpec3.setContent(R.id.tab_3);
		tabSpec3.setIndicator("Map");


	    
		mTabHost.addTab(tabSpec1);
		mTabHost.addTab(tabSpec2);
		mTabHost.addTab(tabSpec3);
		

		mTabHost.getTabWidget().getChildAt(0).getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.tab_height);
		mTabHost.getTabWidget().getChildAt(1).getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.tab_height);
		mTabHost.getTabWidget().getChildAt(2).getLayoutParams().height = 30;

	}

}
