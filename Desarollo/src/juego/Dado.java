package juego;

import java.util.Random;

public class Dado {
	private int cantidadCaras;

	public Dado(int cantidadCaras) {
		this.cantidadCaras = cantidadCaras;
	}

	public int lanzarDado() {
		// Modifica el seed
		Random aleatorio = new Random(System.currentTimeMillis());

		return aleatorio.nextInt(this.cantidadCaras) + 1;
	}
}
