package edificiosnopublicos;

import dominio.EdificioNoPublico;

public class Casa extends EdificioNoPublico{

	public Casa(int costoAdquisicion, int habitantesNecesarios, int renta) {
		
		super(costoAdquisicion, habitantesNecesarios, renta);
		
	}
	
	public boolean equals(Object obj){
		
		return (obj != null) && (obj instanceof Casa);
		
	}
	
}
