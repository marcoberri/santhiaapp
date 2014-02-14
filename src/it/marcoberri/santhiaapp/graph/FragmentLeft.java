package it.marcoberri.santhiaapp.graph;

import it.marcoberri.santhiaapp.R;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentLeft extends ListFragment {

	private final static String TAG = FragmentLeft.class.getName();

	FragmentHome fragmentHome;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.v(TAG, "onCreateView");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				inflater.getContext(), R.layout.frame_left_item, getResources()
						.getStringArray(R.array.left_menu));
		setListAdapter(adapter);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Log.v(TAG, "onListItemClick");
		Log.d(TAG, "position: " + position);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.addToBackStack(null);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.replace(R.id.home_fragment, new FragmentHomeChurchs());
		ft.commit();
	}
}