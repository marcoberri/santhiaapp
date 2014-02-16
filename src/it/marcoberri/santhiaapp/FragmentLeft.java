package it.marcoberri.santhiaapp;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentLeft extends ListFragment {
	private static String TAG = FragmentLeft.class.getName();
	boolean mDualPane;
	int mCurCheckPosition = 0;


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.i(TAG, "onActivityCreated");

		setListAdapter(new ArrayAdapter<String>(getActivity(),
				R.layout.fragment_left_item,
				getResources().getStringArray(R.array.leftmenu)));

		View detailsFrame = getActivity().findViewById(R.id.details);

		mDualPane = detailsFrame != null
				&& detailsFrame.getVisibility() == View.VISIBLE;

		if (savedInstanceState != null) {
			mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
		}

		if (mDualPane) {
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			showDetails(mCurCheckPosition);
		} else {
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			getListView().setItemChecked(mCurCheckPosition, true);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		Log.i(TAG, "onSaveInstanceState");
		super.onSaveInstanceState(outState);
		outState.putInt("curChoice", mCurCheckPosition);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Log.i(TAG, "onListItemClick");
		showDetails(position);
	}
	void showDetails(int index) {
		mCurCheckPosition = index;
		Log.d(TAG, "mDualPane:" + mDualPane);
		
		if (mDualPane) {
			getListView().setItemChecked(index, true);
			FragmentCenter details = (FragmentCenter) getFragmentManager().findFragmentById(R.id.details);
		
			if (details == null || details.getShownIndex() != index) {
				Log.d(TAG, "Make new fragment to show this selection");
				details = FragmentCenter.newInstance(index);
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.replace(R.id.details, details);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}

		} else {
			Intent intent = new Intent();
			intent.setClass(getActivity(), HomeActivity.class);
			intent.putExtra("index", index);
			startActivity(intent);
		}
	}
}


