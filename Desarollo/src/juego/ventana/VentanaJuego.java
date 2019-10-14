package juego.ventana;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import juego.Constantes;
import juego.lobby.Partida;
import juego.tablero.Tablero;
import juego.tablero.casillero.Casillero;

public class VentanaJuego extends JFrame implements ImageObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3849520346687736542L;
	private Partida p;

	/*
	 * public void paint(Graphics g) { g.fillRect(100, 50, Constantes.MAPA_WIDTH,
	 * Constantes.MAPA_HEIGHT - 50); }
	 */
	public VentanaJuego(Partida p) {
		this.p = p;
		setTitle("Mario Party");

		this.setBounds(0, 0, Constantes.VENTANA_WIDTH, Constantes.VENTANA_HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		/*
		 * GridBagLayout gridBagLayout = new GridBagLayout(); gridBagLayout.columnWidths
		 * = new int[] { 0 }; gridBagLayout.rowHeights = new int[] { 0 };
		 * gridBagLayout.columnWeights = new double[] { Double.MIN_VALUE };
		 * gridBagLayout.rowWeights = new double[] { Double.MIN_VALUE };
		 * getContentPane().setLayout(gridBagLayout);
		 */
		JPanel panelJuego = new JPanel();
		panelJuego.setBounds(103, 21, Constantes.MAPA_WIDTH, Constantes.MAPA_HEIGHT);
		getContentPane().add(panelJuego);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Constantes.TABLERO1_PATH));

		lblNewLabel.setBounds(54, 21, Constantes.MAPA_WIDTH, Constantes.MAPA_HEIGHT);
		panelJuego.add(lblNewLabel);

	}

	private void dibujarCasilleros(Graphics g) {
		int i = 0;
		Random r = new Random();
		for (Entry<Integer, Casillero> elemento : this.p.getTablero().getCasilleros().entrySet()) {

			Casillero casilleroActual = elemento.getValue();
			g.drawString(String.valueOf(elemento.getKey()), (casilleroActual.getPosicionX()),
					(casilleroActual.getPosicionY()));
			float h = r.nextFloat();
			float s = r.nextFloat();
			float b = 0.8f + ((1f - 0.8f) * r.nextFloat());
			Color c = Color.getHSBColor(h, s, b);
			g.setColor(c);
			g.fillRect(casilleroActual.getPosicionX(), casilleroActual.getPosicionY(), 100, 100);
			g.setColor(Color.black);

			g.drawRect(casilleroActual.getPosicionX(), casilleroActual.getPosicionY(), 100, 100);
			for (Casillero sig : casilleroActual.getSiguiente()) {
				g.drawLine(casilleroActual.getPosicionX(), (casilleroActual.getPosicionY()), sig.getPosicionX(),
						(sig.getPosicionY()));
			}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);

		// Creo los casilleros
		this.dibujarCasilleros(g);
	}

	public void cargarMapa() {

	}
}
