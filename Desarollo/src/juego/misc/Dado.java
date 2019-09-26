package juego.misc;

import java.util.Random;

public class Dado {
	private int cantidadCaras;

	public Dado(int cantidadCaras) {
		this.cantidadCaras = cantidadCaras;
	}

	public int tirar() {
		
		int resultado = 0;
		
		do {
			resultado = this.obtenerAleatorioMenorQue(this.cantidadCaras);
		} while (resultado == 0);
		
		return resultado;
	}

	/*
	 * Metodo que acepta un numero distinto de cantidad caras
	 */
	public int tirar(int cant) {
		
		int resultado = 0;
		
		do {
			resultado = this.obtenerAleatorioMenorQue(this.cantidadCaras);
		} while (resultado == 0);
		
		return resultado;
	}

	private int obtenerAleatorioMenorQue(int maximo) {
		return new Random().nextInt(maximo);
	}
}