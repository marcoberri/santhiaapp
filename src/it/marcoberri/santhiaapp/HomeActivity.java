package it.marcoberri.santhiaapp;


import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

public class HomeActivity extends Activity {

	private final  String TAG = HomeActivity.class.getName();
	
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG,"onCreate");
		super.onCreate(savedInstanceState);

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			finish();
			return;
		}
		if (savedInstanceState == null) {
			FragmentCenter details = new FragmentCenter();
			details.setArguments(getIntent().getExtras());
			getFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
		}
	}
}
