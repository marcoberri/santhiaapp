package it.marcoberri.santhiaapp.view;

import it.marcoberri.santhiaapp.R;
import it.marcoberri.santhiaapp.animation.AnimiationGalleryImageListener;
import it.marcoberri.santhiaapp.task.DownloadImageTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

/**
 * @author Marco Berri - marcoberri@gmail.com
 * 
 */
public class FragmentHomeGallery extends Fragment {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static final String EXTRA_MESSAGE_URL = "EXTRA_MESSAGE_URL";
    public static final String EXTRA_MESSAGE_DISCLAMER = "EXTRA_MESSAGE_DISCLAMER";
    public static final String EXTRA_MESSAGE_TITLE = "EXTRA_MESSAGE_TITLE";

    private static final String TAG = FragmentHomeGallery.class.getName();

    public static final FragmentHomeGallery newInstance(int resource) {
	FragmentHomeGallery f = new FragmentHomeGallery();
	Bundle bdl = new Bundle(1);
	bdl.putInt(EXTRA_MESSAGE, resource);
	f.setArguments(bdl);
	return f;
    }

    public static final FragmentHomeGallery newInstance(String url, String title, String disclamer) {
	final FragmentHomeGallery f = new FragmentHomeGallery();
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

	final String url = getArguments().getString(EXTRA_MESSAGE_URL);
	Log.d(TAG, "getUrl  : " + url);

	final String disclamer = getArguments().getString(EXTRA_MESSAGE_DISCLAMER);
	Log.d(TAG, "disclamer  : " + disclamer);

	final View v = inflater.inflate(R.layout.fragment_home_gallery, container, false);
	final ImageView image = (ImageView) v.findViewById(R.id.imageview_gallery_home);

	/*
	 * final TranslateAnimation animation = new TranslateAnimation(0, -50,
	 * 0, -100); animation.setDuration(3000); animation.setFillAfter(false);
	 * animation.setRepeatCount(100);
	 */
	// final AnimiationGalleryImageListener animiationGalleryImageListener =
	// new AnimiationGalleryImageListener();

	if (url != null) {
	    new DownloadImageTask((ImageView) image).execute(url);
	}

	return v;
    }
}
