package it.marcoberri.santhiaapp.view;

import it.marcoberri.santhiaapp.R;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentHome extends Fragment {

	protected static final String TAG = FragmentHome.class.getName();

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.i(TAG, "onCreateView()");
		View v = inflater.inflate(R.layout.fragment_home, container,
				false);
		
		return v;
	}
	
	}