package data;

import adornos.Arbol;
import dominio.Item;
import edificiosnopublicos.Casa;
import edificiosnopublicos.Comercio;
import edificiosnopublicos.Industria;
import edificiospublicos.Comisaria;
import edificiospublicos.Cuartel;
import edificiospublicos.Escuela;

public class Transaccion {
	
	private static Transaccion instance;
	
	public static Transaccion getInstance(){
		if(instance == null)
			instance = new Transaccion();
		return instance;

	}

	public void destruirEdificioEnLista(Administrador admin, int tipo){
		
		Dificultad d = admin.getDificultad();
		Ciudad c = admin.getCiudad();
		
		
		switch(tipo){
			
		case 1:
			
			admin.getConstructora().destruirComisaria(new Comisaria(d.getCostoAdquisicionComisaria(), d.getHabitantesGeneradosComisaria(), d.getCostoMantenimientoComisaria()), c);
			break;
		
		case 2:
			admin.getConstructora().destruirCuartel(new Cuartel(d.getCostoAdquisicionCuartel(), d.getHabitantesGeneradosCuartel(), d.getCostoMantenimientoCuartel()), c);
			break;
			
		case 3:
			admin.getConstructora().destruirEscuela(new Escuela(d.getCostoAdquisicionEscuela(), d.getHabitantesGeneradosEscuela(), d.getCostoMantenimientoEscuela()), c);
			break;
		
		case 4:
			admin.getConstructora().destruirArbol(new Arbol(d.getCostoAdquisicionArbol(), d.getPuntosBellezaGeneradosArbol(), d.getCostoMantenimientoArbol()), c);
			break;
		
		case 5:
			admin.getConstructora().destruirCasa(new Casa(d.getCostoAdquisicionCasa(), d.getCantidadHabitantesNecesariosCasa(), d.getRentaCasa()), c);
			break;
			
		case 6:
			admin.getConstructora().destruirComercio(new Comercio(d.getCostoAdquisicionComercio(), d.getCantidadHabitantesNecesariosComercio(), d.getRentaComercio()), c);
			break;
		
		case 7:
			admin.getConstructora().destruirIndustria(new Industria(d.getCostoAdquisicionIndustria(), d.getCantidadHabitantesNecesariosIndustria(), d.getRentaIndustria()), c);
			break;
		}
		
	}
	
	public boolean checkTipoParaConstruccion(Administrador admin, int tipo){
		
		Dificultad d = admin.getDificultad();
		Ciudad c = admin.getCiudad();
		
		switch(tipo){
		case 1:
			
				return admin.getConstructora().confirmarConstruccion(new Comisaria(d.getCostoAdquisicionComisaria(), d.getHabitantesGeneradosComisaria(), d.getCostoMantenimientoComisaria()), c);
			
		case 2:
			
				return admin.getConstructora().confirmarConstruccion(new Cuartel(d.getCostoAdquisicionCuartel(), d.getHabitantesGeneradosCuartel(), d.getCostoMantenimientoCuartel()), c);
			
		case 3:
			
				return admin.getConstructora().confirmarConstruccion(new Escuela(d.getCostoAdquisicionEscuela(), d.getHabitantesGeneradosEscuela(), d.getCostoMantenimientoEscuela()), c);
			
		case 4:

				return admin.getConstructora().confirmarConstruccion(new Arbol(d.getCostoAdquisicionArbol(), d.getPuntosBellezaGeneradosArbol(), d.getCostoMantenimientoArbol()), c);

		case 5:

				return admin.getConstructora().confirmarConstruccion(new Casa(d.getCostoAdquisicionCasa(), d.getCantidadHabitantesNecesariosCasa(), d.getRentaCasa()), c);

		case 6:
			
				return admin.getConstructora().confirmarConstruccion(new Comercio(d.getCostoAdquisicionComercio(), d.getCantidadHabitantesNecesariosComercio(), d.getRentaComercio()), c);

		case 7:

				return admin.getConstructora().confirmarConstruccion(new Industria(d.getCostoAdquisicionIndustria(), d.getCantidadHabitantesNecesariosIndustria(), d.getRentaIndustria()), c);

		default:
			
				return false;
		}
		
	}

	public int checkInstanceOfItem(Item item) {
	
		if(item != null){
			if(item instanceof Comisaria)
				return 1;
			else if(item instanceof Cuartel)
				return 2;
			else if(item instanceof Escuela)
				return 3;
			else if(item instanceof Arbol)
				return 4;
			else if(item instanceof Casa)
				return 5;
			else if(item instanceof Comercio)
				return 6;
			else if(item instanceof Industria)
				return 7;
		}
		
		return 0; // Si no es ninguno, que devuelva 0.
		
		
	}
	
}
