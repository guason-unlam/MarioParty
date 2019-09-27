package juego.item;

import juego.personas.Jugador;

public class ItemRobar extends Item {

	public ItemRobar() {
		super("Robar", "Roba 50p a un jugador");
	}

	@Override
	public void activarItem(Jugador objetivo) {
		this.dueño.darPesos(objetivo.quitarPesos(50));
	}

}
