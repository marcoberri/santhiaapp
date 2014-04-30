package it.marcoberri.santhiaapp.view;

import it.marcoberri.santhiaapp.R;
import it.marcoberri.santhiaapp.task.DownloadImageTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Marco Berri - marcoberri@gmail.com
 * 
 */
public class FragmentPlaceDetailGallery extends Fragment {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static final String EXTRA_MESSAGE_URL = "EXTRA_MESSAGE_URL";
    public static final String EXTRA_MESSAGE_DISCLAMER = "EXTRA_MESSAGE_DISCLAMER";
    public static final String EXTRA_MESSAGE_TITLE = "EXTRA_MESSAGE_TITLE";
    private static final String TAG = FragmentPlaceDetailGallery.class.getName();

    public static final FragmentPlaceDetailGallery newInstance(int resource) {
	FragmentPlaceDetailGallery f = new FragmentPlaceDetailGallery();
	Bundle bdl = new Bundle(1);
	bdl.putInt(EXTRA_MESSAGE, resource);
	f.setArguments(bdl);
	return f;
    }

    public static final FragmentPlaceDetailGallery newInstance(String url, String title, String disclamer) {
	final FragmentPlaceDetailGallery f = new FragmentPlaceDetailGallery();
	final Bundle bdl = new Bundle(1);
	bdl.putString(EXTRA_MESSAGE_URL, url);
	bdl.putString(EXTRA_MESSAGE_TITLE, title);
	bdl.putString(EXTRA_MESSAGE_DISCLAMER, disclamer);
	Log.d(TAG, EXTRA_MESSAGE_URL + " : " + url);
	Log.d(TAG, EXTRA_MESSAGE_TITLE + " : " + title);
	Log.d(TAG, EXTRA_MESSAGE_DISCLAMER + " : " + disclamer);
	f.setArguments(bdl);
	return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	Log.i(TAG, "onCreateView()");

	final View v = inflater.inflate(R.layout.fragment_place_detail_gallery, container, false);
	final ImageView image = (ImageView) v.findViewById(R.id.imageview_gallery_place_detail);

	final String url = getArguments().getString(EXTRA_MESSAGE_URL);
	Log.d(TAG, "getUrl  : " + url);

	// final Integer resource = getArguments().getInt(EXTRA_MESSAGE);

	final String disclamer = getArguments().getString(EXTRA_MESSAGE_DISCLAMER);
	Log.d(TAG, "disclamer  : " + disclamer);

	final TextView disclameText = (TextView) v.findViewById(R.id.imageview_gallery_place_detail_disclamer);
	disclameText.setText(disclamer);

	final String title = getArguments().getString(EXTRA_MESSAGE_TITLE);
	final TextView titleText = (TextView) v.findViewById(R.id.imageview_gallery_place_detail_title);
	titleText.setText(title);

	Log.d(TAG, "Url to load image:" + url);

	if (url != null) {
	    new DownloadImageTask((ImageView) image).execute(url);
	}
	/*
	 * if(resource != null){ //TODO }
	 */

	return v;
    }
}
