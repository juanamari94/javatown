package data;

import gui.GUIJuego;
import gui.MenuInicio;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import utiles.SwingConsole;
import dominio.Adorno;
import dominio.EdificioNoPublico;
import dominio.EdificioPublico;
import dominio.Item;

public class Administrador {

	private Ciudad ciudad;
	private int contadorExplosion;
	private Dificultad dificultad;
	private Constructora constructora;
	private TimerTask taskJuego;
	private Timer timer;
	
	public Administrador(Ciudad ciudad, int contadorExplosion, Dificultad dificultad, Constructora constructora) {
		this.ciudad = ciudad;
		this.contadorExplosion = contadorExplosion;
		this.dificultad = dificultad;
		this.constructora = constructora;
	}
	
	public Administrador(){
		this.setCiudad(new Ciudad());
		this.setContadorExplosion(0);
		this.setDificultad(new Dificultad(1));
		this.setConstructora(new Constructora());
	}
	
	public void administrar(final GUIJuego juego){
		
		timer = new Timer("Juego");
		
		taskJuego = new TimerTask(){
			public void run() {
				
				if(getCiudad().getArca() >= 1000000 || getCiudad().getPuntosBelleza() >= 1000 || getCiudad().getCantidadHabitantes() >= 1000){
					
					this.cancel();
					int opcion=JOptionPane.showConfirmDialog(juego, "¡Ganaste! ¿Deseas volver al menú?", "Victoria", JOptionPane.YES_NO_OPTION);
					juego.getTimer().stop();
					
					accionFinJuego(opcion, juego);
					
				} else if(getCiudad().getArca() <= -10000) {
					
					this.cancel();
					int opcion=JOptionPane.showConfirmDialog(juego, "¡Perdiste! ¿Deseas volver al menú?", "Derrota", JOptionPane.YES_NO_OPTION);
					juego.getTimer().stop();
					
					accionFinJuego(opcion, juego);
					
				} else {
					getEgresosIngresos();
					juego.setEdificioDestruido(tryExplotar());
					if(juego.getEdificioDestruido() != null)
						juego.explotarEdificio(juego.getEdificioDestruido());
				}
			}
		};
		
		timer.scheduleAtFixedRate(taskJuego, 0, 1000);
			
		
	}
	
	private void getEgresosIngresos(){
		
		for(Item i : this.getCiudad().getItems()){
			
			if(i.getContadorTiempo() == 89){
				
				i.setContadorTiempo(0);
				
				if(i instanceof EdificioNoPublico)
					this.getCiudad().setArca(this.getCiudad().getArca() + ((EdificioNoPublico) i).getRenta());
				else
					this.getCiudad().setArca(this.getCiudad().getArca() - getCostosAdornosEdificiosPublicos(i));
				
			} else { i.setContadorTiempo(i.getContadorTiempo() + 1); }
		}
		
	}
	
	private int getCostosAdornosEdificiosPublicos(Item i){
		
		if(i instanceof EdificioPublico)
			return ((EdificioPublico) i).getCostoMantenimiento();
		else if(i instanceof Adorno)
			return ((Adorno) i).getCostoMantenimiento();
		
		return 0;
	}
	
	private Item tryExplotar(){
		
		if(this.getContadorExplosion() == 299 && this.getCiudad().getItems() != null){
			
			Random rnd = new Random();
			Item i = null;
			int numero = rnd.nextInt(100);
			if(numero < this.getCiudad().getProbabilidadExplosion() && this.getCiudad().getItems().size() > 0){
				
				int numEdificioExplotado = rnd.nextInt(this.getCiudad().getItems().size());
				i = this.getCiudad().getItems().get(numEdificioExplotado);
				constructora.borrarItem(i, this.getCiudad());
				JOptionPane.showMessageDialog(null,"¡Ha explotado un edificio!");
				this.getCiudad().getItems().remove(i);
				this.setContadorExplosion(0);
				
			}
				rnd = null;
				return i;
			
		}else{ 
			
			this.setContadorExplosion(this.getContadorExplosion() + 1);
			return null;
			
		}
		
	}

	// ### Getters y Setters ###
	
	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public int getContadorExplosion() {
		return contadorExplosion;
	}

	public void setContadorExplosion(int contadorExplosion) {
		this.contadorExplosion = contadorExplosion;
	}
	
	
	public Dificultad getDificultad() {
		return dificultad;
	}

	public void setDificultad(Dificultad dificultad) {
		this.dificultad = dificultad;
	}

	public Constructora getConstructora() {
		return constructora;
	}

	public void setConstructora(Constructora constructora) {
		this.constructora = constructora;
	}

	private void accionFinJuego(int opcion, GUIJuego juego){
		
		if(opcion == JOptionPane.YES_OPTION){
			
			GUIJuego.killThemAll(juego);
			SwingConsole.run(new MenuInicio(), 300, 150, true);
			
		}else if(opcion == JOptionPane.NO_OPTION){
			
			System.exit(0);
			
		} else {
			
			System.exit(0);
			
		}
	}
	
	public void pause(GUIJuego juego){
	
		timer.cancel();
		juego.getTimer().stop();
		juego.deshabilitar();
		
	}
	
	public void resume(GUIJuego juego){
		
		juego.getAdministrador().administrar(juego);
		juego.getTimer().start();
		juego.habilitar();
		
	}
	
	
	public String formatearDificultad(){
		
		switch(dificultad.getMultiplicador()){
			
			case 1:
				return "1";
			case 2:
				return "2";
			case 3:
				return "3";
			default:
				return "2";
		
		}
		
	}
	
}