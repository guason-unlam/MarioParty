package juego.ventana;

import javax.swing.JPanel;

import graphics.Game;
import graphics.GameWindow;
import juego.ControladorJuego;
import juego.personas.Jugador;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelJugador extends JPanel {

	private JLabel lblNombrejugador;
	private static JButton btnLanzarDado;
	private static JButton btnUsarItem;
	private static JButton btnPasar;
//	ControladorJuego juego;
	Game juego;

	/**
	 * Create the panel.
	 */
	public PanelJugador(int x, int y) {
		this.setBounds(x, y, 260, 110);

		setBackground(Color.BLACK);
		setLayout(null);

		lblNombrejugador = new JLabel("nombreJugador");
		lblNombrejugador.setBounds(10, 11, 131, 14);
		add(lblNombrejugador);

		btnLanzarDado = new JButton("Lanzar dado");
		btnLanzarDado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				juego.avanzarJugador(juego.getJugadorActual().tirarDado());
//				juego.continuar();
				juego.avanzarJugador(juego.jugadorActual.tirarDado());
				desactivarIconos();
				juego.continuar();
			}
		});
		btnLanzarDado.setBounds(10, 36, 106, 23);
		add(btnLanzarDado);

		btnUsarItem = new JButton("Usar item");
		btnUsarItem.setBounds(10, 70, 106, 23);
		btnUsarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				juego.usarItem();
			}
		});
		add(btnUsarItem);

		btnPasar = new JButton("Pasar");
		btnPasar.setBounds(156, 36, 89, 57);
		btnPasar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				juego.continuar();
			}
		});
		add(btnPasar);

	}

	public static void desactivarIconos() {
		btnLanzarDado.setEnabled(false);
		btnUsarItem.setEnabled(false);
		btnPasar.setEnabled(false);
	}

	public static void activarIconos() {
		btnLanzarDado.setEnabled(true);
		btnUsarItem.setEnabled(true);
		btnPasar.setEnabled(true);
	}

	public void setNombreJugador(String nombre) {
		lblNombrejugador.setText(nombre);
	}

	public void setGame(Game juego) {
		this.juego = juego;
	}

//	public void setControladorJuego(ControladorJuego juego) {
//		this.juego = juego;
//	}
}
