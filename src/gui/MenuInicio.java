package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import utiles.SwingConsole;

public class MenuInicio extends JFrame{

	private JLabel accion = new JLabel("Selecione una Acción");
	
	private JButton botonNuevo = new JButton("Nuevo Juego");
	private JButton botonCargar = new JButton("Cargar Juego");
	
	public MenuInicio(){
		
		accion.setHorizontalAlignment(SwingConstants.CENTER);
		add(accion,BorderLayout.NORTH);
		
		JPanel panelBotones = new JPanel(new GridLayout(1,2));
		panelBotones.add(botonNuevo);
		panelBotones.add(botonCargar);
		add(panelBotones);
		
		botonNuevo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				
				Component component = (Component) ev.getSource();
				JFrame frame = (JFrame) SwingUtilities.getRoot(component);
				inicializarMenuJuegoNuevo(frame);
				
			}
		});
		
		botonCargar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				
				File saves = new File("saves");
				if(!saves.exists()){
					saves.mkdir();
				}
				
				JFrame frame = conseguirRoot(ev);
				SwingConsole.run(new MenuCargar(), 300, 200, true);
				frame.dispose();
			}
		});
		
		
	}
	
	private void inicializarMenuJuegoNuevo(JFrame j){
		
		SwingConsole.run(new MenuNuevoJuego(), 300, 150, true);
		j.dispose();
	}
	
	private JFrame conseguirRoot(ActionEvent ev){
		
		Component component = (Component) ev.getSource();
		JFrame frame = (JFrame) SwingUtilities.getRoot(component);
		
		return frame;
	}
}
