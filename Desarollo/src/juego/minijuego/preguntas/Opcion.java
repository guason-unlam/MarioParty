package juego.minijuego.preguntas;

import java.awt.geom.Rectangle2D;

public class Opcion {

	private int x, y;
	static final int ANCHO = 300, ALTO = 40; // static para poder invocarla desde otra clase

	public Opcion(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Rectangle2D getOpcion() {
		return new Rectangle2D.Double(x, y, ANCHO, ALTO);
	}
}
