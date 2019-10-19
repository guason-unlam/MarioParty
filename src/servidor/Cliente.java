package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import juego.Constantes;
import lobby.Sala;
import lobby.Usuario;
import lobby.UsuarioDAO;

public class Cliente extends Thread {
	private Socket socket;
	private DataInputStream entrada;
	private DataOutputStream salida;
	private Usuario usuario;
	private Sala sala;

	public Cliente(Socket socket) {
		this.socket = socket;
		try {
			entrada = new DataInputStream(socket.getInputStream());

			salida = new DataOutputStream(socket.getOutputStream());

		} catch (IOException ex) {
			System.out.println("Error al crear los stream de entrada y salida : " + ex.getMessage());

		}
	}

	@Override
	public void run() {
		boolean conectado = true;
		Properties properties;

		while (conectado) {
			try {
				Message message = (Message) new Gson().fromJson(this.entrada.readUTF(), Message.class);
				switch (message.getType()) {
				// LOGIN
				case Constantes.LOGIN_REQUEST:
					properties = new Gson().fromJson((String) message.getData(), Properties.class);

					usuario = UsuarioDAO.loguear(properties.getProperty("username"),
							properties.getProperty("hashPassword"));

					if (usuario == null) {
						this.salida.flush();
						this.salida.writeUTF(new Message(Constantes.INCORRECT_LOGIN, null).toJson());
					} else {
						boolean usuarioDuplicado = false;
						for (Usuario usuarioActivo : Servidor.getUsuariosActivos()) {

							if (usuarioActivo.getId() == usuario.getId()) {
								this.salida.flush();
								this.salida.writeUTF(new Message(Constantes.DUPLICATED_LOGIN, null).toJson());
								usuarioDuplicado = true;
								break;
							}

						}
						if (!usuarioDuplicado) {
							Servidor.agregarAUsuariosActivos(usuario);
							this.salida.flush();
							this.salida.writeUTF(
									new Message(Constantes.CORRECT_LOGIN, new Gson().toJson(usuario)).toJson());
						}
					}
					break;
				// REGISTRO
				case Constantes.REGISTER_REQUEST:
					properties = new Gson().fromJson((String) message.getData(), Properties.class);

					int resultado = UsuarioDAO.registrar(properties.getProperty("username"),
							properties.getProperty("hashPassword"));

					switch (resultado) {
					case -1:
						this.salida.writeUTF(new Message(Constantes.REGISTER_INCORRECT, null).toJson());
						break;
					case 0:
						this.salida.writeUTF(new Message(Constantes.REGISTER_CORRECT, null).toJson());
						break;
					case 1:
						this.salida.writeUTF(new Message(Constantes.REGISTER_DUPLICATED, null).toJson());
						break;
					}

					break;
				default:
					break;
				}
			} catch (IOException ex) {
				String mensaje = "Cliente con la IP " + socket.getInetAddress().getHostName() + " desconectado.";
				System.out.println(mensaje);
				conectado = false;

				try {
					entrada.close();
					salida.close();
				} catch (IOException ex2) {
					String mensajeError2 = "Error al cerrar los stream de entrada y salida :" + ex2.getMessage();
					System.out.println(mensajeError2);
				}
			} catch (JsonSyntaxException e) {
				System.out.println("Error de sintaxis en el json " + e.getMessage());
			}
		}
		Servidor.desconectar(this);
	}

	public DataOutputStream getSalida() {
		return this.salida;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

}
