package it.marcoberri.santhiaapp.model;

import java.util.List;

/**
 * @author Marco Berri - marcoberri@gmail.com
 *
 */

public class PlaceModel {

	private int id;
	private String title;
	private String subtitle;
	private int image;
	private String text;
	private String locale;
	private List<PlaceImageModel> images;
	private PlaceGpsModel gps;
	private String address;

	public String toString() {
		return "id: " + id + " - title: " + title + " - subtitle: " + subtitle
				+ " - image: " + image + " - images: " + images + " locale: " + locale + "address"+address;
	}

	public PlaceModel(String title, String subtitle, int image, int id, String address,String locale) {
		super();
		this.title = title;
		this.subtitle = subtitle;
		this.image = image;
		this.id = id;
		this.locale = locale;
		this.address = address;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public PlaceModel() {
		super();
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setImages(List<PlaceImageModel> images) {
		this.images = images;
	}

	public List<PlaceImageModel> getImages() {
		return this.images;
	}

	public String getLocale(){
		return this.locale;
	}
	
	public void setLocale(String locale){
		this.locale = locale;
	}

	public PlaceGpsModel getGps() {
		return gps;
	}

	public void setGps(PlaceGpsModel gps) {
		this.gps = gps;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

}
