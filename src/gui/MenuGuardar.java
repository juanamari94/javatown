package gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import utiles.SwingConsole;
import data.GrabarJuego;
import data.LectorJuego;

public class MenuGuardar extends JFrame{

	private JButton guardarBoton = new JButton("Guardar Partida");
	private JButton cancelarBoton = new JButton("Cancelar");
	private JTextField guardarText = new JTextField(20);
	private GrabarJuego grabador;
	private LectorJuego lector;
	
	public MenuGuardar(final GUIJuego juego){
		
		setLayout(new GridLayout(2,1));
		
		add(guardarText);
		
		JPanel panelBotones = new JPanel(new GridLayout(2,1));
		
		panelBotones.add(guardarBoton);
		panelBotones.add(cancelarBoton);
		
		add(panelBotones);
		
		cancelarBoton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				JFrame frame = conseguirRoot(ev);
				frame.dispose();
				SwingConsole.run(new MenuOpciones(juego), 250, 150, true);
			}
		});
		
		guardarBoton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent ev){
				
				if(!guardarText.getText().isEmpty() && guardarText.getText().trim().length() > 0 && !guardarText.getText().contains("<") && !guardarText.getText().contains(">") && !guardarText.getText().contains(":") && !guardarText.getText().contains("\"") && !guardarText.getText().contains("/") && !guardarText.getText().contains("\\") && !guardarText.getText().contains("|") && !guardarText.getText().contains("?") && !guardarText.getText().contains("*")){
					
					File saves = new File("saves");
					if(!saves.exists())
						saves.mkdir();
					
					grabador = new GrabarJuego();
					grabador.checkearYCrearArchivoInexistente();
					
					String nombre = guardarText.getText().replaceAll(" ","");
					lector = new LectorJuego();
					lector.borrarStatesDemas();
					String states[] = lector.leerStates().split("\n");
					
					boolean nombreYaExistente = false;;
					
					for(int i = 0; i < states.length ; i++){
						if(nombre.equals(states[i])){
							
							nombreYaExistente = true;
							
							int option = JOptionPane.showConfirmDialog(null, "Está por sobrescribir un estado de guardado ya existente. ¿Sobreescribir?", "Atención", JOptionPane.YES_NO_OPTION);
							
							if(option == JOptionPane.YES_OPTION){
								
								grabador.grabarBulk(nombre, juego.getAdministrador().getCiudad(), juego);
								
							} else {
								
								guardarText.setText("");
								
							}
						}
					}
					
					if(!nombreYaExistente){
						
						String stateElegido = (String) JOptionPane.showInputDialog(null, "Seleccione el estado sobre el cual guardar. Se sobreescribirán los existentes.", "Atención", JOptionPane.QUESTION_MESSAGE, null, states, states[0]);
						File file = new File("saves/" + stateElegido + ".txt");
						
						for(int i = 0; i < states.length ; i++){
							
							if(states[i].equals(stateElegido)){
								states[i] = nombre;
								grabador.grabarState(states);
								grabador.grabarBulk(nombre, juego.getAdministrador().getCiudad(), juego);
								file.delete();
							}
							
						}
						
					}
					
				} else { 
					JOptionPane.showMessageDialog(conseguirRoot(ev), "Ha ingresado valores inválidos. Intente nuevamente. (No puede contener < > : \" / \\ | ? *)");
					
				}
				
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

				SwingConsole.run(new MenuOpciones(juego), 250, 150, true);
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
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
