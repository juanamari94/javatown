package data;

import gui.GUIJuego;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class GrabarJuego {
	
	public void grabarState(String state[]){
		
		try{
			
			FileWriter fw = new FileWriter("saves/savestates.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i = 0 ; i < state.length; i++){
				bw.write(state[i] + "\r\n");
			}
			
			bw.close();
			
		}catch(Exception e){
			
			JOptionPane.showMessageDialog(null, "Hubo un problema grabando.");
		}
	}
	
	public void grabarBulk(String nombre, Ciudad ciudad, GUIJuego juego){
		
		try{
		
		FileWriter fw = new FileWriter("saves/" + nombre + ".txt");
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write("nombre:" + nombre + "\r\n");
		bw.write("datosciudad:" + ciudad.formatearDatosCiudad() + "\r\n");
		bw.write("dificultad:" + juego.getAdministrador().formatearDificultad() + "\r\n");
		bw.write("mapa:" + juego.formatearMapaTiles() + "\r\n");
		
		bw.close();
		
		}catch (Exception e){
			
			JOptionPane.showMessageDialog(null, "Ha ocurrido un problema durante el grabado del juego.");
			e.printStackTrace();
			
		}
		
	}
	
	public void checkearYCrearArchivoInexistente(){
		
		File f = new File("saves/savestates.txt");
		if(!f.exists()){
			
			String states[] = {"a","b","c","d","e"};
			grabarState(states);
			
		}
	}
	
}
