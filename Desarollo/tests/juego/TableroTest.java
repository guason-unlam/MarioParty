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
		tablero = new Tablero("./tests/juego/files/tableroRenovado.txt");
		Assert.assertEquals(13, this.tablero.getCasilleros().size());
		//for (Entry<Integer, Casillero> m : tablero.getCasilleros().entrySet()) {
			// System.out.println(m.getKey() + " " + m.getValue().getPosicionX() + " " +
			// m.getValue().getPosicionY());
		/*	System.out.println("NODO N° " + m.getKey());
			System.out.println("Padres");

			if (m.getValue().getAnteriores().size() > 0) {
				for (Casillero element : m.getValue().getAnteriores()) {
					System.out.println(element.getId() + "-");
				}
			}
			System.out.println("Hijos");

			if (m.getValue().getSiguiente().size() > 0) {
				for (Casillero element : m.getValue().getSiguiente()) {
					System.out.println(element.getId() + "-");
				}
			}*/
		//}
	}

//	@Test
//	@Ignore
//	public void crearTablero2Test() throws ExcepcionArchivos, FileNotFoundException {
//		tablero = new Tablero("./tests/juego/files/tablero01.txt");
//		for (Entry<Integer, Casillero> m : tablero.getCasilleros().entrySet()) {
//			System.out.println(m.getKey() + " " + m.getValue().getPosicionX() + " " + m.getValue().getPosicionY());
//			if (m.getValue().getItem() != null) {
//				System.out.println("\t" + m.getValue().getItem().getNombre() + " "
//						+ m.getValue().getItem().getDescripcion() + " " + m.getValue().getItem().getCantidadMaxima()
//						+ " " + m.getValue().getItem().getMultiplicador());
//			}
//		}
//	}

	@Test(expected = FileNotFoundException.class)
	public void crearTableroArchivoNoExisteTest() throws ExcepcionArchivos, FileNotFoundException {
		tablero = new Tablero("./tests/juego/files/tablero_inexistente.txt");
	}

	@Test(expected = ExcepcionArchivos.class)
	public void crearTableroArchivoVacioTest() throws ExcepcionArchivos, FileNotFoundException {
		tablero = new Tablero("./tests/juego/files/tablero02.txt");
	}


}
