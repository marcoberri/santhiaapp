package it.marcoberri.santhiaapp.controller;

import java.util.ArrayList;
import java.util.List;

import it.marcoberri.santhiaapp.model.LeftItemLayoutModel;

public class LeftItemLayoutController {

	
	public static List<LeftItemLayoutModel> getList(){
		ArrayList<LeftItemLayoutModel> l = new ArrayList<LeftItemLayoutModel>();
		l.add(new LeftItemLayoutModel("a",1,1));
		l.add(new LeftItemLayoutModel("b",2,2));
		l.add(new LeftItemLayoutModel("c",3,3));
		return l;
	}
	
}
