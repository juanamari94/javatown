package gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import models.CargarModel;
import utiles.SwingConsole;
import data.CargarJuego;
import data.Dificultad;
import data.GrabarJuego;
import data.LectorJuego;

public class MenuCargar extends JFrame{

	private JButton cargarBoton = new JButton("Cargar");
	private JButton salirBoton = new JButton("Salir");
	private CargarJuego cargador;
	
	private CargarModel model;
	
	private JList<String> listaSaves = new JList<String>();
	
	public MenuCargar(){
		
		setLayout(new GridLayout(1,2));
		
		JPanel panelOpcionesSave = new JPanel(new GridLayout(2,1));
		
		panelOpcionesSave.add(cargarBoton);
		panelOpcionesSave.add(salirBoton);
		
		cargarLista();
		
		add(listaSaves);
		
		add(panelOpcionesSave);
		
		salirBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				
				JFrame frame = conseguirRoot(ev);
				SwingConsole.run(new MenuInicio(), 300, 150, true);
				frame.dispose();
				
			}
		});
		
		cargarBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				
				cargador = new CargarJuego();
				
				boolean check = cargador.checkNombre(listaSaves.getSelectedValue());
				
				if(check){
					
					int datosCiudad[] = cargador.getDatosCiudad(listaSaves.getSelectedValue());
					Dificultad dificultad = cargador.getDificultad(listaSaves.getSelectedValue());
					Tile mapaTiles[][] = cargador.crearMapaTiles(listaSaves.getSelectedValue());
					
					SwingConsole.run(new GUIJuego(mapaTiles, datosCiudad, dificultad), 1024, 768, true);
					
					JFrame frame = conseguirRoot(ev);
					frame.dispose();
					
				}
				
			}
		});
		
	}
	
	private JFrame conseguirRoot(ActionEvent ev){
		
		Component component = (Component) ev.getSource();
		JFrame frame = (JFrame) SwingUtilities.getRoot(component);
		
		return frame;
	}
	
	private void cargarLista() {

		model = new CargarModel(formatearStatesCargados());

		listaSaves.setModel(model);
		listaSaves.setSelectedIndex(0);

	}
	
	private ArrayList<String> formatearStatesCargados(){
		
		LectorJuego lector = new LectorJuego();
		GrabarJuego grabador = new GrabarJuego();
		
		ArrayList<String> states = new ArrayList<String>();
		
		grabador.checkearYCrearArchivoInexistente();
		String s = lector.leerStates();
		String h[] = s.split("\n");
		
		for(int i = 0; i < h.length; i++){
			
			states.add(h[i]);
			
		}
		
		return states;
		
	}
}
