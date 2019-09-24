package juego.misc;

import java.io.File;
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
	public void leer(String arch, Tablero tablero) throws Exception {
		Scanner sc = new Scanner(new File(arch));
		// Si ocurre puede quedar null y kaboom
		Item item = null;
		// Primer linea tiene idTablero, dimensionX, dimensionY
		if (sc.hasNextLine()) {
			tablero.setId(sc.nextInt());
			tablero.setDimensionX(sc.nextInt());
			tablero.setDimensionY(sc.nextInt());
		} else {
			sc.close();
			throw new Exception("Archivo mal configurado");
		}
		Map<Integer, Casillero> casilleros = new TreeMap<Integer, Casillero>();
		while (sc.hasNextLine()) {
			Casillero c = new Casillero();

			c.setId(sc.nextInt());
			c.setPosicionX(sc.nextInt());
			c.setPosicionY(sc.nextInt());
			// Si tiene item asociado
			if (sc.hasNextLine()) {
				switch (sc.nextLine()) {
				// Modificador de posicion
				case "MP":
					item = new ModificadorPosicion(sc.nextLine(), sc.nextLine(), sc.nextInt(), sc.nextInt());
					break;
				case "MM":
					item = new ModificadorMonedas(sc.nextLine(), sc.nextLine(), sc.nextInt(), sc.nextInt());
					break;
				case "MD":
					item = new ModificadorDado(sc.nextLine(), sc.nextLine(), sc.nextInt(), sc.nextInt());
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
