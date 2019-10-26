package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.Socket;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import juego.Constantes;
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
System.out.println(tipoDeMensaje);
				if (tipoDeMensaje.equals(Constantes.LOGIN_REQUEST_SERVER)) {
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

			} catch (IOException ex) {
//				System.out.println(ex.getMessage() + "[ConexionServidor] Cliente con la IP "
//						+ socket.getInetAddress().getHostAddress() + " desconectado.");
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

}
