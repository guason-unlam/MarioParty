package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonString;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import juego.Constantes;
import juego.lobby.TipoCondicionVictoria;
import juego.lobby.Usuario;
import juego.ventana.Coordinador;
import servidor.Message;
import servidor.Seguridad;

public class ConexionInterna extends Thread {
	private Socket socketIn;
	private Socket socketOut;
	private Usuario usuario;
	private Message message;
	private DataOutputStream salidaDatos;
	private DataInputStream entradaDatos;

	public ConexionInterna(Socket socketOut, Socket socketIn) {
		this.socketOut = socketOut;
		this.socketIn = socketIn;
		try {
			this.salidaDatos = new DataOutputStream(this.socketOut.getOutputStream());
			this.entradaDatos = new DataInputStream(this.socketIn.getInputStream());
		} catch (IOException ex) {
			System.out.println("Error al crear el stream de salida : " + ex.getMessage());
		} catch (NullPointerException ex) {
			System.out.println("El socket no se creo correctamente. ");
		}
	}

	// Ya mando la pw encriptada

	public Usuario logear(String usuario, String password) {

		try {
			String request = Json.createObjectBuilder().add("username", usuario)
					.add("password", Seguridad.encriptarContrasena(password)).build().toString();

			this.salidaDatos.writeUTF(new Message(Constantes.LOGIN_REQUEST, request).toJson());

			this.message = new Gson().fromJson(entradaDatos.readUTF(), Message.class);

			switch (this.message.getType()) {
			case Constantes.CORRECT_LOGIN:
				this.usuario = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create()
						.fromJson((String) message.getData(), Usuario.class);
				return this.usuario;
			case Constantes.INCORRECT_LOGIN:
				return null;
			case Constantes.DUPLICATED_LOGIN:
				return new Usuario(-1);
			default:
				return null;
			}
		} catch (Exception e) {
			System.out.println("[LOGIN] " + e.getMessage());
		}
		return null;
	}

	public Boolean logout(Usuario u) {

		try {

			this.salidaDatos.writeUTF(new Message(Constantes.LOGOUT_REQUEST, u).toJson());

			this.message = new Gson().fromJson(entradaDatos.readUTF(), Message.class);
			switch (this.message.getType()) {
			case Constantes.CORRECT_LOGOUT:
				return true;
			case Constantes.INCORRECT_LOGIN:
				return false;
			default:
				return false;
			}
		} catch (Exception e) {
			System.out.println("[LOGOUT] " + e.getMessage());
		}
		return null;
	}

	public Message registrar(String username, String password) {
		try {
			String request = Json.createObjectBuilder().add("username", username)
					.add("password", Seguridad.encriptarContrasena(password)).build().toString();

			this.salidaDatos.writeUTF(new Message(Constantes.REGISTER_REQUEST, request).toJson());

			this.message = new Gson().fromJson(entradaDatos.readUTF(), Message.class);
			return this.message;

		} catch (Exception e) {
			System.out.println("[REGISTRAR] " + e.getMessage());
		}

		return new Message(Constantes.REGISTER_REQUEST_INCORRECT, null);
	}

	public boolean unirseASala(String nombreSala) {
		try {
			this.message = new Message(Constantes.JOIN_ROOM_REQUEST, nombreSala);
			this.salidaDatos.writeUTF(this.message.toJson());
			while (true) {
				// Leo lo enviado por el sv
				this.message = new Gson().fromJson(entradaDatos.readUTF(), Message.class);
				// Depende el tipo de la respuesta
				switch (this.message.getType()) {
				case Constantes.JOIN_ROOM_CORRECT:
					return true;
				case Constantes.JOIN_ROOM_INCORRECT:
					return false;
				}
			}

		} catch (Exception ex) {
			System.out.println("[INGRESAR A SALA] " + ex.getMessage());
		}
		return false;
	}

