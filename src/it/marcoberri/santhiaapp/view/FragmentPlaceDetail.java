package it.marcoberri.santhiaapp.view;

import java.util.ArrayList;
import java.util.List;

import it.marcoberri.santhiaapp.R;
import it.marcoberri.santhiaapp.adapter.PlaceDetailGalleryPageAdapter;
import it.marcoberri.santhiaapp.db.datasource.PlaceBookmarkModelDataSource;
import it.marcoberri.santhiaapp.model.PlaceBookmarkModel;
import it.marcoberri.santhiaapp.model.PlaceImageModel;
import it.marcoberri.santhiaapp.model.PlaceModel;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.viewpagerindicator.CirclePageIndicator;

public class FragmentPlaceDetail extends Fragment {

	protected final static String TAG = FragmentPlaceDetail.class.getName();

	private int position;
	private PlaceModel placeModel;

	private CirclePageIndicator mIndicator;
	private FragmentActivity context;
	private GoogleMap map;
	private PlaceBookmarkModelDataSource dsBookMark;

	private static View view;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.i(TAG, "onCreateView()");

		if (view != null) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null)
				parent.removeView(view);
			view = null;
		}

		try {
			view = inflater.inflate(R.layout.fragment_place_detail, container,
					false);
		} catch (InflateException e) {
			Log.e(TAG, "error inflattter ", e);
			return view;
		}

		setHasOptionsMenu(true);

		final TextView title = (TextView) view
				.findViewById(R.id.place_detail_title);
		title.setText(placeModel.getTitle());

		final TextView text = (TextView) view
				.findViewById(R.id.place_detail_text);
		text.setText(placeModel.getText());

		final TabHost mTabHost = (TabHost) view
				.findViewById(android.R.id.tabhost);
		setupTabs(mTabHost);

		Log.d(TAG, "Place Model " + placeModel);

		final List<FragmentPlaceDetailGallery> fragments = new ArrayList<FragmentPlaceDetailGallery>();

		for (PlaceImageModel image : placeModel.getImages()) {
			Log.d(TAG, "image " + image);
			fragments.add(FragmentPlaceDetailGallery.newInstance(
					image.getUrl(), image.getTitle(), image.getDisclamer()));
		}

		final PlaceDetailGalleryPageAdapter galleryPageAdapter = new PlaceDetailGalleryPageAdapter(
				context.getSupportFragmentManager(), fragments);
		final ViewPager pager = (ViewPager) view
				.findViewById(R.id.place_detail_gallery);

		pager.removeAllViewsInLayout();
		pager.setAdapter(galleryPageAdapter);
		mIndicator = (CirclePageIndicator) view
				.findViewById(R.id.place_detail_gallery_indicator);
		mIndicator.setViewPager(pager);

		galleryPageAdapter.notifyDataSetChanged();

		map = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.tab_3_map)).getMap();

		if (map != null) {

			final LatLng gpsPos = new LatLng(placeModel.getGps().getLat(),
					placeModel.getGps().getLng());

			Marker kiel = map.addMarker(new MarkerOptions()
					.position(gpsPos)
					.title(placeModel.getTitle())
					.snippet(
							placeModel.getSubtitle() + "<br/>"
									+ placeModel.getAddress())
			// .icon(BitmapDescriptorFactory
			// .fromResource(R.drawable.ic_launcher)

					// )

					);

			kiel.setVisible(true);

			map.moveCamera(CameraUpdateFactory.newLatLngZoom(gpsPos, 18));
			map.setMyLocationEnabled(true);

		}

		dsBookMark = new PlaceBookmarkModelDataSource(context);
		return view;

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		final MapFragment f = (MapFragment) getFragmentManager().findFragmentById(
				R.id.tab_3_map);
		if (f != null)
			getFragmentManager().beginTransaction().remove(f).commit();

		final MapFragment f2 = (MapFragment) getFragmentManager().findFragmentById(
				R.id.tab_2);
		if (f2 != null)
			getFragmentManager().beginTransaction().remove(f2).commit();

		final MapFragment f1 = (MapFragment) getFragmentManager().findFragmentById(
				R.id.tab_1);
		if (f1 != null)
			getFragmentManager().beginTransaction().remove(f1).commit();

		final MapFragment f4 = (MapFragment) getFragmentManager().findFragmentById(
				R.id.tab_4);
		if (f4 != null)
			getFragmentManager().beginTransaction().remove(f4).commit();
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

		final TabSpec tabSpec4 = mTabHost.newTabSpec("Comments");
		tabSpec4.setContent(R.id.tab_4);
		tabSpec4.setIndicator("comments");
		
		mTabHost.addTab(tabSpec1);
		mTabHost.addTab(tabSpec2);
		mTabHost.addTab(tabSpec3);
		mTabHost.addTab(tabSpec4);

		mTabHost.getTabWidget().getChildAt(0).getLayoutParams().height = getResources()
				.getDimensionPixelSize(R.dimen.tab_height);
		mTabHost.getTabWidget().getChildAt(1).getLayoutParams().height = getResources()
				.getDimensionPixelSize(R.dimen.tab_height);
		mTabHost.getTabWidget().getChildAt(2).getLayoutParams().height = getResources()
				.getDimensionPixelSize(R.dimen.tab_height);
		mTabHost.getTabWidget().getChildAt(3).getLayoutParams().height = getResources()
				.getDimensionPixelSize(R.dimen.tab_height);		

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
		context = (FragmentActivity) activity;
		super.onAttach(activity);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.place_detail, menu);
		final PlaceBookmarkModel bookmark = dsBookMark
				.getPlaceBookmarkByPlaceId(placeModel.getId());
		final MenuItem item = menu.findItem(R.id.add_to_bookmark_icon);
		if (bookmark.getPlaceId() > 0) {
			item.setIcon(android.R.drawable.btn_star_big_on);
		} else {
			item.setIcon(android.R.drawable.btn_star_big_off);
		}

		super.onCreateOptionsMenu(menu, inflater);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.add_to_bookmark_icon:

			final PlaceBookmarkModel bookmark = dsBookMark
					.getPlaceBookmarkByPlaceId(placeModel.getId());

			if (bookmark.getPlaceId() > 0) {
				final int tot_delete = dsBookMark
						.deletePlaceBookmarkByPlaceId(placeModel.getId());
				Log.d(TAG, "tot delete bookmark" + tot_delete);
				item.setIcon(android.R.drawable.btn_star_big_off);
			} else {
				dsBookMark.insertPlaceBookmark(placeModel.getId());
				item.setIcon(android.R.drawable.btn_star_big_on);
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
