package juego.tablero;

import java.util.Map;
import java.util.TreeMap;

import juego.misc.LectorEscritor;
import juego.tablero.casillero.Casillero;

public class Tablero {
	// Me interesa tener un id->value, ordenado
	// Puede cambiar en el futuro
	Map<Integer, Casillero> casilleros;

	public Tablero(String arch) throws Exception {
		this.casilleros = new TreeMap<>();
		crearCasillerosYCrearTablero(arch);
	}

	private void crearCasillerosYCrearTablero(String arch) throws Exception {
		// Por ahora voy a usar un .txt para levantar los casilleros
		LectorEscritor le = new LectorEscritor();
		le.leer(arch, this.casilleros);
	}
}
