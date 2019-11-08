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
		partida = new Partida(usuarios, "MONEDAS", "chico", 1);
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
		// al comienzo, los jugadores estan en la misma posicion
		Casillero cas = jugador2.getPosicion(); // guardo la posicion inicial de los jugadores
		moverJugador(jugador1);
		Assert.assertFalse(jugador2.getPosicion() == jugador1.getPosicion());// verifico q el jugador 1 se haya movido

		moverJugador(jugador2);
		Assert.assertFalse(cas == jugador2.getPosicion());// verifico q el jugador 2 se hay amovido
	}

	// metodo auxiliar para avanzar varios casilleros, esta logica se va a
	// implementar mas adelante
	// cuando el juego ya sea interactivo
	private void moverJugador(Jugador jugador) {
		for (int i = jugador.tirarDado(); i > 0; i--) {
			if (jugador.caminosDisponibles() == 1)
				jugador.avanzarUnCasillero();
			else
				jugador.avanzarUnCasillero(1); // si hay mas de un camino, avanzo por el primer camino
			if (jugador.getPosicion().isTieneArbolito()) { // si hay arbolito, intento comprar un dolar
				jugador.comprarDolar();
			}
		}
		if (jugador1.getPosicion().isTieneRecompensa())
			jugador1.getPosicion().getRecompensa().darRecompensa(jugador1);
	}

	@Test
	public void comprarDolarSinArbolitoTest() {
		jugador1.setPesos(5000);
		if (!jugador1.getPosicion().isTieneArbolito())
			Assert.assertFalse(jugador1.comprarDolar()); // No permite comprar dolares si no hy un arbolito
		else
			Assert.assertTrue(jugador1.comprarDolar());

	}

	@Test
	public void comprarDolarSinPesosTest() {
		jugador1.getPosicion().setTieneArbolito(true);
		jugador1.setPesos(0);
		Assert.assertFalse(jugador1.comprarDolar()); /// comprueba que no se pueda comprar dolares sin la cantidad de
														/// pesos necesaria

	}

	@Test
	public void comprarDolarRestaPesosTest() {
		jugador1.setPosicion(new Casillero(1));
		jugador1.getPosicion().setTieneArbolito(true);
		int cantidadPesos = 5000;
		jugador1.setPesos(cantidadPesos);
		jugador1.comprarDolar();
		// comprueba que reste los pesos una vez comprado los dolares.
		Assert.assertEquals(cantidadPesos - jugador1.getPartida().getPrecioDolar(), jugador1.getPesos());

	}

	@Test
	public void soloUnDolarPorArbolitoTest() {
		jugador1.getPosicion().setTieneArbolito(true);
		int cantidadPesos = 5000;
		jugador1.setPesos(cantidadPesos);
		for (int i = 0; i < 2; i++) {
			jugador1.comprarDolar();
		}
		Assert.assertEquals(1, jugador1.getDolares());
	}
}
