package dominio;

public abstract class EdificioNoPublico extends Item {

	private int habitantesNecesarios, renta;

	public int getHabitantesNecesarios() {
		return habitantesNecesarios;
	}

	public void setHabitantesNecesarios(int habitantesNecesarios) {
		this.habitantesNecesarios = habitantesNecesarios;
	}


	public int getRenta() {
		return renta;
	}

	public void setRenta(int renta) {
		this.renta = renta;
	}

	public EdificioNoPublico(int costoAdquisicion, int habitantesNecesarios, int renta) {
		
		super(costoAdquisicion);
		this.setHabitantesNecesarios(habitantesNecesarios);
		this.setRenta(renta);
	}

	
	
	

}
