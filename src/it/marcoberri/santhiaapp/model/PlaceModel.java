package it.marcoberri.santhiaapp.model;

import java.util.List;

public class PlaceModel {

	private int id;
	private String title;
	private String subtitle;
	private int image;
	private String text;
	private List<PlaceImageModel> images;

	public String toString() {
		return "id: " + id + " - title: " + title + " - subtitle: " + subtitle
				+ " - image: " + image + " - images: " + images;
	}

	public PlaceModel(String title, String subtitle, int image, int id) {
		super();
		this.title = title;
		this.subtitle = subtitle;
		this.image = image;
		this.id = id;
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

}
