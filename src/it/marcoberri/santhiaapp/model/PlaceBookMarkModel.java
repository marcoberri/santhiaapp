package it.marcoberri.santhiaapp.model;

import java.util.Date;

public class PlaceBookMarkModel {

	private int place_id;
	private Date ts;

	public String toString() {
		return " place_id: " + place_id + " - ts: " + ts;
	}

	public PlaceBookMarkModel(int id, int place_id, Date ts) {
		super();
		this.place_id = place_id;
		this.ts = ts;
	}

	public PlaceBookMarkModel() {
		super();
	}

	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}

	public int getPlaceId() {
		return place_id;
	}

	public void setPlaceId(int place_id) {
		this.place_id = place_id;
	}
	

}
