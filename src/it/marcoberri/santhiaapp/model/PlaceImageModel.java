package it.marcoberri.santhiaapp.model;

public class PlaceImageModel {

	private int id;
	private String title;
	private String url;
	private String text;
	private String disclamer;

	public String toString() {
		return "id: " + id + " - title: " + title + " - url: " + url
				+ " - discalmer: " + disclamer + " - text: " + text;
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

}