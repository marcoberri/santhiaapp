package it.marcoberri.santhiaapp.model;

/**
 * @author Marco Berri - marcoberri@gmail.com
 * 
 */

public class LeftListModel {

    private int id;
    private String title;
    private int image;

    public LeftListModel() {
	super();
    }

    public LeftListModel(String title, int image, int id) {
	super();
	this.title = title;
	this.image = image;
	this.id = id;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public int getImage() {
	return image;
    }

    public void setImage(int image) {
	this.image = image;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

}
