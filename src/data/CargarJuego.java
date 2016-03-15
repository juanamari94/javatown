package data;

import gui.Tile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class CargarJuego {

	public boolean checkNombre(String nombre){
		
		GrabarJuego grabador = new GrabarJuego();
		grabador.checkearYCrearArchivoInexistente();
		grabador = null;
		
		try{
			
			FileReader fr = new FileReader("saves/savestates.txt");
			BufferedReader br = new BufferedReader(fr);

			String s, h = "";
			
			while((s = br.readLine()) != null)
				h += s + ";";
			
			String split[] = h.split(";");
			
			for(int i = 0; i < split.length; i++){
				
				if(split[i].equals(nombre)){
					br.close();
					return true;
				}
				
			}
			
			br.close();
			
			return false;
			
			
		} catch (Exception e){
			
			JOptionPane.showMessageDialog(null, "No se ha podido autenticar el archivo a abrir.");
			return false;
		}
		
		
	}
	
	public int[] getDatosCiudad(String nombre){
		
		int[] datos = new int[2]; // Esto es en caso que se encuentren valores corruptos.
		datos[0] = 10000;
		datos[1] = 0;
		
		try{
			
			crearBulkInexistente(nombre); // si no existe, que lo cree como si fuese un nuevo juego.
			FileReader fr = new FileReader("saves/" +nombre+ ".txt");
			BufferedReader br = new BufferedReader(fr);
			
			String s;
			
			while((s = br.readLine()) != null){
				
				if(s.contains("datosciudad:")){
					
					int firstIndex = s.indexOf(":");
					String arca = s.substring(firstIndex + 1, s.indexOf(";"));
					String puntosBelleza = s.substring(s.indexOf(";")+1);
					br.close();
					
					datos[0] = Integer.parseInt(arca);
					datos[1] = Integer.parseInt(puntosBelleza);
					
					return datos;
				}
			}
			
			br.close();
			return datos;
		
		} catch(Exception e){
		
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"No se ha podido recuperar la información del archivo. Posiblemente el archivo esté corrupto.");
		
		}
		
		return datos;
	}
	
	private void crearBulkInexistente(String nombre){
		
		File f = new File("saves/" + nombre + ".txt");
		
		if(!f.exists()){
			
			try{
				
			FileWriter fw = new FileWriter("saves/" + nombre + ".txt");
			BufferedWriter bw = new BufferedWriter(fw);
			
			Ciudad ciudad = new Ciudad();
			
			String s = "";
			
			for(int i = 0; i < 25; i++){
				for(int j = 0; j < 25; j++){
					s += "0"+";";
				}
			}
			
			bw.write("nombre:" + nombre + "\r\n");
			bw.write("datosciudad:" + ciudad.formatearDatosCiudad() + "\r\n");
			bw.write("dificultad:2" + "\r\n");
			bw.write("mapa:" + s + "\r\n");
			
			bw.close();
			
			}catch (Exception e){
				
				JOptionPane.showMessageDialog(null, "Ha ocurrido un problema durante el grabado del juego.");
				e.printStackTrace();
				
			}
		}
		
	}
	
	public Dificultad getDificultad(String nombre){
		
		try{
			
			crearBulkInexistente(nombre);
			
			FileReader fr = new FileReader("saves/" +nombre+ ".txt");
			BufferedReader br = new BufferedReader(fr);
			
			String s;
			
			while((s = br.readLine()) != null){
				
				if(s.contains("dificultad:")){
					
					String dif = s.substring(s.indexOf(":")+1);
					br.close();
					
					return new Dificultad(Integer.parseInt(dif));
					
				}
			}
			
			br.close();
			
			// Si no encontró la dificultad y no dió errores:
			return new Dificultad(2);
		} catch(Exception e){
			
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"No se ha podido recuperar la información de larchivo. Posiblemente el archivo esté corrupto.");
			
		}
		
		return new Dificultad(2);
		
	}
	
	public Tile[][] crearMapaTiles(String nombre){
		
		try{
			
			crearBulkInexistente(nombre);
			
			Tile[][] mapaTile = new Tile[25][25];
			
			FileReader fr = new FileReader("saves/" + nombre + ".txt");
			BufferedReader br = new BufferedReader(fr);
			
			String s;
			
			while((s = br.readLine()) != null){
				
				if(s.contains("mapa:")){
					
					String h = s.substring(s.indexOf(":") + 1);
					String mapa[] = h.split(";");
					mapaTile = relacionarMapa(mapa);
					br.close();
					return mapaTile;
					
				}
			}
			
			br.close();
			return mapaNulo();
		
		} catch(Exception e){
			
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"No se ha podido recuperar la información de larchivo. Posiblemente el archivo esté corrupto.");
			
		}
		
		return mapaNulo();
		
	}
	
	private Tile[][] relacionarMapa(String[] mapa){
		
		Tile[][] mapaTile = new Tile[25][25];
		
		int cont = 0;
		
		for(int i = 0; i < 25; i++){
			for(int j = 0; j < 25; j++){
				
				mapaTile[i][j] = new Tile(i, j, Integer.parseInt(mapa[cont]));
				cont++;
			}
		}
		
		return mapaTile;
		
	}
	
	private Tile[][] mapaNulo(){
		
		Tile[][] mapaTile = new Tile[25][25];
				
		for(int i = 0; i < 25; i++){
			for(int j = 0; j < 25; j++){
				
				mapaTile[i][j] = new Tile(i, j, 0);
			}
		}
		
		return mapaTile;
	}
	
}
