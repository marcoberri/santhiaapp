package it.marcoberri.santhiaapp.view;

import it.marcoberri.santhiaapp.R;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */
public class FragmentCenter extends Fragment {

	public static final String ARG_VOICE_NUMBER = "voice_number";
	protected static final String TAG = FragmentCenter.class.getName();

	public FragmentCenter() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		int index = getArguments().getInt(ARG_VOICE_NUMBER);
		if (index == 0) {

		}

		if (index == 1) {
			final View rootView = inflater.inflate(R.layout.fragment_churchs,
					container, false);
			String title = getResources().getStringArray(R.array.leftmenu)[index];

			final ImageButton button = (ImageButton) rootView
					.findViewById(R.id.church_1_button);

			button.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Log.i(TAG,
							"OnClickListener() - onClick() - R.id.church_1_button");
					FragmentTransaction ft = getFragmentManager()
							.beginTransaction();
					ft.replace(R.id.content_frame, new FragmentChurchsAgata());

					ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
					ft.commit();

				}
			});

			getActivity().setTitle(title);
			return rootView;
		}

		return null;

		/*
		 * int imageId =
		 * getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
		 * "drawable", getActivity().getPackageName()); // ((ImageView)
		 * rootView.findViewById(R.id.image)).setImageResource(imageId);
		 */

	}
}