package juego.ventana;

import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import cliente.Cliente;
import juego.Constantes;

public class Coordinador extends Thread {

	private static VentanaElegirSala ventanaElegirSala;
	private static JsonArray datosDeSalasDisponibles;

	public Coordinador() {
		this.start();
	}

	public void run() {
		while (true) {
			String stringEntrada;
			try {
				stringEntrada = Cliente.getConexionServidor().getEntrada().readUTF();
				JsonReader jsonReader = Json.createReader(new StringReader(stringEntrada));
				JsonObject entradaJson = jsonReader.readObject();
				jsonReader.close(); // Cierro el stream hasta nuevo aviso
				System.out.println("ENTRADA" + entradaJson.getString("type"));

				switch (entradaJson.getString("type")) {
				case Constantes.INDEX_SALAS:
					System.out.println("Index!");
					datosDeSalasDisponibles = entradaJson.getJsonArray("datosDeSalas");
System.out.println(datosDeSalasDisponibles.get(0));
					if (ventanaElegirSala != null) {
						ventanaElegirSala.indexSalas(datosDeSalasDisponibles);
					}
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static void setVentanaUnirSala(VentanaElegirSala ventanaElegirSala) {
		Coordinador.ventanaElegirSala = ventanaElegirSala;
		if (datosDeSalasDisponibles != null) {
			ventanaElegirSala.indexSalas(datosDeSalasDisponibles);
		}
	}

}