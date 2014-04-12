package it.marcoberri.santhiaapp.utils;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class HttpUtils {

	final static String TAG = HttpUtils.class.getName();

	/**
	 * @param args
	 * @throws IOException
	 */
	public static String getDataFromUrl(String url) throws IOException {
		
		final HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
		
		  try {
			  Log.d(TAG,"Start read url:" + url);
			  Log.d(TAG,"Url connected: "  + httpURLConnection.getURL());
              InputStream input = new BufferedInputStream(httpURLConnection.getInputStream());
              return streamToString(input);
              
		  } finally {
                  httpURLConnection.disconnect();
          }
        
    	
	}
	
	private static String streamToString(InputStream stream) throws IOException {
        Writer writer = new StringWriter();
        InputStreamReader input = new InputStreamReader(new BufferedInputStream(stream), "UTF-8");
        
        try {
                final char[] buffer = new char[1024];
                int read;

                while ((read = input.read(buffer)) != -1)
                        writer.write(buffer, 0, read);
        } finally {
                input.close();
        }
        
        return writer.toString();
}
	

	public static boolean isOnline(Activity activity) {
	    ConnectivityManager cm =
	        (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
}
