package it.marcoberri.santhiaapp.model;

public class PlaceGpsModel {

	private int place_id;
	private double lat;
	private double lng;
	
	public int getPlace_id() {
		return place_id;
	}

	public void setPlace_id(int place_id) {
		this.place_id = place_id;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}



	public String toString() {
		return "place_id: " + place_id + " - lat: " + lat + " - lng: " + lng;
	}

	public PlaceGpsModel() {
		super();
	}


}
