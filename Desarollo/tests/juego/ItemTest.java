package juego;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import juego.item.Item;
import juego.item.ItemRobar;
import juego.item.ModificadorDado;
import juego.lobby.Partida;
import juego.lobby.Usuario;
import juego.misc.ExcepcionArchivos;
import juego.personas.Jugador;
import juego.tablero.Tablero;

public class ItemTest {

	private Usuario usuario = new Usuario("asd","asd");
	private Usuario usuario2 = new Usuario("asd","asd");
	ArrayList <Usuario> vec = new ArrayList <Usuario>();
	private Tablero tablero;
	Partida partida = new Partida(vec, 0);
	Jugador jugador2;// = new Jugador(usuario, tablero, partida);
	
	@Test
	public void aumentoDeMonedasCorrecto() throws FileNotFoundException, ExcepcionArchivos{
		Jugador jugador2 = new Jugador(usuario, tablero, partida);
		Jugador jugador1 = new Jugador(usuario2, tablero, partida);
		Item item;
		item = new ItemRobar();
		item.darRecompensa(jugador1);
		int cantidadMonedasDuenioItemOriginal = item.getDueño().getPesos();
		item.activarItem(jugador2);
		
		Assert.assertEquals(cantidadMonedasDuenioItemOriginal+50, item.getDueño().getPesos());
	}
	@Test
	public void restoDeMonedasCorrecto(){
		Jugador jugador2 = new Jugador(usuario, tablero, partida);
		Jugador jugador1 = new Jugador(usuario2, tablero, partida);
		Item item;
		item = new ItemRobar();
		item.darRecompensa(jugador1);
		int cantidadOriginalMonedasJugadorRobado = jugador2.getPesos();
		item.activarItem(jugador2);
		
		Assert.assertEquals(jugador2.getPesos(),cantidadOriginalMonedasJugadorRobado-50 );
	}
	
	@Test
	public void aumentaEnUnoElTamanioDado(){
		Item item;
		item = new ModificadorDado();
		Jugador jugador = new Jugador(usuario,tablero,partida);
		item.darRecompensa(jugador);
		int cantidadCaras = item.getDueño().getDado().getCantidadCaras();
		item.activarItem(jugador);
		
		Assert.assertEquals(cantidadCaras+1, item.getDueño().getDado().getCantidadCaras());
	}
}
