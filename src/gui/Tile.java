package gui;

import javax.swing.JLabel;

public class Tile extends JLabel{

	private int fila, columna, tipo;

	public int getFila() {
		return fila;
	}

	public void setFila(int x) {
		this.fila = x;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int y) {
		this.columna = y;
	}
	
	public int getTipo(){
		return tipo;
	}
	
	public void setTipo(int tipo){
		if(tipo >= 0 || tipo <= 7) // antes era 6
			this.tipo = tipo;
	}

	public Tile(int x, int y, int tipo) {
		this.setFila(x);
		this.setColumna(y);
		this.setTipo(tipo);
	}
	
	public String toString(){
		return "X:" + this.getFila() + " Y: " + this.getColumna() + " Tipo: " + this.getTipo();
	}
}
