package it.marcoberri.santhiaapp.graph;


import it.marcoberri.santhiaapp.R;
import android.app.Fragment;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentHomeChurchs  extends Fragment {
	
	private final static String TAG = FragmentHomeChurchs.class.getName();
	
	 public View onCreateView(LayoutInflater inflater, 
	           ViewGroup container, Bundle savedInstanceState) {
		 Log.v(TAG, "onCreateView");
		    return  inflater.inflate(R.layout.frame_home_churchs, 
                    container, false);

	 }

}