	public boolean crearSala(ArrayList<String> datosSala) {
		try {

			this.message = new Message(Constantes.CREATE_ROOM_REQUEST, datosSala);
			this.salidaDatos.writeUTF(this.message.toJson());
			while (true) {
				// Leo lo enviado por el sv
				this.message = new Gson().fromJson(entradaDatos.readUTF(), Message.class);

				// Depende el tipo de la respuesta
				switch (this.message.getType()) {
				case Constantes.CREATE_ROOM_CORRECT:
					return true;
				case Constantes.CREATE_ROOM_INCORRECT:
					return false;
				}
			}

		} catch (Exception ex) {
			System.out.println("[CREAR SALA] " + ex.getMessage());
		}
		return false;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void salirSala(String nombreSala) {
		try {
			this.message = new Message(Constantes.LEAVE_ROOM_REQUEST, nombreSala);
			this.salidaDatos.writeUTF(this.message.toJson());
		} catch (Exception ex) {
			System.out.println("[SALIR SALA]" + ex.getMessage());
		}
	}

	// Metodo utilizado para hacer el refresh de las salas
	public String refreshRooms() {
		try {
			while (true) {
				this.message = new Gson().fromJson(entradaDatos.readUTF(), Message.class);
				if (message.getType() == Constantes.REQUEST_REFRESH_ROOMS) {
					return (String) message.getData();
				}
			}
		} catch (Exception ex) {
			System.out.println("[REFRESH ROOM]" + ex.getMessage());
		}
		return null;

	}

	public boolean usuariosEnSala() {
		try {
			this.salidaDatos.writeUTF(new Message(Constantes.TODOS_EN_SALA, null).toJson());
			Message retorno = (Message) new Gson().fromJson(this.entradaDatos.readUTF(), Message.class);
			if (retorno.getType().equals(Constantes.NOTICE_TODOS_EN_SALA))
				return (boolean) retorno.getData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean comenzarJuego(int totalBots, int totalRondas, TipoCondicionVictoria condicion, String mapa) {
		String request = "{\"" + Constantes.CANTIDAD_BOTS + "\":\"" + totalBots + "\",\"" + Constantes.TOTAL_RONDAS
				+ "\":\"" + totalRondas + "\",\"" + Constantes.CONDICION_VICTORIA + "\":\"" + condicion + "\",\""
				+ Constantes.MAPA + "\":\"" + mapa + "\"}";

		this.message = new Message(Constantes.NOTICE_ARRANCAR_JUEGO, request);
		try {
			this.salidaDatos.writeUTF(this.message.toJson());
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			// Leo lo enviado por el sv
			try {
				this.message = new Gson().fromJson(entradaDatos.readUTF(), Message.class);
				// Depende el tipo de la respuesta
				switch (this.message.getType()) {
				case Constantes.NOTICE_EMPEZA_JUEGO_CLIENTE:
					Coordinador.ventanaAdministracionSala
							.prepararArranqueJuego(((JsonObjectBuilder) this.message.getData()).build());
				}
			} catch (JsonSyntaxException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public int unirseASala(String sala, String pw) {
		try {
			String request = Json.createObjectBuilder().add("sala", sala).add("password", pw).build().toString();

			this.salidaDatos.writeUTF(new Message(Constantes.JOIN_ROOM_REQUEST_PASSWORD, request).toJson());
			while (true) {
				// Leo lo enviado por el sv
				this.message = new Gson().fromJson(entradaDatos.readUTF(), Message.class);
 				// Depende el tipo de la respuesta
				switch (this.message.getType()) {
				case Constantes.JOIN_ROOM_CORRECT:
					return 1;
				case Constantes.JOIN_ROOM_INCORRECT:
					return -1;
				case Constantes.INCORRECT_PW:
					return -2;
				}
			}

		} catch (Exception ex) {
			System.out.println("[INGRESAR A SALA] " + ex.getMessage());
		}
		return -1;
	}

	public String obtenerEstadisticasTabla(String username) {
		try {
			String request = Json.createObjectBuilder().add("username", username).build().toString();

			this.salidaDatos.writeUTF(new Message(Constantes.SOLICITUD_TABLA_PARTIDAS, request).toJson());
			while (true) {
				// Leo lo enviado por el sv
				this.message = new Gson().fromJson(entradaDatos.readUTF(), Message.class);
				// Depende el tipo de la respuesta
				switch (this.message.getType()) {
				case Constantes.TABLA_PARTIDAS:
					return (String) this.message.getData();
				case Constantes.INCORRECT_TABLA_PARTIDA:
					return null;
				}
			}

		} catch (Exception ex) {
			System.out.println("[HISTORIAL TABLA] " + ex.getMessage());
		}
		return null;
	}

	public String obtenerEstadisticas(String username) {
		try {
			String request = Json.createObjectBuilder().add("username", username).build().toString();

			this.salidaDatos.writeUTF(new Message(Constantes.HISTORIAL, request).toJson());
			while (true) {
				// Leo lo enviado por el sv
				this.message = new Gson().fromJson(entradaDatos.readUTF(), Message.class);
				// Depende el tipo de la respuesta
				switch (this.message.getType()) {
				case Constantes.CORRECT_HISTORIAL:
					return (String) this.message.getData();
				case Constantes.INCORRECT_HISTORIAL:
					return null;
				}
			}

		} catch (Exception ex) {
			System.out.println("[HISTORIAL] " + ex.getMessage());
		}
		return null;
	}
}