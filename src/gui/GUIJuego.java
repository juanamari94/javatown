package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import utiles.SwingConsole;
import data.Administrador;
import data.Ciudad;
import data.Constructora;
import data.Dificultad;
import data.Transaccion;
import dominio.Item;

public class GUIJuego extends JFrame{

	private static int multiplicadorDificultad;
	private static boolean loadedGame = false;
	private Item edificioDestruido;
	
	private Transaccion transaccion;
	
	private JLabel labelConstrucciones = new JLabel("Construcciones");
	private Timer timer;
	
	private JButton botonConstruirEscuela = new JButton("Escuela");
	private JButton botonConstruirComisaria = new JButton("Comisaria");
	private JButton botonConstruirCuartel = new JButton("Cuartel de Bomberos");
	
	private JButton botonConstruirArbol = new JButton("Arbol");
	
	private JButton botonConstruirCasa = new JButton("Casa");
	private JButton botonConstruirComercio = new JButton("Comercio");
	private JButton botonConstruirIndustria = new JButton("Industria");
	
	private JButton botonOpciones = new JButton("Opciones");
	
	private JButton botonEliminarConstruccion = new JButton("Eliminar");
	
	private Tile[][] mapaTiles = new Tile[25][25];
	
	private int tipoConstruccion = -1;
	
	private Administrador administrador;
	
