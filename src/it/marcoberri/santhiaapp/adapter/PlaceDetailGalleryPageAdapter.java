package it.marcoberri.santhiaapp.adapter;

import it.marcoberri.santhiaapp.view.FragmentPlaceDetailGallery;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

/**
 * @author Marco Berri - marcoberri@gmail.com
 * 
 */

public class PlaceDetailGalleryPageAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = PlaceDetailGalleryPageAdapter.class.getName();

    private List<FragmentPlaceDetailGallery> fragments;
    final private FragmentManager mFragmentManager;

    public PlaceDetailGalleryPageAdapter(FragmentManager fm, List<FragmentPlaceDetailGallery> fragments) {
	super(fm);
	Log.d(TAG, "Adapter List:" + fragments);
	this.fragments = fragments;
	mFragmentManager = fm;
	this.notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {

	if (position < getCount()) {
	    return fragments.get(position);
	} else {
	    return null;
	}
    }

    @Override
    public int getCount() {

	return this.fragments.size();

    }

    @Override
    public int getItemPosition(Object object) {
	return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
	if (position >= getCount()) {
	    android.support.v4.app.FragmentTransaction trans = mFragmentManager.beginTransaction();
	    trans.remove((Fragment) object);
	    trans.commit();
	}
    }
}
