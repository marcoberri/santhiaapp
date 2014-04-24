package it.marcoberri.santhiaapp.view;

import it.marcoberri.santhiaapp.R;
import it.marcoberri.santhiaapp.adapter.TourListAdapter;
import it.marcoberri.santhiaapp.db.datasource.TourModelDataSource;
import it.marcoberri.santhiaapp.model.TourModel;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
/**
 * @author Marco Berri - marcoberri@gmail.com
 * 
 */
public class FragmentYourTour extends Fragment implements OnScrollListener {

    protected final static String TAG = FragmentYourTour.class.getName();

    private TourModel[] tourCommunityModelArray;
    private TourModel[] tourYourModelArray;
    private TourListAdapter adapterCommunity;
    private TourListAdapter adapterYour;
    private EditText inputSearchCommunity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

	final View view = inflater.inflate(R.layout.fragment_yourtour, container, false);

	final TabHost mTabHost = (TabHost) view.findViewById(android.R.id.tabhost);
	setupTabs(mTabHost);

	final ListView listviewCommunity = (ListView) view.findViewById(R.id.listViewTourCommunity);

	final TourModelDataSource dsTour = new TourModelDataSource(getActivity().getApplicationContext());

	tourCommunityModelArray = (TourModel[]) dsTour.getCommunityTours().toArray(new TourModel[dsTour.getCommunityTours().size()]);

	adapterCommunity = new TourListAdapter(view.getContext(), R.layout.fragment_yourtour_list_item_left, tourCommunityModelArray);

	listviewCommunity.setOnScrollListener(this);
	listviewCommunity.setAdapter(adapterCommunity);

	inputSearchCommunity = (EditText) view.findViewById(R.id.listViewTourCommunity_search);

	final ListView listviewYour = (ListView) view.findViewById(R.id.listViewTour);

	tourYourModelArray = (TourModel[]) dsTour.getYourTours().toArray(new TourModel[dsTour.getYourTours().size()]);

	adapterYour = new TourListAdapter(view.getContext(), R.layout.fragment_yourtour_list_item_left, tourYourModelArray);

	listviewYour.setOnScrollListener(this);
	listviewYour.setAdapter(adapterYour);

	final Button addNewTourButton = (Button) view.findViewById(R.id.listViewTour_add_tour);
	addNewTourButton.setOnClickListener(new View.OnClickListener() {
	    public void onClick(View v) {

		final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		LayoutInflater inflater = getActivity().getLayoutInflater();

		final View dialogView = inflater.inflate(R.layout.dialog_add_new_tour, null);

		builder.setView(dialogView);

		builder.setMessage("test message").setTitle("test title");

		builder.setPositiveButton("save", new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int id) {

			final EditText valueView = (EditText) dialogView.findViewById(R.id.edit_tour_name);

			if (valueView == null) {
			    Log.d(TAG, "NULL");
			} else {
			    String tour_name = valueView.getText().toString();
			    Log.d(TAG, "Tour to save:" + tour_name);

			    final TourModelDataSource tourDS = new TourModelDataSource(getActivity().getApplicationContext());
			    tourDS.insertYouserTour(tour_name);
			    tourYourModelArray = (TourModel[]) dsTour.getYourTours().toArray(new TourModel[dsTour.getYourTours().size()]);
			    adapterYour = new TourListAdapter(view.getContext(), R.layout.fragment_yourtour_list_item_left, tourYourModelArray);
			    listviewYour.setAdapter(adapterYour);
			}

		    }
		});

		builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int id) {
			dialog.cancel();
		    }
		});

		builder.create();
		builder.show();

	    }
	});

	// final Button btnAddMore = new
	// Button(this.getActivity().getApplicationContext());
	// btnAddMore.setText("Add new");

	// listviewYour.addFooterView(btnAddMore);
	return view;
    }

    private void setupTabs(TabHost mTabHost) {

	mTabHost.setup();
	final TabSpec tabSpec1 = mTabHost.newTabSpec("Your");
	tabSpec1.setContent(R.id.tab_yours);
	tabSpec1.setIndicator("Your");

	final TabSpec tabSpec2 = mTabHost.newTabSpec("Community");
	tabSpec2.setContent(R.id.tab_community);
	tabSpec2.setIndicator("Community");

	mTabHost.addTab(tabSpec1);
	mTabHost.addTab(tabSpec2);

	mTabHost.getTabWidget().getChildAt(0).getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.tab_height);
	mTabHost.getTabWidget().getChildAt(1).getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.tab_height);

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
	// TODO Auto-generated method stub

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
	// TODO Auto-generated method stub

    }

}
