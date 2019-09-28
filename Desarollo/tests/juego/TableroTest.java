package juego;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

import juego.misc.ExcepcionArchivos;
import juego.tablero.Tablero;

public class TableroTest {
	private Tablero tablero;

	@Test
	public void crearTableroTest() throws ExcepcionArchivos, FileNotFoundException {
		tablero = new Tablero("../Mapas/tablero02.txt");
		Assert.assertEquals(71, this.tablero.getCasilleros().size());
	}

	@Test(expected = FileNotFoundException.class)
	public void crearTableroArchivoNoExisteTest() throws ExcepcionArchivos, FileNotFoundException {
		tablero = new Tablero("../Mapas/tablero_inexistente.txt");
	}

	@Test(expected = ExcepcionArchivos.class)
	public void crearTableroArchivoVacioTest() throws ExcepcionArchivos, FileNotFoundException {
		tablero = new Tablero("../Mapas/tableroVacio.txt");
	}

}
