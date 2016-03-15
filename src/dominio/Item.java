package dominio;

public abstract class Item {

	private int costoAdquisicion, contadorTiempo;

	public int getCostoAdquisicion() {
		return costoAdquisicion;
	}

	public void setCostoAdquisicion(int costoAdquisicion) {
		this.costoAdquisicion = costoAdquisicion;
	}

	public int getContadorTiempo() {
		return contadorTiempo;
	}

	public void setContadorTiempo(int contadorTiempo) {
		this.contadorTiempo = contadorTiempo;
	}

	public Item(int costoAdquisicion) {
		contadorTiempo = 0;
		this.costoAdquisicion = costoAdquisicion;
	}
	
	
	
	
	

}
