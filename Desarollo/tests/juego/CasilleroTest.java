package juego;

import java.util.ArrayList;

import org.junit.Test;

import juego.lobby.Usuario;
import juego.personas.Jugador;
import juego.tablero.casillero.Casillero;
import junit.framework.Assert;

public class CasilleroTest {

	@SuppressWarnings("deprecation")
	@Test
	public void main() {
		Casillero cas = new Casillero();
		Assert.assertEquals(cas.getId(), 0);
		cas = TestSaltarCasillero(cas, 3);
		Assert.assertEquals(cas.getId(), 3);

	}

	public static Casillero TestSaltarCasillero(Casillero casillero, int salto) {
		for (int i = 0; i < salto; i++) {
			casillero.setId(casillero.getId() + i);
		}

		return casillero;
	}



}
