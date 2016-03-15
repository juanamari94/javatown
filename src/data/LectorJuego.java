package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class LectorJuego {

	
	public String leerStates(){
		
		try{
			
			FileReader fr = new FileReader("saves/savestates.txt");
			BufferedReader br = new BufferedReader(fr);
			String s, states="";
			
			int cont=0;
			
			while((s = br.readLine()) != null && cont < 5){
				states += s + "\n";
				cont++;
			}
			
			br.close();
			return states;
			
		}catch(Exception e){
			
			JOptionPane.showMessageDialog(null, "Ha habido un problema recuperando los estados de guardado.");
			e.printStackTrace();
			return null;
			
		}
	}
	
	public void borrarStatesDemas(){
		
		try{
			
			String s = this.leerStates();
			FileWriter fw = new FileWriter("saves/savestates.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			
			String h[] = s.split("\n");
			
			for(int i = 0; i < h.length; i++){
				bw.write(h[i] + "\r\n");
			}
			
			bw.close();
		} catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha habido un problema recuperando los estados de guardado");
		}
	}
	
}
