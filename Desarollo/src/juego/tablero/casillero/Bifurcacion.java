package juego.tablero.casillero;

import java.util.ArrayList;
import java.util.Scanner;

import juego.item.ItemBifurcacion;

public class Bifurcacion extends Casillero {
	private Casillero anterior;

	/*
	 * Pueden existir una bifurcacion triple, por ejemplo, por eso hay varios
	 */
	private ArrayList<Casillero> siguientes;

	public Bifurcacion(ArrayList<Casillero> sig) {
		super();
		this.siguientes = sig;
		// Necesito esto, para tener mi accion
		this.setItem(new ItemBifurcacion(sig));
	}

}
