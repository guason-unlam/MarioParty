package juego.minijuego.preguntas;

import java.io.IOException;

import javax.swing.JFrame;

import juego.minijuego.pingpong.EventoTeclado;
import juego.minijuego.pingpong.Hilo;
import juego.minijuego.pingpong.TableroPingPong;

public class VentanaPreguntas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int ANCHO = 800, ALTO = 500;
	private Tablero lamina;

	public VentanaPreguntas() throws IOException {
		setTitle("Quien Quiere Ser Billonario");
		setSize(ANCHO, ALTO);
		setLocationRelativeTo(null);
		setResizable(false);
		lamina = new Tablero();
		add(lamina);
		addKeyListener(new EventoTeclado());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}