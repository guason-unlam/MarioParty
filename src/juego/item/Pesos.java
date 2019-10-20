package juego.item;

import juego.personas.Jugador;

public class Pesos extends Recompensa {

	int cantidad;
	
	public Pesos(int cantidad) {
		this.cantidad = cantidad;
	}
	
	@Override
	public void darRecompensa(Jugador jugador) {
		jugador.darPesos(cantidad);
	}

}
