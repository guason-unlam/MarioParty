package juego.item;

import juego.personas.Jugador;

public class ModificadorDado extends Item {
	public ModificadorDado(String nombre, String descripcion, int cantMaxima, int multiplicador) {
		super(nombre, descripcion, cantMaxima, multiplicador);
	}

	@Override
	public void activarItem(Jugador jugador) {
		// TODO Auto-generated method stub
		if(this.cantidadMaxima != jugador.getDado().getCantidadCaras()) {
			jugador.getDado().setCantidadCaras(this.cantidadMaxima);
		}
	}
}
