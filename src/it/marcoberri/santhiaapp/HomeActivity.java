package it.marcoberri.santhiaapp;


import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

public class HomeActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			finish();
			return;
		}

		if (savedInstanceState == null) {

			// create fragment
			FragmentCenter details = new FragmentCenter();
			details.setArguments(getIntent().getExtras());
			getFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
		}
	}
}
