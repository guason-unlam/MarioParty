package juego;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import graphics.ObjectId;
import juego.item.Item;
import juego.item.ItemRobar;
import juego.item.ModificadorDado;
import juego.lobby.Partida;
import juego.lobby.Usuario;
import juego.misc.ExcepcionArchivos;
import juego.personas.Jugador;
import juego.tablero.Tablero;
import juego.tablero.casillero.Casillero;

public class ItemTest {

	private Usuario usuario = new Usuario("asd", "asd");
	private Usuario usuario2 = new Usuario("asd", "asd");
	ArrayList<Usuario> vec = new ArrayList<Usuario>();
	private Tablero tablero;
	Partida partida = new Partida(vec, 0);
	Jugador jugador2;// = new Jugador(usuario, tablero, partida);
	private Casillero casillero = new Casillero(1);

	@Test
	public void aumentoDeMonedasCorrecto() throws FileNotFoundException, ExcepcionArchivos {
		Jugador jugador2 = new Jugador(casillero, 1, ObjectId.Player);
		Jugador jugador1 = new Jugador(casillero, 2, ObjectId.Player);
		Item item;
		item = new ItemRobar();
		item.darRecompensa(jugador1);
		int cantidadMonedasDuenioItemOriginal = item.getDuenio().getPesos();
		item.activarItem(jugador2);

		Assert.assertEquals(cantidadMonedasDuenioItemOriginal + 50, item.getDuenio().getPesos());
	}

	@Test
	public void restoDeMonedasCorrecto() {
		Jugador jugador2 = new Jugador(casillero, 1, ObjectId.Player);
		Jugador jugador1 = new Jugador(casillero, 2, ObjectId.Player);
		Item item;
		item = new ItemRobar();
		item.darRecompensa(jugador1);
		int cantidadOriginalMonedasJugadorRobado = jugador2.getPesos();
		item.activarItem(jugador2);

		Assert.assertEquals(jugador2.getPesos(), cantidadOriginalMonedasJugadorRobado - 50);
	}

	@Test
	public void aumentaEnUnoElTamanioDado() {
		Item item;
		item = new ModificadorDado();
		Jugador jugador = new Jugador(casillero, 1, ObjectId.Player);
		item.darRecompensa(jugador);
		int cantidadCaras = item.getDuenio().getDado().getCantidadCaras();
		item.activarItem(jugador);

		Assert.assertEquals(cantidadCaras + 1, item.getDuenio().getDado().getCantidadCaras());
	}
}