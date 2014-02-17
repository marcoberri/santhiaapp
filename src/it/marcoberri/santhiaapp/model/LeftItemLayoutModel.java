package it.marcoberri.santhiaapp.model;

public class LeftItemLayoutModel {
	
	private String name;
	private int id;
	private int imageResource;
	
	public LeftItemLayoutModel(String name, int id, int imageResource){
		this.setName(name);
		this.setId(id);
		this.setImageResource(imageResource);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getImageResource() {
		return imageResource;
	}

	public void setImageResource(int imageResource) {
		this.imageResource = imageResource;
	}

}
