package it.marcoberri.santhiaapp;

import it.marcoberri.santhiaapp.view.FragmentChurchsAgata;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

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
			return getArguments().getInt("index", -1);
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
			 
			 if(getShownIndex() == -1){
				 
			 }else if(getShownIndex() == 0){
			
				
		/*		
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.replace(R.id.details, new FragmentChurchsAgata());
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
				*/
				 
				 
				 View rootView = inflater.inflate(R.layout.fragment_churchs,
					container, false);
				
				 
				 final ImageButton button = (ImageButton) rootView.findViewById(R.id.church_1_button);
		         button.setOnClickListener(new View.OnClickListener() {
		             public void onClick(View v) {
		            	 Log.i(TAG, "OnClickListener() - onClick() - R.id.church_1_button");
		            	 
		            	FragmentTransaction ft = getFragmentManager().beginTransaction();
		 				ft.replace(R.id.details, new FragmentChurchsAgata());
		 				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		 				ft.commit();
		 				
		 				
		 				
		            	 
		            	 
		             }
		         });
		         
				 
		        return rootView;
			}else{


				
			}
			//((TextView)v.findViewById(R.id.a)).setText(""+getShownIndex());
			
			return v;
		}
		
}
