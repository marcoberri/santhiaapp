package it.marcoberri.santhiaapp.model;

import java.util.List;

/**
 * @author Marco Berri - marcoberri@gmail.com
 * 
 */

public class TourModel {

    private int id;
    private String title;
    private int vote;
    private int community;

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



    @Override
    public String toString() {
	return "TourModel [id=" + id + ", title=" + title + ", vote=" + vote + ", community=" + community + ", places=" + places + ", getVote()=" + getVote() + ", getPlaces()=" + getPlaces() + ", getTitle()=" + getTitle() + ", getId()=" + getId() + ", getCommunity()=" + getCommunity() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
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

    public int getCommunity() {
	return community;
    }

    public void setCommunity(int community) {
	this.community = community;
    }

}
