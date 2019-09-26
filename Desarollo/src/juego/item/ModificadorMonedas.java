package juego.item;

import juego.personas.Jugador;

public class ModificadorMonedas extends Item {
	public ModificadorMonedas(String nombre, String descripcion, int cantMaxima, int multiplicador) {
		super(nombre, descripcion, cantMaxima, multiplicador);
	}

	@Override
	public void activarItem(Jugador jugador) {
		// TODO Auto-generated method stub

	}
}
