package utiles;

import gui.GUIJuego;
import gui.MenuCargar;
import gui.MenuInicio;
import gui.MenuNuevoJuego;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SwingConsole {

	public static void run(final JFrame frame, final int width, final int height, final boolean exitOnClose) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				if (exitOnClose){
					if(frame instanceof GUIJuego || frame instanceof MenuNuevoJuego || frame instanceof MenuCargar || frame instanceof MenuInicio)
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					else
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				frame.setSize(width, height);
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);

			}
		});
	}
	
	public static void run(final JFrame frame, final int width, final int height, final boolean exitOnClose, final String title) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				if (exitOnClose)
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				frame.setTitle(title);
				frame.setSize(width, height);
				frame.setVisible(true);

			}
		});
	}
	

}