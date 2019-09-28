package juego.item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import juego.personas.Jugador;

public class ItemRobar extends Item {

	public ItemRobar() {
		super("Robar", "Roba 50p a un jugador");
	}

	@Override
	public void activarItem(Jugador objetivo) { // Cambio la selección manual de objetivo por un parametro para permitir testeo.
		//Jugador objetivo = elegirObjetivo();
		this.dueño.darPesos(objetivo.quitarPesos(50));
		
	}

	@Override
	public Jugador elegirObjetivo() {
		Iterator<Jugador> iterador = this.dueño.getPartida().getJugadoresEnPartida().iterator();
		List<Jugador> oponentes = new ArrayList<Jugador>(); //Una lista con los jugadores oponentes
		
		while(iterador.hasNext()) {
			Jugador jugador = iterador.next();
			if(!jugador.equals(this.dueño))
				oponentes.add(jugador);
		}
		
		
		iterador = oponentes.iterator();
		int i=1,numeroIngresado;
		while(iterador.hasNext()) {//Muestro todos los oponentes para que elija el objetivo
			juego.Main.mostrar(i + "- " + iterador.next().getNombre());
			i++;
		}
		do {
			numeroIngresado = juego.Main.leer(); //El jugador elije el oponente objetivo
		}while(numeroIngresado<1 || numeroIngresado>i);
		return  oponentes.get(numeroIngresado);
	}

}
