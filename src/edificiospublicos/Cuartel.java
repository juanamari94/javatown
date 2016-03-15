package edificiospublicos;

import dominio.EdificioPublico;

public class Cuartel extends EdificioPublico{

	public Cuartel(int costoAdquisicion, int habitantesGenerados, int costoMantenimiento) {
		
		super(costoAdquisicion, habitantesGenerados, costoMantenimiento);
	
	}
	
	public boolean equals(Object obj){
		
		return (obj != null) && (obj instanceof Cuartel);
		
	}

}
