package data;

public class Dificultad {

	private int multiplicador;

	public int getMultiplicador() {
		return multiplicador;
	}

	public void setMultiplicador(int multiplicador) {
		this.multiplicador = multiplicador;
	}
	
	public Dificultad(int m){
		this.setMultiplicador(m);
	}
	
	// Adornos
	
	public int getCostoMantenimientoArbol(){
		return 150/this.getMultiplicador();
	}
	
	public int getPuntosBellezaGeneradosArbol(){
		return 10*this.getMultiplicador();
	}
	
	public int getCostoAdquisicionArbol(){
		return 400/this.getMultiplicador();
	}
	
	// Edificios Publicos
	
	public int getCostoMantenimientoCuartel(){
		return 500/this.getMultiplicador();
	}
	
	public int getCostoMantenimientoComisaria(){
		return 750/this.getMultiplicador();
	}
	
	public int getCostoMantenimientoEscuela(){
		return 1000/this.getMultiplicador();
	}
	
	public int getHabitantesGeneradosCuartel(){
		return 6*this.getMultiplicador();
	}
	
	public int getHabitantesGeneradosComisaria(){
		return 10*this.getMultiplicador();
	}
	
	public int getHabitantesGeneradosEscuela(){
		return 14*this.getMultiplicador();
	}
	
	public int getCostoAdquisicionCuartel(){
		return 3000/this.getMultiplicador();
	}
	
	public int getCostoAdquisicionComisaria(){
		return 3500/this.getMultiplicador();
	}
	
	public int getCostoAdquisicionEscuela(){
		return 2500/this.getMultiplicador();
	}
	
	// Edificios No Publicos
	
	public int getRentaCasa(){
		return 350*this.getMultiplicador();
	}
	
	public int getRentaIndustria(){
		return 2100*this.getMultiplicador();
	}
	
	public int getRentaComercio(){
		return 700*this.getMultiplicador();
	}
	
	public int getCantidadHabitantesNecesariosCasa(){
		return 4/this.getMultiplicador();
	}
	
	public int getCantidadHabitantesNecesariosIndustria(){
		return 30/this.getMultiplicador();
	}
	
	public int getCantidadHabitantesNecesariosComercio(){
		return 9/this.getMultiplicador();
	}
	
	public int getCostoAdquisicionCasa(){
		return 1000/this.getMultiplicador();
	}
	
	public int getCostoAdquisicionComercio(){
		return 1500/this.getMultiplicador();
	}
	
	public int getCostoAdquisicionIndustria(){
		return 3000/this.getMultiplicador();
	}
	
	// Misc
	
	public int getProbabilidadExplosion(){
		return 6 / this.getMultiplicador();
	}
}
