package juego;

import java.io.FileNotFoundException;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import juego.misc.ExcepcionArchivos;
import juego.misc.LectorEscritor;
import juego.tablero.Tablero;
import juego.tablero.casillero.Casillero;

public class TableroTest {
	private Tablero tablero;

	@Test
	public void crearTableroTest() throws ExcepcionArchivos, FileNotFoundException {
		tablero = new Tablero("./tests/juego/files/tableroRenovado.txt");
		for (Entry<Integer, Casillero> m : tablero.getCasilleros().entrySet()) {
			// System.out.println(m.getKey() + " " + m.getValue().getPosicionX() + " " +
			// m.getValue().getPosicionY());
			System.out.println("NODO N° " + m.getKey());
			System.out.println("Padres");

			if (m.getValue().getAnteriores().size() > 0) {
				for (Casillero element : m.getValue().getAnteriores()) {
					System.out.println(element.getId() + "-");
				}
			}
			System.out.println("Hijos");

			if (m.getValue().getSiguiente().size() > 0) {
				for (Casillero element : m.getValue().getSiguiente()) {
					System.out.print(element.getId() + "-");
				}
			}
			if (m.getValue().getItem() != null) {
				System.out.println("\t" + m.getValue().getItem().getNombre() + " "
						+ m.getValue().getItem().getDescripcion() + " " + m.getValue().getItem().getCantidadMaxima()
						+ " " + m.getValue().getItem().getMultiplicador());
			}
		}
	}

	@Test
	@Ignore
	public void crearTablero2Test() throws ExcepcionArchivos, FileNotFoundException {
		tablero = new Tablero("./tests/juego/files/tablero01.txt");
		for (Entry<Integer, Casillero> m : tablero.getCasilleros().entrySet()) {
			System.out.println(m.getKey() + " " + m.getValue().getPosicionX() + " " + m.getValue().getPosicionY());
			if (m.getValue().getItem() != null) {
				System.out.println("\t" + m.getValue().getItem().getNombre() + " "
						+ m.getValue().getItem().getDescripcion() + " " + m.getValue().getItem().getCantidadMaxima()
						+ " " + m.getValue().getItem().getMultiplicador());
			}
		}
	}

	@Test(expected = FileNotFoundException.class)
	@Ignore
	public void crearTableroArchivoNoExisteTest() throws ExcepcionArchivos, FileNotFoundException {
		tablero = new Tablero("./tests/juego/files/tablero_inexistente.txt");
	}

	@Test(expected = ExcepcionArchivos.class)
	@Ignore
	public void crearTableroArchivoVacioTest() throws ExcepcionArchivos, FileNotFoundException {
		tablero = new Tablero("./tests/juego/files/tablero02.txt");
	}

	@Test(expected = ExcepcionArchivos.class)
	@Ignore
	public void crearTableroArchivoItemIncorrectoTest() throws ExcepcionArchivos, FileNotFoundException {
		tablero = new Tablero("./tests/juego/files/tablero03.txt");
	}
}
