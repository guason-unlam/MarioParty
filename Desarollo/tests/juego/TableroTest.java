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

	@Test
	public void crearTableroArchivoNoExisteTest() {
		try {
			tablero = new Tablero("../Mapas/tablero_inexistente.txt");
		} catch (FileNotFoundException |ExcepcionArchivos ex) {
			Assert.assertEquals(FileNotFoundException.class, ex.getClass());
		}
	}

	@Test
	public void crearTableroArchivoVacioTest() {
		try {
			tablero = new Tablero("../Mapas/tableroVacio.txt");
		} catch (FileNotFoundException | ExcepcionArchivos ex) {
			Assert.assertEquals(ExcepcionArchivos.class, ex.getClass());
		}
	}

}
