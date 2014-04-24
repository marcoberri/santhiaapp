package it.marcoberri.santhiaapp.animation;

import android.support.v4.widget.SlidingPaneLayout;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

/**
 * @author Marco Berri - marcoberri@gmail.com
 * 
 */

public class AnimiationGalleryImageListener implements AnimationListener {

    private ImageView imageView;
    private int x = 0;
    private int y = 0;
    private SlidingPaneLayout.LayoutParams lp;

    @Override
    public void onAnimationRepeat(Animation animation) {
	lp.setMargins(x, y, 0, 0);
	imageView.setLayoutParams(lp);
	if (x < imageView.getWidth())
	    x++;
	else
	    x--;
	if (y < imageView.getHeight())
	    y++;
	else
	    y--;
    }

    @Override
    public void onAnimationStart(Animation animation) {
	lp = new SlidingPaneLayout.LayoutParams(imageView.getWidth() + x, imageView.getHeight() + y);
	lp.setMargins(x, y, 0, 0);
	imageView.setLayoutParams(lp);
	imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	// lp.updateViewLayout(imageView, lp);
    }

    public void setImageView(ImageView imageView) {
	this.imageView = imageView;
    }

    @Override
    public void onAnimationEnd(Animation animation) {
	// imageView.clearAnimation();

    }

}
