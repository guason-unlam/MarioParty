package juego.ventana;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import graphics.Texture;
import juego.Constantes;
import juego.ControladorJuego;
import juego.lobby.ExcepcionJugadoresInsuficientes;
import juego.lobby.Partida;
import juego.lobby.Ronda;
import juego.personas.Jugador;
import juego.tablero.Tablero;
import juego.tablero.casillero.Casillero;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

public class VentanaJuego extends JFrame implements ImageObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3849520346687736542L;
	private Partida p;
	private static Texture tex;
	private ControladorJuego juego;
	PanelJugador panelJugador;

	/*
	 * public void paint(Graphics g) { g.fillRect(100, 50, Constantes.MAPA_WIDTH,
	 * Constantes.MAPA_HEIGHT - 50); }
	 */
	public VentanaJuego(Partida p) {
		this.p = p;
		p.setVentanaJuego(this);
		setTitle("Mario Party");

		this.setBounds(0, 0, Constantes.VENTANA_WIDTH, Constantes.VENTANA_HEIGHT+200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		panelJugador = new PanelJugador(0, Constantes.MAPA_HEIGHT+100);
		juego = new ControladorJuego(p,this);
		panelJugador.setControladorJuego(juego);
		this.add(panelJugador);
//		panelJugador = new PanelJugador()
		/*
		 * GridBagLayout gridBagLayout = new GridBagLayout(); gridBagLayout.columnWidths
		 * = new int[] { 0 }; gridBagLayout.rowHeights = new int[] { 0 };
		 * gridBagLayout.columnWeights = new double[] { Double.MIN_VALUE };
		 * gridBagLayout.rowWeights = new double[] { Double.MIN_VALUE };
		 * getContentPane().setLayout(gridBagLayout);
		 */
		JPanel panelJuego = new JPanel();
		panelJuego.setBounds(0, 0, Constantes.MAPA_WIDTH, Constantes.MAPA_HEIGHT);
		this.add(panelJuego);
		tex = new Texture();
		/*JButton btnAbrirMinijuego = new JButton("Abrir Minijuego");
		btnAbrirMinijuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaMiniJuego();
				VentanaMiniJuego.ejecutar(p.getJugadoresEnPartida());
				setVisible(false);
			}
		});
		
		btnAbrirMinijuego.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		panelJuego.add(btnAbrirMinijuego);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Constantes.TABLERO1_PATH));

		lblNewLabel.setBounds(0, 0, Constantes.MAPA_WIDTH, Constantes.MAPA_HEIGHT);
		panelJuego.add(lblNewLabel);
		
//		JButton btnLanzarDado = new JButton("Lanzar dado");
//		btnLanzarDado.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				
//				
//			}
//		});
		

	}

	private void dibujarCasillerosYPersonajes(Graphics g) {
//		Random r = new Random();
		for (Entry<Integer, Casillero> elemento : this.p.getTablero().getCasilleros().entrySet()) {

			Casillero casilleroActual = elemento.getValue();
			g.drawString(String.valueOf(elemento.getKey()), (casilleroActual.getPosicionX()),
					(casilleroActual.getPosicionY()));
			
			/* Caminos */
			g.setColor(Color.black);
			for (Casillero sig : casilleroActual.getSiguiente()) {
				g.drawLine(	casilleroActual.getPosicionX() + ((Constantes.CASILLERO_WIDTH) / 2),
							(casilleroActual.getPosicionY())+((Constantes.CASILLERO_HEIGHT) / 2), 
							sig.getPosicionX() + Constantes.CASILLERO_WIDTH/2,
							(casilleroActual.getPosicionY()+((Constantes.CASILLERO_HEIGHT) / 2)));
			}
			for (Casillero sig : casilleroActual.getSiguiente()) {
				g.drawLine(	sig.getPosicionX() + Constantes.CASILLERO_WIDTH/2,
						(casilleroActual.getPosicionY())+ ((Constantes.CASILLERO_HEIGHT) / 2), 
						sig.getPosicionX() + Constantes.CASILLERO_WIDTH/2,
						(sig.getPosicionY()) + Constantes.CASILLERO_HEIGHT/2);
			}
			
			Color c = casilleroActual.getColor();
			g.setColor(c);
			
			g.fillRect(	casilleroActual.getPosicionX(), casilleroActual.getPosicionY(),
					  	Constantes.CASILLERO_WIDTH, Constantes.CASILLERO_HEIGHT);
			g.setColor(Color.black);

			g.drawRect( casilleroActual.getPosicionX(), casilleroActual.getPosicionY(),
						Constantes.CASILLERO_WIDTH, Constantes.CASILLERO_HEIGHT);
			int desplazamiento=0;
			for (Jugador jugador : casilleroActual.getJugadores()) {
				g.drawImage(tex.characters[0/*jugador.getPersonaje().getIdCharacter()*/],jugador.getPosicion().getPosicionX()+desplazamiento, jugador.getPosicion().getPosicionY(), null);
				desplazamiento+=20;
			}
			
		}
	}
	
	public void dibujarPersonajes(Graphics g) {
		int x, y;
		for(int i = 0; i < p.getJugadoresEnPartida().size(); i++) {
			Jugador tempPlayer = p.getJugadoresEnPartida().get(i);
			x = tempPlayer.getPosicion().getPosicionX();
			y = tempPlayer.getPosicion().getPosicionY();
			g.drawImage(tex.characters[0/*tempPlayer.getPersonaje().getIdCharacter()*/], x, y, null);
			//eso hay que descomentarlo cuando este bien implementado el registro y logeo de usuario y sacar el 0
	
		}
	}

	public void paint(Graphics g) {
		super.paint(g);

		this.dibujarCasillerosYPersonajes(g);
	}
	
	public static Texture getInstance() {
		return tex;
	}
	
	public PanelJugador getPanelJugador() {
		return this.panelJugador;
	}
}
