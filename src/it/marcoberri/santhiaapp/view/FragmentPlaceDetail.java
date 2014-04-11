package it.marcoberri.santhiaapp.view;

import java.util.ArrayList;
import java.util.List;

import it.marcoberri.santhiaapp.R;
import it.marcoberri.santhiaapp.adapter.PlaceDetailGalleryPageAdapter;
import it.marcoberri.santhiaapp.db.model.PlaceImageModelDataSource;
import it.marcoberri.santhiaapp.model.PlaceImageModel;
import it.marcoberri.santhiaapp.model.PlaceModel;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.viewpagerindicator.CirclePageIndicator;

public class FragmentPlaceDetail extends Fragment {

	protected final static String TAG = FragmentPlaceDetail.class.getName();

	private int position;
	private PlaceModel placeModel;
	
	static final LatLng CHIESA = new LatLng(45.366255, 8.174720);
	private PlaceDetailGalleryPageAdapter galleryPageAdapter;
	private CirclePageIndicator mIndicator;
	private FragmentActivity context;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.i(TAG, "onCreateView()");
		final View v = inflater.inflate(R.layout.fragment_place_detail,
				container, false);

		final TextView title = (TextView) v
				.findViewById(R.id.place_detail_title);
		title.setText(placeModel.getTitle());
		

		
		

		final TabHost mTabHost = (TabHost) v.findViewById(android.R.id.tabhost);
		setupTabs(mTabHost);

		//GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(				R.id.tab_3_map)).getMap();
/*
		if (map != null) {

			Marker kiel = map.addMarker(new MarkerOptions()
					.position(CHIESA)
					.title("Chiesa di Sant'Agata")
					.snippet("Kiel is cool")
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.ic_launcher)

					)

			);

			kiel.setVisible(true);

			map.moveCamera(CameraUpdateFactory.newLatLngZoom(CHIESA, 30));

		}
*/
		/*
		 * this..onKeyDown(int keyCode, KeyEvent event) { switch(keyCode){ case
		 * KeyEvent.KEYCODE_BACK: // do something here return true; } return
		 * super.onKeyDown(keyCode, event); }
		 */

		Log.d(TAG, "Place Model " + placeModel);
		
		final List<FragmentPlaceDetailGallery> fragments = new ArrayList<FragmentPlaceDetailGallery>();
		
		for(PlaceImageModel image : placeModel.getImages()){
			Log.d(TAG, "image " + image.getUrl());
			fragments.add(FragmentPlaceDetailGallery.newInstance(image.getUrl()));
		}

		this.galleryPageAdapter  = new PlaceDetailGalleryPageAdapter(context.getSupportFragmentManager(), fragments);
		
		final ViewPager pager = (ViewPager)v.findViewById(R.id.place_detail_gallery);
        pager.setAdapter(this.galleryPageAdapter);
        mIndicator = (CirclePageIndicator)v.findViewById(R.id.place_detail_gallery_indicator);
        mIndicator.setViewPager(pager);
        
		return v;
	}

	private void setupTabs(TabHost mTabHost) {

		mTabHost.setup();
		final TabSpec tabSpec1 = mTabHost.newTabSpec("Story");
		tabSpec1.setContent(R.id.tab_1);
		tabSpec1.setIndicator("Storia");

		final TabSpec tabSpec2 = mTabHost.newTabSpec("Near");
		tabSpec2.setContent(R.id.tab_2);
		tabSpec2.setIndicator("Foto");

		final TabSpec tabSpec3 = mTabHost.newTabSpec("Map");
		tabSpec3.setContent(R.id.tab_3);
		tabSpec3.setIndicator("Map");

		mTabHost.addTab(tabSpec1);
		mTabHost.addTab(tabSpec2);
		mTabHost.addTab(tabSpec3);

		mTabHost.getTabWidget().getChildAt(0).getLayoutParams().height = getResources()
				.getDimensionPixelSize(R.dimen.tab_height);
		mTabHost.getTabWidget().getChildAt(1).getLayoutParams().height = getResources()
				.getDimensionPixelSize(R.dimen.tab_height);
		mTabHost.getTabWidget().getChildAt(2).getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.tab_height);

	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setPlaceModel(PlaceModel placeModel) {
		this.placeModel = placeModel;

	}
	
	@Override
	public void onAttach(Activity activity) {
		context=(FragmentActivity) activity;
	    super.onAttach(activity);
	}



}
