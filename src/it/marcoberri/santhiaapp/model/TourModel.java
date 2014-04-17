package it.marcoberri.santhiaapp.model;

import java.util.List;


public class TourModel {

	private int id;
	private String title;
	private int vote;
	
	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public List<PlaceModel> getPlaces() {
		return places;
	}

	public void setPlaces(List<PlaceModel> places) {
		this.places = places;
	}

	private List<PlaceModel> places;

	public String toString() {
		return "id: " + id + " - title: " + title + 
				  "places" + places;
	}

	public TourModel(int id, String title) {
		super();
		this.title = title;
		this.id = id;

	}


	public TourModel() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	

}
