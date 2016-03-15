package adornos;

import dominio.Adorno;

public class Arbol extends Adorno{

	public Arbol(int costoAdquisicion, int puntosBellezaGenerados,int costoMantenimiento){
		
		super(costoAdquisicion, puntosBellezaGenerados, costoMantenimiento);
		
	}

	public boolean equals(Object obj){
		return (obj != null) && (obj instanceof Arbol);
	}
	
}
