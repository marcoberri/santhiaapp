package it.marcoberri.santhiaapp.adapter;

import it.marcoberri.santhiaapp.view.FragmentPlaceDetailGallery;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class PlaceDetailGalleryPageAdapter extends FragmentPagerAdapter {

	private static final String TAG = PlaceDetailGalleryPageAdapter.class.getName();
	
	private List<FragmentPlaceDetailGallery> fragments;

	public PlaceDetailGalleryPageAdapter(FragmentManager fm, List<FragmentPlaceDetailGallery> fragments) {
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
