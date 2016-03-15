package dominio;

public abstract class EdificioPublico extends Item {

	private int habitantesGenerados, costoMantenimiento;

	public int getHabitantesGenerados() {
		return habitantesGenerados;
	}

	public void setHabitantesGenerados(int habitantesGenerados) {
		this.habitantesGenerados = habitantesGenerados;
	}

	public int getCostoMantenimiento() {
		return costoMantenimiento;
	}

	public void setCostoMantenimiento(int costoMantenimiento) {
		this.costoMantenimiento = costoMantenimiento;
	}

	public EdificioPublico(int costoAdquisicion,int habitantesGenerados, int costoMantenimiento) {
		
		super(costoAdquisicion);
		this.setCostoMantenimiento(costoMantenimiento);
		this.setHabitantesGenerados(habitantesGenerados);
		
	}

}
