package it.marcoberri.santhiaapp.model;

/**
 * @author Marco Berri - marcoberri@gmail.com
 * 
 */

public class PlaceImageModel {

    private int id;
    private String title;
    private String url;
    private String text;
    private String disclamer;
    private int placeId;



    @Override
    public String toString() {
	return "PlaceImageModel [id=" + id + ", title=" + title + ", url=" + url + ", text=" + text + ", disclamer=" + disclamer + ", placeId=" + placeId + "]";
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public PlaceImageModel() {
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

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public String getDisclamer() {
	return disclamer;
    }

    public void setDisclamer(String disclamer) {
	this.disclamer = disclamer;
    }

    public int getPlaceId() {
	return placeId;
    }

    public void setPlaceId(int placeId) {
	this.placeId = placeId;
    }

}
