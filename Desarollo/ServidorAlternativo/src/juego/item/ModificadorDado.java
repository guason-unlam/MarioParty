package juego.item;

import juego.misc.Dado;
import juego.personas.Jugador;

public class ModificadorDado extends Item {

	public ModificadorDado() {
		super("Modificador de dado", "Aumenta en 1 la cantidad de caras del dado del jugador");
	}

	@Override
	public void activarItem(Jugador objetivo) {// agrego el parametro Jugador objetivo para permitir su correcto testeo.
		Dado dado = objetivo.getDado();
		dado.setCantidadCaras(dado.getCantidadCaras() + 1);
	}

	@Override
	public Jugador elegirObjetivo() {
		return this.duenio; // el objetivo de este item siempre sera el due�o
	}
}
