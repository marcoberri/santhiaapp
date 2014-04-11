package it.marcoberri.santhiaapp.view;

import it.marcoberri.santhiaapp.R;
import it.marcoberri.santhiaapp.animation.AnimiationGalleryImageListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class FragmentHomeGallery extends Fragment {
	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
	private static final String TAG = FragmentHomeGallery.class.getName();

	
	
	public static final FragmentHomeGallery newInstance(int resource) {
		FragmentHomeGallery f = new FragmentHomeGallery();
		Bundle bdl = new Bundle(1);
		bdl.putInt(EXTRA_MESSAGE, resource);
		f.setArguments(bdl);
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView");
		final int resource = getArguments().getInt(EXTRA_MESSAGE);
		final View v = inflater.inflate(R.layout.fragment_home_gallery,
				container, false);
		final ImageView image = (ImageView) v.findViewById(R.id.imageview_gallery_home);
		image.setImageResource(resource);

		
		  final TranslateAnimation animation = new TranslateAnimation(0, -50, 0, -100);
		  animation.setDuration(3000);
		  animation.setFillAfter(false);
		  animation.setRepeatCount(100);
		  
		  final AnimiationGalleryImageListener animiationGalleryImageListener = new AnimiationGalleryImageListener();
		 /* animiationGalleryImageListener.setImageView(image);
		  animation.setAnimationListener(animiationGalleryImageListener);*/
		  animiationGalleryImageListener.setImageView(image);
		  
		  //image.startAnimation(animation);
		  


	        
		 
		return v;
	}
}
