package it.marcoberri.santhiaapp.view;

import it.marcoberri.santhiaapp.HomeActivity;
import it.marcoberri.santhiaapp.R;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class FragmentCenter extends Fragment {

	public static final String ARG_VOICE_NUMBER = "voice_number";
	protected static final String TAG = FragmentCenter.class.getName();

	public FragmentCenter() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, "onCreateView()");
		int index = getArguments().getInt(ARG_VOICE_NUMBER);
		
		if (index == 0) {
			final String title = getResources()
					.getStringArray(R.array.leftmenu)[index];
			getActivity().setTitle(title);
		
			final FragmentTransaction ft = getFragmentManager()
					.beginTransaction();
			ft.replace(R.id.content_frame, new FragmentHome()).addToBackStack(
					null);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
		}

		if (index == 1) {
			final String title = HomeActivity.mMenuTitles[index].getTitle();
			getActivity().setTitle(title);
			FragmentPlace fplace = new FragmentPlace();

			final FragmentTransaction ft = getFragmentManager()
					.beginTransaction();
			ft.replace(R.id.content_frame, fplace).addToBackStack(null);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
		}

		if (index == 2) {
			final String title = HomeActivity.mMenuTitles[index].getTitle();
			getActivity().setTitle(title);
			final FragmentTransaction ft = getFragmentManager()
					.beginTransaction();
			ft.replace(R.id.content_frame, new FragmentCarnevale())
					.addToBackStack(null);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
		}

		if (index == 3) {
			final String title = HomeActivity.mMenuTitles[index].getTitle();
			getActivity().setTitle(title);
			final FragmentTransaction ft = getFragmentManager()
					.beginTransaction();
			ft.replace(R.id.content_frame, new FragmentBookmark()).addToBackStack(
					null);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
		}

		if (index == 4) {
			final String title = HomeActivity.mMenuTitles[index].getTitle();
			getActivity().setTitle(title);
			final FragmentTransaction ft = getFragmentManager()
					.beginTransaction();
			ft.replace(R.id.content_frame, new FragmentYourTour()).addToBackStack(
					null);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
		}

		return null;

	}
}