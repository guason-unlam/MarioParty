package juego.minijuego;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Raqueta {
	private int x, y;
	static final int ANCHO = 10, ALTO = 40; // static para poder invocarla desde otra clase

	public Raqueta(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Rectangle2D getRaqueta() {
		return new Rectangle2D.Double(x, y, ANCHO, ALTO);
	}

	public void moverR1(Rectangle limites) {
		if (EventoTeclado.w && y > limites.getMinY()) {
			y--;
		}
		if (EventoTeclado.s && y + ALTO < limites.getMaxY()) {
			y++;
		}
	}

	// plantear para el caso multijugador donde se tiene que elegir el jugador 1 o
	// jugador 2
	public void moverR2(Rectangle limites) {
		if (EventoTeclado.up && y > limites.getMinY()) {
			y--;
		}
		if (EventoTeclado.down && y + ALTO < limites.getMaxY()) {
			y++;
		}
	}
}
