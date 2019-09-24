package juego.misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import juego.item.Item;
import juego.item.ModificadorDado;
import juego.item.ModificadorMonedas;
import juego.item.ModificadorPosicion;
import juego.tablero.casillero.Casillero;

public class LectorEscritor {
	/*
	 * Mi estructura de archivo va a ser,
	 * IDPosicionX-PosicionY-TipoItem-Nombre-Descripcion-CantidadMaxima-
	 * multiplicador O tambien (En el caso de no tener nada) ID-PosicionX-PosicionY
	 */
	public void leer(String arch, Map<Integer, Casillero> casilleros) throws Exception {
		Scanner sc = new Scanner(new File(arch));
		// Si ocurre puede quedar null y kaboom
		Item item = null;

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
		sc.close();
	}
}
