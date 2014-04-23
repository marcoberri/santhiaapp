package it.marcoberri.santhiaapp.model;

import java.util.List;
/**
 * @author Marco Berri - marcoberri@gmail.com
 *
 */

public class PlaceModelList extends BaseModel {

	private List<PlaceModel> places;

	public List<PlaceModel> getPlaces() {
		return places;
	}

	public void setPlaces(List<PlaceModel> places) {
		this.places = places;
	}

	public String toString(){
		return "version :" + this.getVersion() + " places: " + this.places; 
	}
}
