package juego.ventana;

import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import cliente.Cliente;
import juego.Constantes;
import juego.ventana.minijuego.VentanaResultadosMiniJuego;

public class Coordinador extends Thread {

	private static VentanaElegirSala ventanaElegirSala;
	private static JsonArray datosDeSalasDisponibles;
	private static JsonArray datosDeMinijuego;
	private static VentanaAdministracionSala ventanaAdministracionSala;
	private static VentanaResultadosMiniJuego ventanaResultadosMiniJuego;
	private static JsonArray datosDeFinMinijuego;
	private static VentanaJuego ventanaJuego;

	public Coordinador() {
		this.start();
	}

	@Override
	public void run() {
		while (true) {
			String stringEntrada;
			try {
				stringEntrada = Cliente.getConexionServidor().getEntrada().readUTF();
				JsonReader jsonReader = Json.createReader(new StringReader(stringEntrada));
				JsonObject entradaJson = jsonReader.readObject();
				jsonReader.close(); // Cierro el stream hasta nuevo aviso

				switch (entradaJson.getString("type")) {
				case Constantes.INDEX_SALAS:

					datosDeSalasDisponibles = entradaJson.getJsonArray("datosDeSalas");

					if (ventanaElegirSala != null) {
						ventanaElegirSala.indexSalas(datosDeSalasDisponibles);
					}
					break;
				case Constantes.PUNTOS_MINIJUEGO:
					datosDeMinijuego = entradaJson.getJsonArray("datosDeMinijuego");

					if (ventanaResultadosMiniJuego != null) {
						ventanaResultadosMiniJuego.refrescarPuntaje(datosDeMinijuego);
					}
					break;
				case Constantes.FIN_MINIJUEGO:

					datosDeFinMinijuego = entradaJson.getJsonArray("datosDeFinMinijuego");

					if (ventanaResultadosMiniJuego != null) {
						ventanaResultadosMiniJuego.mostrarPantallaFinal(datosDeFinMinijuego);
					}
					break;
				case Constantes.REFRESH_PARAM_ROOM:
				case Constantes.REFRESH_ROOM:
					try {
						Coordinador.ventanaAdministracionSala.actualizarUsuarios(entradaJson);
						Coordinador.ventanaAdministracionSala.validacionBotonJugar();
					} catch (Exception e) {
						System.out.println("[REFRESCAR SALA]" + e.getCause());
					}
					break;
				case Constantes.JOIN_ROOM_PARAM:
					Coordinador.ventanaAdministracionSala.validacionBotonJugar();
					Coordinador.ventanaAdministracionSala.actualizarSala();
					break;

				case Constantes.FIN_SALA:
					Coordinador.ventanaAdministracionSala.cerrarSala();
					ventanaElegirSala.setVisible(true);
					break;
				default:
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

	public static void setVentanaElegirSala(VentanaElegirSala ventanaElegirSala) {
		Coordinador.ventanaElegirSala = ventanaElegirSala;
	}

	public static void setDatosDeSalasDisponibles(JsonArray datosDeSalasDisponibles) {
		Coordinador.datosDeSalasDisponibles = datosDeSalasDisponibles;
	}

	public static void setVentanaAdministracionSala(VentanaAdministracionSala ventanaAdministracionSala) {
		Coordinador.ventanaAdministracionSala = ventanaAdministracionSala;
	}

	public static VentanaAdministracionSala getVentanaAdministracionSala() {
		return ventanaAdministracionSala;
	}

	public static VentanaElegirSala getVentanaElegirSala() {
		return ventanaElegirSala;
	}

	public static JsonArray getDatosDeSalasDisponibles() {
		return datosDeSalasDisponibles;
	}

	public static VentanaResultadosMiniJuego getVentanaResultadosMiniJuego() {
		return ventanaResultadosMiniJuego;
	}

	public static void setVentanaResultadosMiniJuego(VentanaResultadosMiniJuego ventanaResultadosMiniJuego) {
		Coordinador.ventanaResultadosMiniJuego = ventanaResultadosMiniJuego;
	}

	public static VentanaJuego getVentanaJuego() {
		return ventanaJuego;
	}

	public static void setVentanaJuego(VentanaJuego ventanaJuego) {
		Coordinador.ventanaJuego = ventanaJuego;
	}
}