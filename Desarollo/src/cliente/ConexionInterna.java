package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.json.Json;

import com.google.gson.Gson;

import juego.Constantes;
import juego.lobby.Usuario;
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

			this.message = (Message) new Gson().fromJson((String) entradaDatos.readUTF(), Message.class);
			switch (this.message.getType()) {
			case Constantes.CORRECT_LOGIN:
				this.usuario = new Gson().fromJson((String) message.getData(), Usuario.class);
				return this.usuario;
			case Constantes.INCORRECT_LOGIN:
				return null;
			case Constantes.DUPLICATED_LOGIN:
				return new Usuario(-1);
			default:
				return null;
			}
		} catch (Exception e) {
			System.out.println("Error login " + e.getMessage());
		}
		return null;
	}

	public Message registrar(String username, String password) {
		try {
			String request = Json.createObjectBuilder().add("username", username)
					.add("password", Seguridad.encriptarContrasena(password)).build().toString();

			this.salidaDatos.writeUTF(new Message(Constantes.REGISTER_REQUEST, request).toJson());

			this.message = (Message) new Gson().fromJson((String) entradaDatos.readUTF(), Message.class);
			return this.message;

		} catch (Exception e) {
			System.out.println("[ERROR] Registro Usuario - " + e.getMessage());
		}

		return new Message(Constantes.REGISTER_REQUEST_INCORRECT, null);
	}

	public boolean unirseASala(String nombreSala) {
		try {

			this.message = new Message(Constantes.CREATE_ROOM_REQUEST, nombreSala);
			this.salidaDatos.writeUTF(this.message.toJson());
			while (true) {
				//Leo lo enviado por el sv
				this.message = (Message) new Gson().fromJson((String) entradaDatos.readUTF(), Message.class);

				//Depende el tipo de la respuesta
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

}