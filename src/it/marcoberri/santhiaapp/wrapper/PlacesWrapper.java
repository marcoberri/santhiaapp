package it.marcoberri.santhiaapp.wrapper;

import it.marcoberri.santhiaapp.model.PlaceModel;

import java.util.List;

import android.util.Log;
/**
 * @author Marco Berri - marcoberri@gmail.com
 * 
 */
public class PlacesWrapper {

    private final static String TAG = PlacesWrapper.class.getName();
    private List<PlaceModel> places;
    private int version;

    public List<PlaceModel> getPlaces() {
	return places;
    }

    public PlaceModel[] getPlacesArray() {
	return (PlaceModel[]) places.toArray();
    }

    public void setPlaces(List<PlaceModel> places) {
	this.places = places;
    }

    public int getVersion() {
	return version;
    }

    public void setVersion(int version) {
	this.version = version;
    }

    public String toString() {
	return "vestion : 1 - places : " + places;
    }

    public PlaceModel[] toArray() {
	if (places != null) {
	    Log.d(TAG, "place != null");
	    return (PlaceModel[]) places.toArray();
	}

	return null;
    }
}
