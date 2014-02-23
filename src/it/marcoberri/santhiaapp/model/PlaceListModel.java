package it.marcoberri.santhiaapp.model;

public class PlaceListModel {

	private String title;
	private String subtitle;
	private int image;
	
	public PlaceListModel() {
	super();
	}
	public PlaceListModel(String title, String subtitle, int image) {
		super();
	this.title = title;
	this.subtitle=subtitle;
	this.image=image;
	
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}
	
}
