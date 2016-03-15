package dominio;

public abstract class Adorno extends Item {

	private int puntosBellezaGenerados, costoMantenimiento;

	public int getPuntosBellezaGenerados() {
		return puntosBellezaGenerados;
	}

	public void setPuntosBellezaGenerados(int puntosBellezaGenerados) {
		this.puntosBellezaGenerados = puntosBellezaGenerados;
	}

	public int getCostoMantenimiento() {
		return costoMantenimiento;
	}

	public void setCostoMantenimiento(int costoMantenimiento) {
		this.costoMantenimiento = costoMantenimiento;
	}

	public Adorno(int costoAdquisicion, int puntosBellezaGenerados, int costoMantenimiento) {
		super(costoAdquisicion);
		this.setPuntosBellezaGenerados(puntosBellezaGenerados);
		this.setCostoMantenimiento(costoMantenimiento);
	}

}