	public GUIJuego(Tile mapaCargado[][], int datosCargados[], Dificultad dificultad){
		
		JPanel panelConstruccion = new JPanel(new GridLayout(9,1));
		JPanel panelDatosCiudad = new JPanel(new FlowLayout());
		final JPanel panelMapa = new JPanel(new GridLayout(25,25));
		final PanelEstado panEst = new PanelEstado();
		PanelConstrucciones panCon = new PanelConstrucciones();
		
		add(panelConstruccion, BorderLayout.WEST);
		panelConstruccion.add(botonConstruirEscuela);
		panelConstruccion.add(botonConstruirComisaria);
		panelConstruccion.add(botonConstruirCuartel);
		panelConstruccion.add(botonConstruirArbol);
		panelConstruccion.add(botonConstruirCasa);
		panelConstruccion.add(botonConstruirComercio);
		panelConstruccion.add(botonConstruirIndustria);
		panelConstruccion.add(botonEliminarConstruccion);
		panelConstruccion.add(botonOpciones);
		
		// #### ActionListeners de Botones Costrucción
		
		botonOpciones.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				administrador.pause(conseguirRoot(ev));
				SwingConsole.run(new MenuOpciones(conseguirRoot(ev)), 300, 150, true);
			}
		});
		
		botonEliminarConstruccion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				setTipoConstruccion(0);
			}
		});
		
		botonConstruirComisaria.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				setTipoConstruccion(1);
			}
		});
		
		botonConstruirCuartel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				setTipoConstruccion(2);
			}
		});
		
		botonConstruirEscuela.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				setTipoConstruccion(3);
			}
		});
		
		botonConstruirArbol.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				setTipoConstruccion(4);
			}
		});
		
		botonConstruirCasa.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				setTipoConstruccion(5);
			}
		});
		
		botonConstruirComercio.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				setTipoConstruccion(6);
			}
		});
		
		botonConstruirIndustria.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				setTipoConstruccion(7);
			}
		});
		
		// ### Fin ActionListeners de Botones Construcción
		
		transaccion = Transaccion.getInstance();
		
		if(mapaCargado == null && datosCargados == null && dificultad == null){
			administrador = nuevoJuego();
			cargarTiles(panelMapa);
		}
		else{
			administrador = cargarJuego(datosCargados, dificultad);
			this.setMapaTiles(mapaCargado);
			loadedGame = true;
			cargarTilesJuegoCargado(panelMapa, mapaTiles);
		}
		
		loadedGame = false;
			
		administrador.administrar(this);
		
		add(panelDatosCiudad, BorderLayout.NORTH);
		panCon.add(labelConstrucciones);
		panelDatosCiudad.add(panCon);
		
		panelDatosCiudad.add(panEst);
		
		add(panelMapa, BorderLayout.CENTER);
		
		//cargarTiles(panelMapa);
		
		timer = new Timer(100, new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
			
				panEst.setValores(administrador.getCiudad().getArca(), administrador.getCiudad().getPuntosBelleza(), administrador.getCiudad().getCantidadHabitantes(), administrador.getCiudad().getCantidadHabitantesDisponibles(), administrador.getCiudad().getIngresosEgresosTotales());
				panEst.update(getGraphics());
				
			}
			
		});
		
		timer.start();
		
	}
	
	private void cargarTiles(JPanel panel){
		
		for(int i = 0; i < 25; i++){
			for(int j = 0; j < 25; j++){
				mapaTiles[i][j] = new Tile(i, j, 0);
				asignarTile(mapaTiles[i][j], panel);
				setMyTile(mapaTiles[i][j]);
			}
		}
		
	}
	
	private void cargarTilesJuegoCargado(JPanel panel, Tile mapaCargado[][]){
		
		for(int i = 0; i < 25; i++){
			for(int j = 0; j < 25; j++){
				asignarTile(mapaCargado[i][j], panel);
				setMyTile(mapaCargado[i][j]);
			}
		}
	}
	
	// Necesito asignar las imágenes de las tiles.
	private void asignarTile(Tile tile, JPanel panel){

		switch(tile.getTipo()){
		case 0:
			setTileImage("imagenes/pasto.png", tile, panel);
			break;
		case 1:
			setTileImage("imagenes/comisaria.png", tile, panel);
			administrador.getConstructora().construirComisaria(administrador.getDificultad(), administrador.getCiudad());
			break;
		case 2:
			setTileImage("imagenes/cuartel.png", tile, panel);
			administrador.getConstructora().construirCuartel(administrador.getDificultad(), administrador.getCiudad());
			break;
		case 3:
			setTileImage("imagenes/escuela.png", tile, panel);
			administrador.getConstructora().construirEscuela(administrador.getDificultad(), administrador.getCiudad());
			break;
		case 4:
			setTileImage("imagenes/arbol.png", tile, panel);
			administrador.getConstructora().construirArbol(administrador.getDificultad(), administrador.getCiudad());
			break;
		case 5:
			setTileImage("imagenes/casa.png", tile, panel);
			administrador.getConstructora().construirCasa(administrador.getDificultad(), administrador.getCiudad());
			break;
		case 6:
			setTileImage("imagenes/comercio.png", tile, panel);
			administrador.getConstructora().construirComercio(administrador.getDificultad(), administrador.getCiudad());
			break;
		case 7:
			setTileImage("imagenes/industria.png", tile, panel);
			administrador.getConstructora().construirIndustria(administrador.getDificultad(), administrador.getCiudad());
			break;
		default:
			setTileImage("imagenes/pasto.png", tile, panel);
			break;
		}
			
	}
	
	// Método para 'crear' tiles.
	private void setTileImage(String s, Tile tile, JPanel panel){
		
		try{
			
			tile.setIcon(new StretchIcon(s,false));
			panel.add(tile);
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
		
	}
	
	public void explotarEdificio(Item edificioDestruido){
		
		if(edificioDestruido != null){
			//System.out.println("Estoy intentando destruir esto.");
			Tile tileDestruida = new Tile(0,0, (transaccion.checkInstanceOfItem(edificioDestruido)));
			//System.out.println(""+transaccion.checkInstanceOfEdificioDestruido(edificioDestruido));
			borrarTileDestruida(tileDestruida);
			edificioDestruido = null;
			
		}
			
	}
	
	private void borrarTileDestruida(Tile tile){
		
		boolean destruccion = false;

		for(int i = 0; i < 25; i++){
			for(int j = 0; j < 25 ; j++){
				
				if(!destruccion){
					if(mapaTiles[i][j].getTipo() == tile.getTipo() && tile.getTipo() != 0){
						
						mapaTiles[i][j].setTipo(0);
						try{
							
						mapaTiles[i][j].setIcon(new StretchIcon("imagenes/pasto.png",false));
						
						}catch(Exception e){
							
							e.printStackTrace();
						
						}
						
						destruccion = true;
					}
				}
			}
		}
	}


	// Método para Construcción y Destrucción
	private void setMyTile(Tile t){
		
		t.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent me) {
				
				Tile tile = ((Tile)me.getSource());
				
				if((getTipoConstruccion() > 0 || getTipoConstruccion() <= 7) && tile.getTipo() == 0){
						
					if(transaccion.checkTipoParaConstruccion(administrador, getTipoConstruccion())){
						tile.setTipo(getTipoConstruccion());
						asignarTile(tile, (JPanel) tile.getParent());
						setTipoConstruccion(-1);
					}
					
				} else if( getTipoConstruccion() == 0 && tile.getTipo() != 0){
					
					transaccion.destruirEdificioEnLista(administrador, tile.getTipo());
					tile.setTipo(getTipoConstruccion());
					asignarTile(tile, (JPanel) tile.getParent());
					setTipoConstruccion(-1);
					
					
				}
		}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}});
		
	}
	
	public static int getMultiplicadorDificultad() {
		return multiplicadorDificultad;
	}

	public static void setMultiplicadorDificultad(int multiplicadorDificultad) {
		GUIJuego.multiplicadorDificultad = multiplicadorDificultad;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	private int getTipoConstruccion() {
		return tipoConstruccion;
	}

	private void setTipoConstruccion(int tipoConstruccion) {
		this.tipoConstruccion = tipoConstruccion;
	}
	
	public Item getEdificioDestruido() {
		return edificioDestruido;
	}

	public void setEdificioDestruido(Item edificioDestruido) {
		this.edificioDestruido = edificioDestruido;
	}
	
	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public static void killThemAll(GUIJuego juego){ // En honor a Ashbringer -- la idea es que como están en null, que Garbage Collector se ocupe de eliminarlos así no ocupan espacio demás en memoria.
		
		juego.getAdministrador().setCiudad(null);
		juego.getAdministrador().setConstructora(null);
		juego.getAdministrador().setDificultad(null);
		juego.setAdministrador(null);
		juego.setMapaTiles(null);
		juego.dispose();
		
	}

	public Transaccion getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}

	public Tile[][] getMapaTiles() {
		return mapaTiles;
	}

	public void setMapaTiles(Tile[][] mapaTiles) {
		this.mapaTiles = mapaTiles;
	}
	
	public void deshabilitar(){
		
		botonConstruirEscuela.setEnabled(false);
		botonConstruirComisaria.setEnabled(false);
		botonConstruirCuartel.setEnabled(false);
		botonConstruirArbol.setEnabled(false);
		botonConstruirCasa.setEnabled(false);
		botonConstruirComercio.setEnabled(false);
		botonConstruirIndustria.setEnabled(false);
		botonEliminarConstruccion.setEnabled(false);
		botonOpciones.setEnabled(false);
		
	}
	
	public void habilitar(){
		botonConstruirEscuela.setEnabled(true);
		botonConstruirComisaria.setEnabled(true);
		botonConstruirCuartel.setEnabled(true);
		botonConstruirArbol.setEnabled(true);
		botonConstruirCasa.setEnabled(true);
		botonConstruirComercio.setEnabled(true);
		botonConstruirIndustria.setEnabled(true);
		botonEliminarConstruccion.setEnabled(true);
		botonOpciones.setEnabled(true);
		
	}
	
	private GUIJuego conseguirRoot(ActionEvent ev){
		
		Component component = (Component) ev.getSource();
		GUIJuego frame = (GUIJuego) SwingUtilities.getRoot(component);
		
		return frame;
	}
	
	private Administrador nuevoJuego(){
		
		Dificultad d = new Dificultad(this.getMultiplicadorDificultad());
		
		return new Administrador(new Ciudad(new ArrayList<Item>(), 0, 10000, 0, d.getProbabilidadExplosion(), 0), 0, d, new Constructora());
	}
	
	private Administrador cargarJuego(int datos[], Dificultad dificultad){
		
		Dificultad d = dificultad;
		
		System.out.println(datos[0]);
		System.out.println(datos[1]);
		
		return new Administrador(new Ciudad(new ArrayList<Item>(),datos[1], datos[0], 0, d.getProbabilidadExplosion(), 0), 0, d, new Constructora());
	}

	public String formatearMapaTiles(){
		
		String mapa="";
		
		for(int i = 0; i < 25; i++){
			for(int j = 0; j < 25; j++){
				mapa += mapaTiles[i][j].getTipo() + ";";
			}
		}
		
		return mapa;
		
	}

	public static boolean isLoadedGame() {
		return loadedGame;
	}

	public static void setLoadedGame(boolean loadedGame) {
		GUIJuego.loadedGame = loadedGame;
	}
	
	
	
}