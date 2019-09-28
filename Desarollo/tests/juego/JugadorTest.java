package juego;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import juego.lobby.Partida;
import juego.lobby.Usuario;
import juego.personas.Jugador;
import juego.tablero.casillero.Casillero;

public class JugadorTest {
	private Usuario user1;
	private Usuario user2;
	private Partida partida;
	private Jugador jugador1;
	private Jugador jugador2;

	@Before
	public void inicio() {
		// Uso dos usuarios, condicion necesaria para arrancar una partida
		user1 = new Usuario("usuario", "contrasena");
		user2 = new Usuario("usuario2", "contrasena2");
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(user1);
		usuarios.add(user2);
		// 1 Ronda
		partida = new Partida(usuarios, 1);
		jugador1 = user1.getJugador();
		jugador2 = user2.getJugador();
	}

	@Test
	public void tieneJugadorAsignadoTest() {
		Assert.assertNotEquals(null, jugador1);
	}
	
	@Test
	public void darleYSacarPlata() {
		jugador1.darPesos(50);
		Assert.assertTrue(150 == jugador1.getPesos());
		int n = jugador1.quitarPesos(75);
		Assert.assertEquals(n, jugador1.getPesos());
	}
	
	@Test
	public void pruebaAvanzar() {
		Casillero cas = jugador2.getPosicion();
		Assert.assertEquals(jugador2.getPosicion(), jugador1.getPosicion());
		jugador1.tirarDado();
		Assert.assertFalse(jugador2.getPosicion() == jugador1.getPosicion());
		jugador2.tirarDado();
		Assert.assertFalse(cas == jugador2.getPosicion());
	}
}
