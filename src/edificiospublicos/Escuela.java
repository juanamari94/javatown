package edificiospublicos;

import dominio.EdificioPublico;

public class Escuela extends EdificioPublico{

	public Escuela(int costoAdquisicion, int habitantesGenerados, int costoMantenimiento) {
		
		super(costoAdquisicion, habitantesGenerados, costoMantenimiento);

	}
	
	public boolean equals(Object obj){
		
		return (obj != null) && (obj instanceof Escuela);
		
	}

}
