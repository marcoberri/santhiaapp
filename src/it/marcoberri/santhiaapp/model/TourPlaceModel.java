package it.marcoberri.santhiaapp.model;

/**
 * @author Marco Berri - marcoberri@gmail.com
 * 
 */

public class TourPlaceModel {

    private int tour_id;
    private int place_id;



    public TourPlaceModel(int tour_id, int place_id) {
	super();
	this.tour_id = tour_id;
	this.place_id = place_id;
    }

    public int getTour_id() {
	return tour_id;
    }

    public void setTour_id(int tour_id) {
	this.tour_id = tour_id;
    }

    public int getPlace_id() {
	return place_id;
    }

    public void setPlace_id(int place_id) {
	this.place_id = place_id;
    }

    @Override
    public String toString() {
	return "TourPlaceModel [tour_id=" + tour_id + ", place_id=" + place_id + ", getTour_id()=" + getTour_id() + ", getPlace_id()=" + getPlace_id() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }

}
