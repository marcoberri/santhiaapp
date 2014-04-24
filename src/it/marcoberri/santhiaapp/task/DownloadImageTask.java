package it.marcoberri.santhiaapp.task;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

/**
 * @author Marco Berri - marcoberri@gmail.com
 * 
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    private static final String TAG = DownloadImageTask.class.getName();

    ImageView bmImage;
    public static Map<String, Bitmap> cacheBitmap;

    public DownloadImageTask(ImageView bmImage) {
	this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
	final String urldisplay = urls[0];

	if (cacheBitmap != null && !cacheBitmap.isEmpty() && cacheBitmap.containsKey(urldisplay)) {
	    Log.d(TAG, "url: " + urldisplay + " is in memory cache");
	    return cacheBitmap.get(urldisplay);
	}

	Bitmap mIcon11 = null;
	try {
	    final InputStream in = new java.net.URL(urldisplay).openStream();
	    mIcon11 = BitmapFactory.decodeStream(in);
	    if (cacheBitmap == null) {
		cacheBitmap = new HashMap<String, Bitmap>();
	    }
	    cacheBitmap.put(urldisplay, mIcon11);
	} catch (Exception e) {
	    Log.e(TAG, "Error", e);
	}
	return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
	bmImage.setImageBitmap(result);
    }
}
