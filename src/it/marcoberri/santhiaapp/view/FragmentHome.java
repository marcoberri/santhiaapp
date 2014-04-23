package it.marcoberri.santhiaapp.view;

import it.marcoberri.santhiaapp.R;
import it.marcoberri.santhiaapp.adapter.HomeGalleryPageAdapter;
import it.marcoberri.santhiaapp.db.datasource.PlaceGpsModelDataSource;
import it.marcoberri.santhiaapp.db.datasource.PlaceModelDataSource;
import it.marcoberri.santhiaapp.model.PlaceModel;
import it.marcoberri.santhiaapp.task.LoadDataUrlTask;
import it.marcoberri.santhiaapp.wrapper.PlacesWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.viewpagerindicator.CirclePageIndicator;

public class FragmentHome extends Fragment {

	protected static final String TAG = FragmentHome.class.getName();
	private FragmentActivity context;
	private HomeGalleryPageAdapter galleryPageAdapter;
	private CirclePageIndicator mIndicator;
	private GoogleMap map;
	private PlaceModelDataSource dsPlace;
	private PlaceGpsModelDataSource dsGps;
	private static View view;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		Log.i(TAG, "onCreateView()");
		
		if (view != null) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null)
				parent.removeView(view);
			view = null;
		}

		
		  
		
		try {
			view = inflater.inflate(R.layout.fragment_home, container,false);
		} catch (InflateException e) {
			Log.e(TAG, "error inflattter ", e);
			return view;
		}
		


		dsPlace = new PlaceModelDataSource(context);
		dsGps = new PlaceGpsModelDataSource(context);

		map = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.home_map)).getMap();

		if (map != null) {

			List<PlaceModel> places = dsPlace.getPlaces();
			
			for (PlaceModel place : places) {
				place.setGps(dsGps.getGpsByPlaceId(place.getId()));
				if (place.getGps() == null) {
					continue;
				}

				final LatLng gpsPos = new LatLng(place.getGps().getLat(), place
						.getGps().getLng());

				Marker marker = map.addMarker(new MarkerOptions()
						.position(gpsPos)
						.title(place.getTitle())
						.snippet(
								place.getSubtitle() + "\n"
										+ place.getAddress()));

				marker.setVisible(true);

				map.moveCamera(CameraUpdateFactory.newLatLngZoom(gpsPos, 10));
			}

			map.setMyLocationEnabled(true);

		}

	/*	final ImageButton placeButton = (ImageButton) view
				.findViewById(R.id.home_button_place);

		placeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final String title = getResources().getStringArray(
						R.array.leftmenu)[2];
				// getActivity().setTitle(title);
				final FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.content_frame, new FragmentPlace());
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		});

		final ImageButton testButton = (ImageButton) view
				.findViewById(R.id.home_button_test);

		testButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Log.d(TAG, "Start Load data");

				LoadDataUrlTask load = new LoadDataUrlTask();
				load.execute("http://www.marcoberri.it/santhiaapp/places.json");

				try {
					String result = load.get();
					Toast.makeText(getActivity(), result, Toast.LENGTH_LONG)
							.show();
					PlacesWrapper placesWrapper = new Gson().fromJson(result,
							PlacesWrapper.class);
					Log.d(TAG, "PlaceWrapper " + placesWrapper);

				} catch (final InterruptedException e) {
					Log.e(TAG, "", e);
				} catch (final ExecutionException e) {
					Log.e(TAG, "", e);
				}

			}
		});
*/
		// gallery view in home
		final List<FragmentHomeGallery> fragments = new ArrayList<FragmentHomeGallery>();
		// TODO get random image from archive
		fragments.add(FragmentHomeGallery
				.newInstance(R.drawable.church_parrochia));
		fragments.add(FragmentHomeGallery
				.newInstance(R.drawable.church_sanrocco));
		fragments.add(FragmentHomeGallery
				.newInstance(R.drawable.church_santannajpg));
		this.galleryPageAdapter = new HomeGalleryPageAdapter(
				context.getSupportFragmentManager(), fragments);

		final ViewPager pager = (ViewPager) view.findViewById(R.id.home_gallery);
		pager.setAdapter(this.galleryPageAdapter);
		mIndicator = (CirclePageIndicator) view
				.findViewById(R.id.imageview_gallery_home_indicator);
		mIndicator.setViewPager(pager);

		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		context = (FragmentActivity) activity;
		super.onAttach(activity);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		MapFragment f = (MapFragment) getFragmentManager().findFragmentById(
				R.id.home_map);
		if (f != null)
			getFragmentManager().beginTransaction().remove(f).commit();

	}
}