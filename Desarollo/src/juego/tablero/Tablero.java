package juego.tablero;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.TreeMap;

import juego.misc.ExcepcionArchivos;
import juego.misc.LectorEscritor;
import juego.tablero.casillero.Casillero;

public class Tablero {
	// Me interesa tener un id->value, ordenado
	// Puede cambiar en el futuro
	Map<Integer, Casillero> casilleros;
	private int id;
	private int dimensionX;
	private int dimensionY;

	public Tablero(String arch) throws ExcepcionArchivos, FileNotFoundException {
		this.casilleros = new TreeMap<Integer, Casillero>();
		crearCasillerosYCrearTablero(arch);
	}

	private void crearCasillerosYCrearTablero(String arch) throws ExcepcionArchivos, FileNotFoundException {
		// Por ahora voy a usar un .txt para levantar los casilleros
		LectorEscritor le = new LectorEscritor();
		le.leerTablero(arch, this);
	}

	public Map<Integer, Casillero> getCasilleros() {
		return casilleros;
	}

	public void setCasilleros(Map<Integer, Casillero> casilleros) {
		this.casilleros = casilleros;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDimensionX() {
		return dimensionX;
	}

	public void setDimensionX(int dimensionX) {
		this.dimensionX = dimensionX;
	}

	public int getDimensionY() {
		return dimensionY;
	}

	public void setDimensionY(int dimensionY) {
		this.dimensionY = dimensionY;
	}
}
