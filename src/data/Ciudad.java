package data;

import java.util.ArrayList;

import dominio.Adorno;
import dominio.EdificioNoPublico;
import dominio.EdificioPublico;
import dominio.Item;


public class Ciudad {

	private ArrayList<Item> items;
	private int puntosBelleza, arca, cantidadHabitantes, cantidadHabitantesDisponibles, probabilidadExplosion;

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public int getPuntosBelleza() {
		return puntosBelleza;
	}

	public void setPuntosBelleza(int puntosBelleza) {
		this.puntosBelleza = puntosBelleza;
	}

	public int getArca() {
		return arca;
	}

	public void setArca(int arca) {
		this.arca = arca;
	}
	
	public int getProbabilidadExplosion() {
		return probabilidadExplosion;
	}

	public void setProbabilidadExplosion(int probabilidadExplosion) {
		this.probabilidadExplosion = probabilidadExplosion;
	}

	public int getCantidadHabitantes() {
		return cantidadHabitantes;
	}

	public void setCantidadHabitantes(int cantidadHabitantes) {
		this.cantidadHabitantes = cantidadHabitantes;
	}
	
	public int getCantidadHabitantesDisponibles() {
		return cantidadHabitantesDisponibles;
	}

	public void setCantidadHabitantesDisponibles(int cantidadHabitantesDisponibles) {
		this.cantidadHabitantesDisponibles = cantidadHabitantesDisponibles;
	}

	
	public String getIngresosEgresosTotales(){
		
		int ingresos = 0, egresos = 0;
		
		String total = "";
		
		for(Item i : items){
			
			if(i != null){
				if(i instanceof EdificioPublico){
					egresos += ((EdificioPublico)i).getCostoMantenimiento();
				} else if (i instanceof EdificioNoPublico){
					ingresos += ((EdificioNoPublico)i).getRenta();
				} else if(i instanceof Adorno){
					egresos += ((Adorno)i).getCostoMantenimiento();
				}
			}
		}
		
		total = "" + ingresos + " / " + egresos;
		
		return total;
	}
	
	/* Constructores */

	public Ciudad(ArrayList<Item> items, int puntosBelleza, int arca, int cantidadHabitantes, int probabilidadExplosion, int cantidadHabitantesDisponibles) {
		
		this.setItems(items);
		this.setPuntosBelleza(puntosBelleza);
		this.setArca(arca);
		this.setCantidadHabitantes(cantidadHabitantes);
		this.setProbabilidadExplosion(probabilidadExplosion);
		this.setCantidadHabitantesDisponibles(cantidadHabitantesDisponibles);
		
	}
	
	public Ciudad(){
		this.setItems(new ArrayList<Item>());
		this.setPuntosBelleza(0);
		this.setArca(10000);
		this.setCantidadHabitantes(10);
		this.setProbabilidadExplosion(1);
		this.setCantidadHabitantesDisponibles(this.getCantidadHabitantes());
	}
	
	public String formatearDatosCiudad(){
		
		return this.getArca() + ";" + this.getPuntosBelleza();
		
	}
}