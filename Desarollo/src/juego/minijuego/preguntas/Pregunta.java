package juego.minijuego.preguntas;

import java.awt.geom.Rectangle2D;

public class Pregunta {

	private int x, y;
	static final int ANCHO = 700, ALTO = 100; // static para poder invocarla desde otra clase

	public Pregunta(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Rectangle2D getPregunta() {
		return new Rectangle2D.Double(x, y, ANCHO, ALTO);

	}
}
