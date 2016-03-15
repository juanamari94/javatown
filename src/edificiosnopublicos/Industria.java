package edificiosnopublicos;

import dominio.EdificioNoPublico;

public class Industria extends EdificioNoPublico{

	public Industria(int costoAdquisicion, int habitantesNecesarios, int renta) {
		
		super(costoAdquisicion, habitantesNecesarios, renta);
		
	}

	public boolean equals(Object obj){
		
		return (obj != null) && (obj instanceof Industria);
		
	}
	
}
