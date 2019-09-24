package juego.tablero.casillero;

import java.util.ArrayList;
import java.util.Scanner;

public class Bifurcacion extends Casillero {
	private Casillero anterior;
	
	/*
	 * Pueden existir una bifurcacion triple, por ejemplo, por eso hay varios
	 */
	private ArrayList<Casillero> siguientes;

	public Bifurcacion(ArrayList<Casillero> sig) {
		super();
		this.siguientes = sig;
	}

	public Casillero seleccionarCamino() {
		Scanner lector = new Scanner(System.in);
		int camino;
		do {
			System.out.println("Ingrese un camino: ");
			camino = lector.nextInt();
		} while (camino <= this.siguientes.size());
		lector.close();
		return this.siguientes.get(camino);
	}
}
