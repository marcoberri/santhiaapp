package it.marcoberri.santhiaapp.adapter;

import it.marcoberri.santhiaapp.view.FragmentHomeGallery;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
/**
 * @author Marco Berri - marcoberri@gmail.com
 *
 */

public class HomeGalleryPageAdapter extends FragmentStatePagerAdapter {

	private static final String TAG = HomeGalleryPageAdapter.class.getName();
	
	private List<FragmentHomeGallery> fragments;

	public HomeGalleryPageAdapter(FragmentManager fm, List<FragmentHomeGallery> fragments) {
		super(fm);
		Log.d(TAG, "Adapter List:" + fragments);
		this.fragments = fragments;

	}


	@Override
	public Fragment getItem(int position) {

		return this.fragments.get(position);

	}

	@Override
	public int getCount() {

		return this.fragments.size();

	}

}
