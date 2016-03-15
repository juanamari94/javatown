package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class PanelEstado extends JPanel{
	
	private String valores = "";

	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		g.setColor(Color.BLACK);

		g.drawString(valores, 130, 20);
	}
	
	public void setValores(int arca, int puntosBelleza, int cantidadHabitantes, int cantidadHabitantesDisponibles, String ingresosEgresos){
		
		valores = "Arca: " + arca 
				+ "                    " 
				+ " Puntos de Belleza: " + puntosBelleza + 
				"                      " + 
				" Habitantes: " + 
				cantidadHabitantes + " / " 
				+ cantidadHabitantesDisponibles + "                    Ingresos / Egresos: " + ingresosEgresos;
	}
	
	public String getValores(){
		return valores;
	}
	
	public void update(Graphics g){
		repaint();
	}
	
	public Dimension getPreferredSize() {
	    return new Dimension(850, 30);
	}
	
	public PanelEstado(){
		setBorder(new LineBorder(Color.BLACK));
	}
	

}
