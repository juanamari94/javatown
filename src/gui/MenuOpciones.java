package gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import utiles.SwingConsole;

public class MenuOpciones extends JFrame{

	private JButton salirBoton = new JButton("Salir");
	private JButton continuarBoton = new JButton("Continuar");
	private JButton guardarBoton = new JButton("Guardar");
	
	private JLabel opcionLabel = new JLabel("Seleccione una opción");
	
	public MenuOpciones(final GUIJuego juego){
		
		JPanel panelOpciones = new JPanel(new GridLayout(2,1));
		JPanel panelBotones = new JPanel(new GridLayout(1,3));
		
		panelOpciones.add(opcionLabel);
		panelOpciones.add(panelBotones);
		panelBotones.add(continuarBoton);
		panelBotones.add(salirBoton);
		panelBotones.add(guardarBoton);
		add(panelOpciones);
		
		continuarBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				
				juego.habilitar();
				juego.getAdministrador().resume(juego);
				JFrame frame = conseguirRoot(ev);
				frame.dispose();
				
				
			}
		});
		
		salirBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				
				int opcion = JOptionPane.showConfirmDialog(conseguirRoot(ev), "Va a salir de la aplicación. ¿Está seguro?", "¡Atención!", JOptionPane.YES_NO_OPTION);
				if(opcion == JOptionPane.YES_OPTION)
					System.exit(0);
				
			}
		});
		
		guardarBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				SwingConsole.run(new MenuGuardar(juego), 250, 150, true);
				JFrame frame = conseguirRoot(ev);
				frame.dispose();
			}
		});
		
		this.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {

				juego.getAdministrador().resume(juego);
				juego.habilitar();
				
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private JFrame conseguirRoot(ActionEvent ev){
		
		Component component = (Component) ev.getSource();
		JFrame frame = (JFrame) SwingUtilities.getRoot(component);
		
		return frame;
	}
}
