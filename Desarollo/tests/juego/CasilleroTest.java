package juego;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import juego.lobby.Partida;
import juego.lobby.Usuario;
import juego.personas.Jugador;
import juego.tablero.casillero.Casillero;

public class CasilleroTest {

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
	public void hayId() {

		Casillero cas = new Casillero(15);

		assertTrue(15 == cas.getId());
	}

	@Test
	public void noHayId() {

		Casillero cas = new Casillero(14);

		assertFalse(15 == cas.getId());
	}

	@Test
	public void hayColor() {

		Casillero cas = new Casillero(0);

		Color col = new Color(255);

		cas.setColor(col);

		Color col2 = new Color(255);

		cas.setColor(col2);

		assertTrue(col2 == cas.getColor());
	}

	@Test
	public void noHayColor() {

		Casillero cas = new Casillero(0);

		Color col = new Color(123);

		cas.setColor(col);

		Color col2 = new Color(255);

		cas.setColor(col2);

		assertTrue(col2 == cas.getColor());
	}

	@Test
	public void hayPosicionX() {
		Casillero cas = new Casillero(0);

		cas.setPosicionX(10);

		assertTrue(10 == cas.getPosicionX());

	}

	@Test
	public void noHayPosicionX() {
		Casillero cas = new Casillero(0);

		cas.setPosicionX(10);

		assertFalse(11 == cas.getPosicionX());
	}

	@Test
	public void hayPosicionY() {
		Casillero cas = new Casillero(0);

		cas.setPosicionY(10);

		assertTrue(10 == cas.getPosicionY());
	}

	@Test
	public void noHayPosicionY() {
		Casillero cas = new Casillero(0);

		cas.setPosicionY(10);

		assertFalse(11 == cas.getPosicionY());
	}

	@Test
	public void esPrimeraVez() {
		Casillero cas = new Casillero(0);

		cas.setPrimeraVez(true);

		assertTrue(true == cas.isPrimeraVez());
	}

	@Test
	public void noEsPrimeraVez() {
		Casillero cas = new Casillero(0);

		cas.setPrimeraVez(false);

		assertFalse(true == cas.isPrimeraVez());
	}

	@Test
	public void moverJugadorNoActivaPrimeraVezAnterioresTest() {
		Casillero cas = jugador2.getPosicion();
		Casillero casillero = new Casillero(1);
		int aAvanzar;
		for (int i = (aAvanzar = jugador2.tirarDado()); i > 0; i--) {
			if (jugador2.caminosDisponibles() == 1)
				jugador2.avanzarUnCasillero();
			else
				jugador2.avanzarUnCasillero(1); // si hay mas de un camino,
												// avanzo por el primer camino
			if (jugador2.getPosicion().isTieneArbolito()) { // si hay arbolito,
				// intento comprar
				// un dolar
				jugador2.comprarDolar();
			}
		}
		if (aAvanzar > 1) { // Esta condición es porque si avanza sólo un casillero estaría comprobando
							// que la posicion anterior sea isPrimeraVez, lo que es ilógico
			Iterator<Casillero> iterator = jugador2.getPosicion().getAnteriores().iterator();

			while (iterator.hasNext()) {
				casillero = iterator.next();
				assertTrue(casillero.isPrimeraVez()); // Comprueba que los casilleros
				// no hayan sido ocupados solo porque el personaje pasó por arriba.
			}
		}
	}

}
