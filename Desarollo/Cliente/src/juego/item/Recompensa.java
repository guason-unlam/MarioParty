package juego.item;

import juego.personas.Jugador;

public abstract class Recompensa {
	public abstract void darRecompensa(Jugador jugador);

	public abstract String getNombre();
}
