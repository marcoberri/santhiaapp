package it.marcoberri.santhiaapp.view;

import it.marcoberri.santhiaapp.R;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnKeyListener;

/**
 * @author Marco Berri - marcoberri@gmail.com
 * 
 */
public class FragmentEvents extends Fragment {

    protected final static String TAG = FragmentEvents.class.getName();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

	Log.i(TAG, "onCreateView()");
	View view = inflater.inflate(R.layout.fragment_events, container, false);

	view.setFocusableInTouchMode(true);
	view.requestFocus();
	view.setOnKeyListener(new OnKeyListener() {

	    @Override
	    public boolean onKey(View v, int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
		    Log.i(TAG, "keyCode: " + keyCode);
		    final FragmentTransaction ft = getFragmentManager().beginTransaction();
		    ft.replace(R.id.content_frame, new FragmentHome()).addToBackStack(null);
		    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		    ft.commit();
		}
		return true;
	    }
	});

	return view;
    }

}
