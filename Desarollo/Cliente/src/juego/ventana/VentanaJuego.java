package juego.ventana;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import graphics.elements.Texture;
import juego.Constantes;
import juego.ControladorJuego;
import juego.lobby.Partida;
import juego.personas.Jugador;
import juego.tablero.casillero.Casillero;

public class VentanaJuego extends JFrame implements ImageObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3849520346687736542L;
	private Partida p;
	private JFrame pantalla;
	private static Texture tex;
	private PanelJugador panelJugador;
	private ControladorJuego juego;
	private PanelConsola consola;
	private PanelPuntaje panelPuntaje;

	/*
	 * public void paint(Graphics g) { g.fillRect(100, 50, Constantes.MAPA_WIDTH,
	 * Constantes.MAPA_HEIGHT - 50); }
	 */
	public VentanaJuego(Partida part) {
		this.p = part;
		pantalla = this;
		setTitle("Mario Party");

		this.setBounds(0, 0, Constantes.VENTANA_WIDTH, Constantes.VENTANA_HEIGHT + 120);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 65
		panelJugador = new PanelJugador(10, Constantes.VENTANA_HEIGHT - 35);
		panelJugador.setBounds(10, 565, 260, 110);
		consola = new PanelConsola(panelJugador.getWidth() + 10, Constantes.VENTANA_HEIGHT - 35);
		consola.setBounds(270, 565, 350, 110);
		panelPuntaje = new PanelPuntaje(panelJugador.getWidth() + consola.getWidth() + 10,
				Constantes.VENTANA_HEIGHT - 35);
		panelPuntaje.setBounds(620, 565, 163, 110);
		getContentPane().setLayout(null);

		getContentPane().add(consola);
		getContentPane().add(panelJugador);
		getContentPane().add(panelPuntaje);
		juego = new ControladorJuego(p, this);
//		panelJugador.setControladorJuego(juego);

		/*
		 * GridBagLayout gridBagLayout = new GridBagLayout(); gridBagLayout.columnWidths
		 * = new int[] { 0 }; gridBagLayout.rowHeights = new int[] { 0 };
		 * gridBagLayout.columnWeights = new double[] { Double.MIN_VALUE };
		 * gridBagLayout.rowWeights = new double[] { Double.MIN_VALUE };
		 * getContentPane().setLayout(gridBagLayout);
		 */
		JPanel panelJuego = new JPanel();
		panelJuego.setBounds(0, 0, 794, 691);
		getContentPane().add(panelJuego);
		tex = new Texture();

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Constantes.TABLERO1_PATH));

		lblNewLabel.setBounds(0, 0, Constantes.MAPA_WIDTH, Constantes.MAPA_HEIGHT);
		panelJuego.add(lblNewLabel);

	}

	private void dibujarCasillerosYPersonajes(Graphics g) {
		for (Entry<Integer, Casillero> elemento : this.p.getTablero().getCasilleros().entrySet()) {

			Casillero casilleroActual = elemento.getValue();
			g.drawString(String.valueOf(elemento.getKey()), (casilleroActual.getPosicionX()),
					(casilleroActual.getPosicionY()));

			/* Caminos */
			g.setColor(Color.black);
			for (Casillero sig : casilleroActual.getSiguiente()) {
				g.drawLine(casilleroActual.getPosicionX() + ((Constantes.CASILLERO_WIDTH) / 2),
						(casilleroActual.getPosicionY()) + ((Constantes.CASILLERO_HEIGHT) / 2),
						sig.getPosicionX() + Constantes.CASILLERO_WIDTH / 2,
						(casilleroActual.getPosicionY() + ((Constantes.CASILLERO_HEIGHT) / 2)));
			}
			for (Casillero sig : casilleroActual.getSiguiente()) {
				g.drawLine(sig.getPosicionX() + Constantes.CASILLERO_WIDTH / 2,
						(casilleroActual.getPosicionY()) + ((Constantes.CASILLERO_HEIGHT) / 2),
						sig.getPosicionX() + Constantes.CASILLERO_WIDTH / 2,
						(sig.getPosicionY()) + Constantes.CASILLERO_HEIGHT / 2);
			}

			Color c = casilleroActual.getColor();
			g.setColor(c);

			g.fillRect(casilleroActual.getPosicionX(), casilleroActual.getPosicionY(), Constantes.CASILLERO_WIDTH,
					Constantes.CASILLERO_HEIGHT);
			g.setColor(Color.black);

			g.drawRect(casilleroActual.getPosicionX(), casilleroActual.getPosicionY(), Constantes.CASILLERO_WIDTH,
					Constantes.CASILLERO_HEIGHT);
			int desplazamiento = 0;
			for (Jugador jugador : casilleroActual.getJugadores()) {
				g.drawImage(tex.characters[0/* jugador.getPersonaje().getIdCharacter() */],
						jugador.getPosicion().getPosicionX() + desplazamiento, jugador.getPosicion().getPosicionY(),
						null);
				desplazamiento += 20;
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		// Creo los casilleros
		this.dibujarCasillerosYPersonajes(g);
//		this.dibujarPersonajes(g);
	}

	public void cargarMapa() {

	}

	public static Texture getInstance() {
		return tex;
	}

	public PanelJugador getPanelJugador() {
		return this.panelJugador;
	}

	public PanelConsola getPanelConsola() {
		return this.consola;
	}

}
