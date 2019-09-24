package juego.misc;

import java.util.Random;

public class Dado {
	private int cantidadCaras;

	public Dado(int cantidadCaras) {
		this.cantidadCaras = cantidadCaras;
	}

	public int tirar() {
		return this.obtenerAleatorioMenorQue(this.cantidadCaras);
	}

	/*
	 * Metodo que acepta un numero distinto de cantidad caras
	 */
	public int tirar(int cant) {
		return this.obtenerAleatorioMenorQue(cant);
	}

	private int obtenerAleatorioMenorQue(int maximo) {
		return new Random().nextInt(maximo);
	}
}