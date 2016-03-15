package edificiospublicos;

import dominio.EdificioPublico;

public class Comisaria extends EdificioPublico{

	public Comisaria(int costoAdquisicion, int habitantesGenerados, int costoMantenimiento) {
		
			super(costoAdquisicion, habitantesGenerados, costoMantenimiento);

	}

	public boolean equals(Object obj){
		
		return (obj != null) && (obj instanceof Comisaria);
		
	}
	
}
