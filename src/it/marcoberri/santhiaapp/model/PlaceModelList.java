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

    @Override
    public String toString() {
	return "PlaceModelList [places=" + places + ", getPlaces()=" + getPlaces() + ", getVersion()=" + getVersion() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }


    
}
