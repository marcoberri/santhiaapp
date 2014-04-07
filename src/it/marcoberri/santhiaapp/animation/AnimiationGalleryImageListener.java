package it.marcoberri.santhiaapp.animation;

import android.support.v4.widget.SlidingPaneLayout.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class AnimiationGalleryImageListener implements AnimationListener{

		private ImageView imageView;
		private int x = 0;
		private int y = 0;
		private LayoutParams lp;
		
	    @Override
	    public void onAnimationEnd(Animation animation) {
	        imageView.clearAnimation();

	    }

	    @Override
	    public void onAnimationRepeat(Animation animation) {
	        lp.setMargins(x, y, 0, 0);
	        imageView.setLayoutParams(lp);
	    	x++;
	    	y++;
	    }

	    @Override
	    public void onAnimationStart(Animation animation) {
	        lp = new LayoutParams(imageView.getWidth(), imageView.getHeight());
	        lp.setMargins(x, y, 0, 0);
	        imageView.setLayoutParams(lp);
	    }

	
	    public void setImageView(ImageView imageView){
	    	this.imageView = imageView;
	    }

	
}
