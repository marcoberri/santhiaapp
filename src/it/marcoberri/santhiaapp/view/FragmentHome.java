package it.marcoberri.santhiaapp.view;

import it.marcoberri.santhiaapp.R;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class FragmentHome extends Fragment {

	protected static final String TAG = FragmentHome.class.getName();

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.i(TAG, "onCreateView()");
		final View v = inflater.inflate(R.layout.fragment_home, container, false);

		final ImageButton placeButton = (ImageButton) v
				.findViewById(R.id.home_button_place);

		placeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final String title = getResources().getStringArray(
						R.array.leftmenu)[2];
				getActivity().setTitle(title);
				final FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.content_frame, new FragmentPlace());
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		});

		return v;
	}

}