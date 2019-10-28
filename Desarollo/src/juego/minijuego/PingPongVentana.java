package juego.minijuego;

import javax.swing.JFrame;

public class PingPongVentana extends JFrame {

	private final int ANCHO = 800, ALTO = 500;
	private TableroPingPong lamina;
	private Hilo hilo;

	public PingPongVentana() {
		setTitle("PingPong");
		setSize(ANCHO, ALTO);
		setLocationRelativeTo(null);
		setResizable(false);
		lamina = new TableroPingPong();
		add(lamina);
		addKeyListener(new EventoTeclado());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hilo = new Hilo(lamina);
		hilo.start();
	}
}