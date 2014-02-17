package it.marcoberri.santhiaapp.view;

import it.marcoberri.santhiaapp.FragmentCenter;
import it.marcoberri.santhiaapp.R;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class FragmentChurchsAgata extends FragmentCenter {

	protected final static String TAG = FragmentChurchsAgata.class.getName();

	private TabHost mTabHost;
	
	public static FragmentChurchsAgata newInstance(int index) {
		Log.i(TAG, "newInstance");
		FragmentChurchsAgata f = new FragmentChurchsAgata();
		Bundle args = new Bundle();
		args.putInt("index", index);
		f.setArguments(args);
		return f;	
	}

	
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
	//	mTabHost.addTab(newTab("A", /*"A",*/ R.id.tab_1));
	//	mTabHost.addTab(newTab("B", /*"B",*/ R.id.tab_2));
	}

/*	private TabSpec newTab(String tag, int labelId, int tabContentId) {
		Log.d(TAG, "buildTab(): tag=" + tag);

		View indicator = LayoutInflater.from(getActivity()).inflate(
				R.layout.tab,
				(ViewGroup) mRoot.findViewById(android.R.id.tabs), false);
		//((TextView) indicator.findViewById(R.id.text)).setText(labelId);

		TabSpec tabSpec = mTabHost.newTabSpec(tag);
	//	tabSpec.setIndicator(indicator);
		tabSpec.setContent(tabContentId);
		return tabSpec;
	}
*/
}
