package juego.item;

import java.util.ArrayList;
import java.util.Scanner;

import juego.personas.Jugador;
import juego.tablero.casillero.Casillero;

public class ItemBifurcacion extends Item {
	private ArrayList<Casillero> siguientes;

	public ItemBifurcacion(ArrayList<Casillero> siguientes) {
		super("Bifurcacion", "", 6, 1);
		this.siguientes = siguientes;
	}

	@Override
	public void activarItem(Jugador jugador) {
		Casillero destino = this.seleccionarCamino();
		jugador.setPosicion(destino);
		System.out.println("Usted avanzo por el casillero #" + destino.getId());
	}

	/*
	 * Aca selecciono mi camino
	 */
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
