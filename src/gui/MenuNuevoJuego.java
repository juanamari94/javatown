package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import utiles.SwingConsole;

public class MenuNuevoJuego extends JFrame{

	private JButton botonComenzar = new JButton("Comenzar");
	private JButton botonCancelar = new JButton("Cancelar");
	
	private JLabel dificultadLabel = new JLabel("Dificultad");
	private JRadioButton dificultadFacilRB = new JRadioButton("Facil");
	private JRadioButton dificultadNormalRB = new JRadioButton("Normal");
	private JRadioButton dificultadDificilRB = new JRadioButton("Dificil");
	
	ButtonGroup grupoDificultad = new ButtonGroup();
	
	public MenuNuevoJuego(){
		
		this.setVisible(true);
		this.setSize(400,200);
		
		grupoDificultad.add(dificultadFacilRB);
		grupoDificultad.add(dificultadNormalRB);
		grupoDificultad.add(dificultadDificilRB);
		dificultadNormalRB.setSelected(true);
		
		JPanel panelDificultades = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.anchor = GridBagConstraints.WEST;
		panelDificultades.add(dificultadFacilRB);
		panelDificultades.add(dificultadNormalRB);
		panelDificultades.add(dificultadDificilRB);
		add(panelDificultades, BorderLayout.CENTER);
		
		dificultadLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(dificultadLabel, BorderLayout.NORTH);
		
		JPanel panelBotones = new JPanel(new GridLayout(1,2));
		panelBotones.add(botonComenzar);
		panelBotones.add(botonCancelar);
		add(panelBotones, BorderLayout.SOUTH);
		
		botonCancelar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				
				JFrame frame = conseguirRoot(ev);
				volverMenuInicio(frame);
				
			}
		});
		
		botonComenzar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				
				if(dificultadFacilRB.isSelected())
					GUIJuego.setMultiplicadorDificultad(3);
				else if(dificultadNormalRB.isSelected())
					GUIJuego.setMultiplicadorDificultad(2);
				else if(dificultadDificilRB.isSelected())
					GUIJuego.setMultiplicadorDificultad(1);
				
				
				JFrame frame = conseguirRoot(ev);
				irGuiJuego(frame);
				
			}
		});
	}
	
	
	private JFrame conseguirRoot(ActionEvent ev){
		
		Component component = (Component) ev.getSource();
		JFrame frame = (JFrame) SwingUtilities.getRoot(component);
		
		return frame;
	}
	
	private void volverMenuInicio(JFrame frame){
		SwingConsole.run(new MenuInicio(), 300, 150, true);
		frame.dispose();
	}
	
	private void irGuiJuego(JFrame frame){
		SwingConsole.run(new GUIJuego(null, null, null), 1024, 768, true);
		frame.dispose();
	}
}
