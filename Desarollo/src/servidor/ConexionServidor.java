package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.Socket;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

import juego.Constantes;
import juego.lobby.Sala;
import juego.lobby.Usuario;

public class ConexionServidor extends Thread {

	private Socket socket;
	private DataInputStream entrada;
	private DataOutputStream salida;
	private Usuario usuario;

	public ConexionServidor(Socket servidorIn, Socket servidorOut) {
		this.socket = servidorIn;

		try {
			this.entrada = new DataInputStream(servidorIn.getInputStream());
			this.salida = new DataOutputStream(servidorOut.getOutputStream());

		} catch (IOException ex) {
			System.out.println("Error al crear los stream de entrada y salida : " + ex.getMessage());
		}
	}

	@Override
	public void run() {
		boolean conectado = true;

		while (conectado) {

			try {
				String entrada = (String) this.entrada.readUTF();
				JsonReader jsonReader = Json.createReader(new StringReader(entrada));
				JsonObject entradaJson = jsonReader.readObject();
				jsonReader.close();

				String tipoDeMensaje = entradaJson.getString("type");

				if (tipoDeMensaje.equals(Constantes.LOGIN_REQUEST_SV)) {
					for (Usuario u : Servidor.getUsuariosActivos()) {
						if (u.getUsername().equals(entradaJson.getString("username"))) {
							this.usuario = u;
						}
					}

					if (this.usuario != null) {
						String respuestaLogueoOk = Json.createObjectBuilder()
								.add("type", Constantes.LOGIN_REQUEST_SERVER_CORRECT).build().toString();
						System.out.println("[LOGIN]Usuario " + this.usuario.getUsername() + " se logeo correctamente.");
						this.salida.writeUTF(respuestaLogueoOk);
					}
				}

				// A todas las ventanasUnirSala
				if (tipoDeMensaje.equals(Constantes.CREATE_ROOM_SV_REQUEST)
						|| tipoDeMensaje.equals(Constantes.JOIN_ROOM_SV_REQUEST)
						|| tipoDeMensaje.equals(Constantes.INDEX_ROOM_REQUEST)
						|| tipoDeMensaje.equals(Constantes.LEAVE_ROOM_REQUEST)) {
					actualizarSalasClientes();
				}

				// A una sala particular
				if (tipoDeMensaje.equals(Constantes.REFRESH_PARAM_ROOM)
						|| tipoDeMensaje.equals(Constantes.LEAVE_ROOM_REQUEST)
						|| tipoDeMensaje.equals(Constantes.JOIN_ROOM_SV_REQUEST)) {
					actualizarClientesSalaUnica(entradaJson);
				}

			} catch (IOException ex) {
				System.out.println(ex.getMessage() + "[ConexionServidor] Cliente con la IP "
						+ socket.getInetAddress().getHostAddress() + " desconectado.");
				conectado = false;

				try {
					this.entrada.close();
					this.salida.close();
				} catch (IOException ex2) {
					System.out.println("Error al cerrar los stream de entrada y salida:" + ex2.getMessage());
				}
			}
		}

		Servidor.desconectarServidor(this);
	}

	public void actualizarClientesSalaUnica(JsonObject entradaJson) {

		String tipoDeMensaje = entradaJson.getString("type");

		Sala salaARefrescar = Servidor.getSalaPorNombre(entradaJson.getString("sala"));

		JsonObject paqueteAEnviar;
		if (salaARefrescar != null) {

			if (tipoDeMensaje.equals(Constantes.JOIN_ROOM_SV_REQUEST)
					|| tipoDeMensaje.equals(Constantes.LEAVE_ROOM_REQUEST)) {

				JsonArrayBuilder usernamesConectadosALaSala = Json.createArrayBuilder();

				for (Usuario u : salaARefrescar.getUsuariosActivos()) {
					usernamesConectadosALaSala.add(u.getUsername());
				}

				paqueteAEnviar = Json.createObjectBuilder().add("type", Constantes.REFRESH_ROOM)
						.add("usuarios", usernamesConectadosALaSala.build())
						.add("admin", salaARefrescar.getJugadorCreador().getUsername()).build();
			} else {
				paqueteAEnviar = armarPaqueteParamSala(entradaJson);
			}

			for (ConexionServidor c : Servidor.getServidoresConectados()) {
				try {
					if (usuarioEstaEnLaSala(c.getUsuario(), salaARefrescar)) {
						c.salida.writeUTF(paqueteAEnviar.toString());
					}
				} catch (IOException e) {
					System.out.println("Fallo la escritura de datos de actualizar parametros sala");
				}
			}

			// En caso que me quiera unir a una sala, tengo que avisarle al admin
			if (tipoDeMensaje.equals(Constantes.JOIN_ROOM_SV_REQUEST)) {
				for (ConexionServidor c : Servidor.getServidoresConectados()) {

					if (c.getUsuario().equals(salaARefrescar.getJugadorCreador())) {
						paqueteAEnviar = Json.createObjectBuilder().add("type", Constantes.JOIN_ROOM_PARAM).build();
						try {
							c.salida.writeUTF(paqueteAEnviar.toString());
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					}
				}
			}
		}
	}

	private boolean usuarioEstaEnLaSala(Usuario user, Sala sala) {
		for (Usuario usuarioActual : sala.getUsuariosActivos()) {
			if (user != null && usuarioActual.getId() == user.getId())
				return true;
		}
		return false;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void escribirSalida(JsonObject dato) {
		try {
			this.salida.writeUTF(dato.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void actualizarSalasClientes() {
		JsonArray datosDeSalas = Servidor.getIndexSalas();
		JsonObjectBuilder paqueteActualizacionDeSalas = Json.createObjectBuilder();
		String cadenaSalida = paqueteActualizacionDeSalas.add("type", Constantes.INDEX_SALAS)
				.add("datosDeSalas", datosDeSalas).build().toString();

		for (ConexionServidor conexion : Servidor.getServidoresConectados()) {
			try {
				conexion.salida.writeUTF(cadenaSalida);
			} catch (IOException e) {
				System.out.println("[ENVIO SALA] Error al enviar las salas");
			}
		}
	}

	private JsonObject armarPaqueteParamSala(JsonObject entradaJson) {
		return Json.createObjectBuilder().add("type", Constantes.REFRESH_PARAM_ROOM)
				.add("condicion", entradaJson.getString("condicion"))
				.add("cantidad", entradaJson.getString("cantidad"))
				.add("mapa", entradaJson.getString("mapa"))
				.add("bots", entradaJson.getString("bots")).build();
	}
}
