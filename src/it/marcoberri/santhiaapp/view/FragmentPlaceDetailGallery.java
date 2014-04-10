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

public class FragmentPlaceDetailGallery extends Fragment {
	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
	public static final String EXTRA_MESSAGE_URL = "EXTRA_MESSAGE_URL";
	private static final String TAG = FragmentPlaceDetailGallery.class.getName();

	public static final FragmentPlaceDetailGallery newInstance(int resource) {
		FragmentPlaceDetailGallery f = new FragmentPlaceDetailGallery();
		Bundle bdl = new Bundle(1);
		bdl.putInt(EXTRA_MESSAGE, resource);
		f.setArguments(bdl);
		return f;
	}
	
	
	public static final FragmentPlaceDetailGallery newInstance(String url) {
		FragmentPlaceDetailGallery f = new FragmentPlaceDetailGallery();
		Bundle bdl = new Bundle(1);
		bdl.putString(EXTRA_MESSAGE_URL, url);
		f.setArguments(bdl);
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView");
	
		final View v = inflater.inflate(R.layout.fragment_place_detail_gallery,container, false);
		final ImageView image = (ImageView) v.findViewById(R.id.imageview_gallery_place_detail);
		
		final String url = getArguments().getString(EXTRA_MESSAGE_URL);
		final Integer resource = getArguments().getInt(EXTRA_MESSAGE);

		if(url != null){
			new DownloadImageTask((ImageView)  image).execute(url);
		}
		if(resource != null){
			//TODO
		}
		
		return v;
	}
}
