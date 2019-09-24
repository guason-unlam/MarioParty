package juego.misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import juego.item.Item;
import juego.item.ModificadorDado;
import juego.item.ModificadorMonedas;
import juego.item.ModificadorPosicion;
import juego.tablero.Tablero;
import juego.tablero.casillero.Casillero;

/*
 * TODO validar archivos
 */
public class LectorEscritor {

	/*
	 * Mi estructura de archivo va a ser,
	 * IDPosicionX-PosicionY-TipoItem-Nombre-Descripcion-CantidadMaxima-
	 * multiplicador O tambien (En el caso de no tener nada) ID-PosicionX-PosicionY
	 */
	public void leerTablero(String arch, Tablero tablero) throws Exception {

		Scanner sc = new Scanner(new File(arch));
		// Si ocurre puede quedar null y kaboom
		Item item = null;
		String[] parts = sc.nextLine().split("\\|");

		// Primer linea tiene idTablero, dimensionX, dimensionY
		if (parts.length > 0) {
			tablero.setId(Integer.parseInt(parts[0]));
			tablero.setDimensionX(Integer.parseInt(parts[1]));
			tablero.setDimensionY(Integer.parseInt(parts[2]));
		} else {
			sc.close();
			throw new Exception("Archivo mal configurado");
		}

		Map<Integer, Casillero> casilleros = new TreeMap<Integer, Casillero>();
		while (sc.hasNextLine()) {
			Casillero c = new Casillero();
			parts = sc.nextLine().split("\\|");
			c.setId(Integer.parseInt(parts[0]));
			c.setPosicionX(Integer.parseInt(parts[1]));
			c.setPosicionY(Integer.parseInt(parts[2]));

			// Si tiene item asociado
			if (parts.length > 3) {
				switch (parts[3]) {
				// Modificador de posicion
				case "MP":
					item = new ModificadorPosicion(parts[4], parts[5], Integer.parseInt(parts[6]),
							Integer.parseInt(parts[7]));
					break;
				case "MM":
					item = new ModificadorMonedas(parts[4], parts[5], Integer.parseInt(parts[6]),
							Integer.parseInt(parts[7]));
					break;
				case "MD":
					item = new ModificadorDado(parts[4], parts[5], Integer.parseInt(parts[6]),
							Integer.parseInt(parts[7]));
					break;
				default:
					throw new Exception("Ocurrio un error al cargar el archivo");
				}
			}
			c.setItem(item);
			c.setPrimeraVez(true);
			c.setAnterior(null);
			c.setSiguiente(null);
			c.setPersonajes(null);

			casilleros.put(c.getId(), c);

		}

		tablero.setCasilleros(casilleros);
		sc.close();
	}
}
