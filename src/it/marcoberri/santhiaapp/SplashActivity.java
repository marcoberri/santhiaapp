package it.marcoberri.santhiaapp;

import it.marcoberri.santhiaapp.db.datasource.PlaceGpsModelDataSource;
import it.marcoberri.santhiaapp.db.datasource.PlaceImageModelDataSource;
import it.marcoberri.santhiaapp.db.datasource.PlaceModelDataSource;
import it.marcoberri.santhiaapp.db.datasource.TourModelDataSource;
import it.marcoberri.santhiaapp.db.datasource.TourPlaceModelDataSource;
import it.marcoberri.santhiaapp.db.helper.DatabaseHelper;
import it.marcoberri.santhiaapp.model.PlaceImageModel;
import it.marcoberri.santhiaapp.model.PlaceModel;
import it.marcoberri.santhiaapp.model.PlaceModelList;
import it.marcoberri.santhiaapp.utils.HttpUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

/**
 * @author Marco Berri - marcoberri@gmail.com
 * 
 */
public class SplashActivity extends Activity {

    private static final String TAG = SplashActivity.class.getName();

    private String URL_PLACE = "http://www.marcoberri.it/santhiaapp/places.json";

    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;

    private TextView logText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	Log.i(TAG, "onCreate()");
	super.onCreate(savedInstanceState);
	setContentView(R.layout.splashscreen);
	logText = (TextView) findViewById(R.id.editText1);

	boolean checkOnline = HttpUtils.isOnline(this);
	if (checkOnline){
	    Log.i(TAG,"start download: " + URL_PLACE);
	    new DownloadFileFromURL().execute(URL_PLACE);
	} else {
	    Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();
	}
    }

    @Override
    protected Dialog onCreateDialog(int id) {
	switch (id) {
	case progress_bar_type: // we set this to 0*/
	    pDialog = new ProgressDialog(this);
	    pDialog.setMessage("Downloading file. Please wait...");
	    pDialog.setIndeterminate(false);
	    pDialog.setMax(100);
	    pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	    pDialog.setCancelable(true);
	    pDialog.show();
	    return pDialog;
	default:
	    return null;
	}
    }

    /**
     * Background Async Task to download file
     * */
    class DownloadFileFromURL extends AsyncTask<String, String, String> {

	private String places = "";

	/**
	 * Before starting background thread Show Progress Bar Dialog
	 * */
	@Override
	protected void onPreExecute() {
	    super.onPreExecute();
	    // pDialog.show();

	    showDialog(progress_bar_type);
	}

	/**
	 * Downloading file in background thread
	 * */
	@Override
	protected String doInBackground(String... f_url) {

	    final StringBuilder builder = new StringBuilder();
	    final HttpClient client = new DefaultHttpClient();
	    try {

		final HttpGet httpGet = new HttpGet(f_url[0]);
		final HttpResponse response = client.execute(httpGet);
		final StatusLine searchStatus = response.getStatusLine();
		if (searchStatus.getStatusCode() == 200) {
		    final HttpEntity entity = response.getEntity();
		    long lenghtOfFile = entity.getContentLength();

		    final InputStream content = entity.getContent();
		    final InputStreamReader input = new InputStreamReader(content);
		    final BufferedReader reader = new BufferedReader(input);
		    String lineIn;

		    long total = 0;
		    while ((lineIn = reader.readLine()) != null) {
			builder.append(lineIn);
			total += lineIn.getBytes().length;
			publishProgress("" + (int) ((total * 100) / lenghtOfFile));
		    }
		    places = builder.toString();
		}

	    } catch (Exception e) {
		Log.e(TAG, e.getMessage(), e);
	    }

	    return null;
	}

	/**
	 * Updating progress bar
	 * */
	protected void onProgressUpdate(String... progress) {
	    pDialog.setProgress(Integer.parseInt(progress[0]));
	}

	/**
	 * After completing background task Dismiss the progress dialog
	 * **/
	@Override
	protected void onPostExecute(String file_url) {
	    pDialog.dismiss();
	    logText.setText(places);

	    DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
	    dbHelper.clean();

	    final Gson gson = new Gson();
	    final PlaceModelList placeModelList = gson.fromJson(places, PlaceModelList.class);

	    final PlaceModelDataSource placeDS = new PlaceModelDataSource(getApplicationContext());
	    final PlaceImageModelDataSource placeImageDS = new PlaceImageModelDataSource(getApplicationContext());
	    final PlaceGpsModelDataSource placeGpsDS = new PlaceGpsModelDataSource(getApplicationContext());

	    for (PlaceModel model : placeModelList.getPlaces()) {
		placeDS.insertPlace(model.getId(), model.getTitle(), model.getSubtitle(), model.getText(), model.getAddress(), (model.getLocale() == null) ? "it_IT" : model.getLocale());

		for (PlaceImageModel modelImage : model.getImages()) {
		    if(modelImage == null || modelImage.getUrl() == null){
			Log.d(TAG, "modelImage is null");
			continue;
		    }
		    Log.d(TAG, modelImage.getUrl());
		    placeImageDS.insertPlaceImage(modelImage.getId(), model.getId(), modelImage.getUrl(), modelImage.getDisclamer(), modelImage.getTitle(), modelImage.getText());
		}

		placeGpsDS.insertPlaceGps(model.getId(), model.getGps().getLat(), model.getGps().getLng());

	    }

	    // DATI TEST
	    final TourModelDataSource tourDS = new TourModelDataSource(getApplicationContext());

	    final TourPlaceModelDataSource tourPlaceDS = new TourPlaceModelDataSource(getApplicationContext());

	    for (int i = 0; i < 20; i++) {
		tourDS.insertCommunityTour(i, "tour di test " + i, 0);

		for (int j = 1; j <= (Math.random() * (5 - 1)); j++) {
		    tourPlaceDS.insertPlace(i, j);
		}
	    }

	}

    }

    public void enterApp(View view) {
	final Intent intent = new Intent(getBaseContext(), HomeActivity.class);
	startActivity(intent);

    }
}