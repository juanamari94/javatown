package data;


import adornos.Arbol;
import dominio.Adorno;
import dominio.EdificioNoPublico;
import dominio.EdificioPublico;
import dominio.Item;
import edificiosnopublicos.Casa;
import edificiosnopublicos.Comercio;
import edificiosnopublicos.Industria;
import edificiospublicos.Comisaria;
import edificiospublicos.Cuartel;
import edificiospublicos.Escuela;
import gui.GUIJuego;

public class Constructora {
	
	// Edificios Publicos
	
	public void construirCuartel(Dificultad d, Ciudad c){
		if(c != null)
			cargarItem(new Cuartel(d.getCostoAdquisicionCuartel(), d.getHabitantesGeneradosCuartel(), d.getCostoMantenimientoCuartel()), c);
	}
	
	public void construirComisaria(Dificultad d, Ciudad c){
		if(c != null)
			cargarItem(new Comisaria(d.getCostoAdquisicionComisaria(), d.getHabitantesGeneradosComisaria(), d.getCostoMantenimientoComisaria()), c);
	}
	
	public void construirEscuela(Dificultad d, Ciudad c){
		if(c != null)
			cargarItem(new Escuela(d.getCostoAdquisicionEscuela(), d.getHabitantesGeneradosEscuela(), d.getCostoMantenimientoEscuela()), c);
	}
	
	// Adornos
	
	public void construirArbol(Dificultad d, Ciudad c){
		if(c != null)
			cargarItem(new Arbol(d.getCostoAdquisicionArbol(), d.getPuntosBellezaGeneradosArbol(), d.getCostoMantenimientoArbol()), c);
	}
	
	// Edificios No Publicos
	
	public void construirCasa(Dificultad d, Ciudad c){
		if(c != null)
			cargarItem(new Casa(d.getCostoAdquisicionCasa(), d.getCantidadHabitantesNecesariosCasa(), d.getRentaCasa()), c);
	}
	
	public void construirIndustria(Dificultad d, Ciudad c){
		if(c != null)
			cargarItem(new Industria(d.getCostoAdquisicionIndustria(), d.getCantidadHabitantesNecesariosIndustria(), d.getRentaIndustria()), c);
	}
	
	public void construirComercio(Dificultad d, Ciudad c){
		if(c != null)
			cargarItem(new Comercio(d.getCostoAdquisicionComercio(), d.getCantidadHabitantesNecesariosComercio(), d.getRentaComercio()), c);
	}
	
	// La constructora también hace servicios de demolición.
	
	// Edificios Publicos
	
	public void destruirCuartel(Cuartel cu, Ciudad c){
		
		if(c != null){
			borrarItem(cu, c);
			c.getItems().remove(cu);
		}
		
	}
	
	public void destruirComisaria(Comisaria co, Ciudad c){
		if(c != null){
			borrarItem(co, c);
			c.getItems().remove(co);
		}
	}
	
	public void destruirEscuela(Escuela es, Ciudad c){
		if(c != null){
			borrarItem(es, c);
			c.getItems().remove(es);
		}
	}
	
	// Edificios No Publicos
	
	public void destruirCasa(Casa ca, Ciudad c){
		if(c != null){
			borrarItem(ca, c);
			c.getItems().remove(ca);
		}
	}
	
	public void destruirComercio(Comercio co, Ciudad c){
		if(c != null){
			borrarItem(co, c);
			c.getItems().remove(co);
		}
	}
	
	public void destruirIndustria(Industria i, Ciudad c){
		if(c != null){
			borrarItem(i, c);
			c.getItems().remove(i);
		}
	}
	
	// Adornos
	
	public void destruirArbol(Adorno a, Ciudad c){
		if(c != null){
			borrarItem(a, c);
			c.getItems().remove(a);
		}
	}
	
	// Cargador
	
	private void cargarItem(Item i, Ciudad c){
		if(!GUIJuego.isLoadedGame()){
			
			if(i != null && confirmarConstruccion(i,c)){
					c.getItems().add(i);
					c.setArca(c.getArca() - i.getCostoAdquisicion());
					if(i instanceof EdificioPublico){
						c.setCantidadHabitantes(c.getCantidadHabitantes() + ((EdificioPublico)i).getHabitantesGenerados());
						c.setCantidadHabitantesDisponibles(c.getCantidadHabitantesDisponibles() + ((EdificioPublico)i).getHabitantesGenerados());
					}else if(i instanceof EdificioNoPublico){
						c.setCantidadHabitantesDisponibles(c.getCantidadHabitantesDisponibles() - ((EdificioNoPublico)i).getHabitantesNecesarios());
					}else if(i instanceof Adorno){
						c.setPuntosBelleza(c.getPuntosBelleza() + ((Adorno)i).getPuntosBellezaGenerados());
					}
			}
					
			} else {
			
				if(i != null){
					c.getItems().add(i);
					if(i instanceof EdificioPublico){
						c.setCantidadHabitantes(c.getCantidadHabitantes() + ((EdificioPublico)i).getHabitantesGenerados());
						c.setCantidadHabitantesDisponibles(c.getCantidadHabitantesDisponibles() + ((EdificioPublico)i).getHabitantesGenerados());
					}else if(i instanceof EdificioNoPublico){
						c.setCantidadHabitantesDisponibles(c.getCantidadHabitantesDisponibles() - ((EdificioNoPublico)i).getHabitantesNecesarios());
					}
				}
		}
	}
	
	public boolean confirmarConstruccion(Item i, Ciudad c){
		if(i != null){
			if(c.getArca() - i.getCostoAdquisicion() >= 0 && i instanceof EdificioNoPublico && ((EdificioNoPublico)i).getHabitantesNecesarios() <= c.getCantidadHabitantesDisponibles() )
				return true;
			else if(i instanceof EdificioPublico && c.getArca() - i.getCostoAdquisicion() >= 0)
				return true;
			else if(i instanceof Adorno)
				return true;
		}
		return false;
	}
	
	public void borrarItem(Item i, Ciudad c){
		
		if(i != null){
			if(i instanceof EdificioPublico){
				c.setCantidadHabitantes(c.getCantidadHabitantes() - ((EdificioPublico)i).getHabitantesGenerados());
				c.setCantidadHabitantesDisponibles(c.getCantidadHabitantesDisponibles() - ((EdificioPublico)i).getHabitantesGenerados());
			}else if(i instanceof EdificioNoPublico){
				c.setCantidadHabitantesDisponibles(c.getCantidadHabitantesDisponibles() + ((EdificioNoPublico)i).getHabitantesNecesarios());
			}else if(i instanceof Adorno){
				c.setPuntosBelleza(c.getPuntosBelleza() - ((Adorno)i).getPuntosBellezaGenerados());
			}
		}
	}
}