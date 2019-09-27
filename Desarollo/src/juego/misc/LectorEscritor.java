package juego.misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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
	public void leerTablero(String arch, Tablero tablero) throws ExcepcionArchivos, FileNotFoundException {

		Scanner sc = new Scanner(new File(arch));

		int cantidadCasilleros = sc.nextInt();
		Map<Integer, Casillero> casilleros = new TreeMap<Integer, Casillero>();
		while (sc.hasNextLine()) {
			String[] partes = sc.nextLine().split("\\|");
			// Si entro aca, es porque tengo algo de texto
			// Y no es una linea vacia
			if (partes.length > 1) {

				String[] partesPadres = partes[3].split(",");
				String[] partesHijos = partes[4].split(",");
				ArrayList<Casillero> padres = new ArrayList<Casillero>();
				ArrayList<Casillero> hijos = new ArrayList<Casillero>();

				// Si no contiene ese casillero, entonces debo crearlo
				if (!casilleros.containsKey(Integer.valueOf(partes[0]))) {
					Casillero casillero = new Casillero();
					// Seteo x e y
					casillero.setPosicionX(Integer.valueOf(partes[1]));
					casillero.setPosicionY(Integer.valueOf(partes[2]));
					// System.out.println("Estoy en " + partes[0] + "\tpadres: " + partes[3] +
					// "\thijos:" + partes[4]);

					for (String valorP : partesPadres) {
						int valorNumerico = Integer.valueOf(valorP);
						if (valorNumerico > 0 && casilleros.containsKey(valorNumerico)) {
							padres.add(casilleros.get(valorNumerico));
							casilleros.get(valorNumerico).getSiguiente().add(casillero);
							// System.out.println("Agregue el padre " +
							// casilleros.get(valorNumerico).getId() + " al nodo "
							// + Integer.valueOf(partes[0]));
						}
					}

					for (String valorH : partesHijos) {
						int valorNumerico = Integer.valueOf(valorH);
						if (valorNumerico > 0 && casilleros.containsKey(valorNumerico)) {

							hijos.add(casilleros.get(valorNumerico));
							// System.out.println("Agregue el hijo " + casilleros.get(valorNumerico).getId()
							// + " al nodo "
							// + Integer.valueOf(partes[0]));
						}
					}
					casillero.setAnterior(padres);
					casillero.setSiguiente(hijos);
					casillero.setId(Integer.valueOf(partes[0]));
					casilleros.put(Integer.valueOf(partes[0]), casillero);
				} else {
					// System.out.println("Estoy en " + partes[0] + "\tpadres: " + partes[3] +
					// "\thijos:" + partes[4]);
					for (String valorP : partesPadres) {
						int valorNumerico = Integer.valueOf(valorP);
						if (valorNumerico > 0 && casilleros.containsKey(valorNumerico)
								&& !casilleros.get(Integer.valueOf(partes[0])).getAnteriores()
										.contains(casilleros.get(valorNumerico))) {
							casilleros.get(Integer.valueOf(partes[0])).getAnteriores()
									.add(casilleros.get(valorNumerico));
							// System.out.println("Agregue el padre " +
							// casilleros.get(valorNumerico).getId() + " al nodo "
							// + Integer.valueOf(partes[0]));
						}
					}

					for (String valorH : partesHijos) {
						int valorNumerico = Integer.valueOf(valorH);
						if (valorNumerico > 0 && casilleros.containsKey(valorNumerico)
								&& !casilleros.get(Integer.valueOf(partes[0])).getSiguiente()
										.contains(casilleros.get(valorNumerico))) {
							casilleros.get(Integer.valueOf(partes[0])).getSiguiente()
									.add(casilleros.get(valorNumerico));
							// System.out.println("Agregue el hijo " + casilleros.get(valorNumerico).getId()
							// + " al nodo "
							// + Integer.valueOf(partes[0]));
						}
					}

				}
			}
		}
		sc.close();

		if (casilleros.size() == cantidadCasilleros) {
			tablero.setCasilleros(casilleros);
			System.out.println(cantidadCasilleros + " casilleros agregados");
			// System.out.println(cantidadCasilleros + " casilleros agregados");
		} else {
			// System.out.println(cantidadCasilleros);
			// System.out.println(casilleros.keySet());
			throw new ExcepcionArchivos("No coincide el numero de casilleros con el final");
		}
	}
}
