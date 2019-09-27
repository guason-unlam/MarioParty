package juego.lobby;

import java.util.ArrayList;
import java.util.Iterator;

import juego.personas.Jugador;
import juego.item.Item;;

public class Turno {

	private Jugador jugador;
	
	public Turno(Jugador jugador) {
		this.jugador = jugador;
	}
	
	public void iniciarTurno() {
		char respuesta;
		int numeroIngresado;
		juego.Main.mostrar("Turno de " + jugador.getNombre());
		jugador.tirarDado();
		
		if(jugador.getInventario().getCantItems() == 0) //Si el jugador no tiene items, termina su turno
			return;
		
		juego.Main.mostrar("Quiere usar un objeto? (S/N)");
		do
			respuesta = (char) juego.Main.leer();
		while(respuesta != 'S' && respuesta != 'N');
		
		if(respuesta == 'N') //Si el jugador no quiere usar ningun item, termina su turno
			return;
		
		
		int i = 1; //Llego aca cuando el jugador quiere usar algun item
		juego.Main.mostrar("Elije item a usar:");
		Iterator<Item> iteradorItems = jugador.getInventario().listarItems();
		while(iteradorItems.hasNext()) {
			juego.Main.mostrar(i + "- "+iteradorItems.next().getNombre());
			i++;
		}

		do {
			numeroIngresado = juego.Main.leer(); //El jugador elije q item usar
		}while(numeroIngresado<1 || numeroIngresado>i);
		
		Item item = jugador.getInventario().getItems().get(numeroIngresado);
		ArrayList<Jugador> listaJugadores = jugador.getPartida().getJugadoresEnPartida();
		Iterator<Jugador> iteradorJugadores = listaJugadores.iterator();
		
		i=1;
		while(iteradorJugadores.hasNext()) {//Muestro todos los jugadores para que elija el objetivo
			juego.Main.mostrar(i + "- " + iteradorJugadores.next().getNombre());
			i++;
		}
		do {
			numeroIngresado = juego.Main.leer(); //El jugador elije el jugador objetivo
		}while(numeroIngresado<1 || numeroIngresado>i);
		
		item.activarItem(listaJugadores.get(numeroIngresado)); //se activa el item seleccionado en el objetivo seleccionado
		
	}
}
