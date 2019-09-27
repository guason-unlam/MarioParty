package juego.lobby;

import java.util.Iterator;

import juego.item.Item;
import juego.personas.Jugador;;

public class Turno {

	private Jugador jugador;
	
	public Turno(Jugador jugador) {
		this.jugador = jugador;
	}
	
	public void iniciarTurno() {
		juego.Main.mostrar("Turno de " + jugador.getNombre());
		jugador.tirarDado();
		if(jugador.getInventario().getCantItems() == 0) //Si el jugador no tiene items, termina su turno
			return;
		
		etapaAccion();
	}
	
	private void etapaAccion() {
		char respuesta;
		
		juego.Main.mostrar("Quiere usar un objeto? (S/N)");
		do
			respuesta = (char) juego.Main.leer();
		while(respuesta != 'S' && respuesta != 'N');
		
		if(respuesta == 'N') //Si el jugador no quiere usar ningun item, termina su turno
			return;
		
		elegirItem().usarItem();
	}
	
	private Item elegirItem() {
		int i = 1,numeroIngresado;
		juego.Main.mostrar("Elije item a usar:");
		Iterator<Item> iteradorItems = jugador.getInventario().listarItems();
		while(iteradorItems.hasNext()) { // Muestro los items del jugador
			juego.Main.mostrar(i + "- "+iteradorItems.next().getNombre());
			i++;
		}

		do {
			numeroIngresado = juego.Main.leer(); //El jugador elije q item usar
		}while(numeroIngresado<1 || numeroIngresado>i);
		
		return jugador.getInventario().getItems().get(numeroIngresado); //devuelvo el item seleccionado
	}
}