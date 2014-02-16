package it.marcoberri.santhiaapp;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class FragmentCenter extends Fragment {

	private static String TAG = FragmentCenter.class.getName();

		public static FragmentCenter newInstance(int index) {
			Log.i(TAG, "newInstance");
			FragmentCenter f = new FragmentCenter();
			Bundle args = new Bundle();
			args.putInt("index", index);
			f.setArguments(args);
			return f;	
		}

		public int getShownIndex() {
			Log.i(TAG, "getShownIndex");
			return getArguments().getInt("index", 0);
		}
		@Override
		public void onAttach(Activity activity) {
			Log.i(TAG, "onAttach()");
			super.onAttach(activity);
		}

		@Override
		public void onCreate(Bundle savedInstanceState) {
			Log.i(TAG, "onCreate()");
			super.onCreate(savedInstanceState);
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
		
			Log.i(TAG, "onCreateView()");
			
			 View v = null;
			
			if(getShownIndex() == 0){
			
				
				 View rootView = inflater.inflate(R.layout.fragment_churchs,
					container, false);
				
		        return rootView;
			}
			//((TextView)v.findViewById(R.id.a)).setText(""+getShownIndex());
			
			return v;
		}
		
}
