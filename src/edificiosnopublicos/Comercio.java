package edificiosnopublicos;

import dominio.EdificioNoPublico;

public class Comercio extends EdificioNoPublico{

	public Comercio(int costoAdquisicion, int habitantesNecesarios, int renta) {
		
		super(costoAdquisicion, habitantesNecesarios, renta);
		
	}
	
	public boolean equals(Object obj){
		
		return (obj != null) && (obj instanceof Comercio);
		
	}
	
}
