package juego;

import org.junit.Assert;
import org.junit.Test;

import juego.lobby.CondicionVictoria;
import juego.lobby.TipoCondicionVictoria;

public class CondicionVictoriaTest {

	@Test
	public void crearCondicionVictoria() {
		CondicionVictoria cv = new CondicionVictoria(TipoCondicionVictoria.RONDAS, 2);
		Assert.assertEquals(TipoCondicionVictoria.RONDAS, cv.getTipo());
		Assert.assertEquals(2, cv.getCantidad());
	}
}
