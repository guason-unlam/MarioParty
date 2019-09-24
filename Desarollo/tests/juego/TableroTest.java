package juego;

import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import juego.misc.LectorEscritor;
import juego.tablero.Tablero;
import juego.tablero.casillero.Casillero;

public class TableroTest {
	private LectorEscritor le;
	private Tablero tablero;

	@Before
	public void inicio() {
		le = new LectorEscritor();
	}

	@Test
	public void crearTablero() throws Exception {

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

}
