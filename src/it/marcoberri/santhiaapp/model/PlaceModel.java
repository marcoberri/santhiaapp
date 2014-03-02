package it.marcoberri.santhiaapp.model;

import java.util.List;

public class PlaceModel {

	private int id;
	private String title;
	private String subtitle;
	private int image;
	private String text;
	private GpsData gps;
	private List<Image> images;

	public String toString(){
		return "id: " + id + " - title: " + title+ " - subtitle: " + subtitle +" - image: " + image + " - images: " + images + " - gpsdata: " + gps;
	}
	
	public PlaceModel(String title, String subtitle, int image, int id) {
		super();
		this.title = title;
		this.subtitle = subtitle;
		this.image = image;
		this.id = id;
	}
	
	class GpsData {
		private float lat;
		private float lng;

		public float getLat() {
			return lat;
		}

		public void setLat(float lat) {
			this.lat = lat;
		}

		public float getLng() {
			return lng;
		}

		public void setLng(float lng) {
			this.lng = lng;
		}
		
		public String toString(){
			return "lng : "+lng + " - lat:" + lat;
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public GpsData getGps() {
		return gps;
	}

	public void setGps(GpsData gps) {
		this.gps = gps;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	class Image {
		private String url;
		private String title;
		private int id;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
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

		public String toString(){
			return "id : "+id + " - title:" + title + " - url:" + url;
		}

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

}
