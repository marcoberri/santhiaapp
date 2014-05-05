package it.marcoberri.santhiaapp.model;

import java.util.Date;
import java.util.List;

/**
 * @author Marco Berri - marcoberri@gmail.com
 * 
 */

public class ExtraModel {

    private String name;
    private String stringValue;
    private String intValue;
    private String category;
    private Date ts;

    public String getCategory() {
	return category;
    }

    public void setCategory(String category) {
	this.category = category;
    }

    public Date getTs() {
	return ts;
    }

    public void setTs(Date ts) {
	this.ts = ts;
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
	return "ExtraModel [name=" + name + ", stringValue=" + stringValue + ", intValue=" + intValue + ", category=" + category + ", ts=" + ts + ", places=" + places + "]";
    }

    public ExtraModel() {
	super();
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getStringValue() {
	return stringValue;
    }

    public void setStringValue(String stringValue) {
	this.stringValue = stringValue;
    }

    public String getIntValue() {
	return intValue;
    }

    public void setIntValue(String intValue) {
	this.intValue = intValue;
    }

}
