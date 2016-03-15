package models;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

public class CargarModel extends DefaultListModel<String>{

	public CargarModel(ArrayList<String> states){
		
		for(String s : states){
			addElement(s);
		}
	}
	
}
