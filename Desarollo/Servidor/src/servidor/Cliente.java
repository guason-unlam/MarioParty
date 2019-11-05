package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import juego.Constantes;
import juego.lobby.Sala;
import juego.lobby.Usuario;
import juego.lobby.UsuarioDAO;

public class Cliente extends Thread {
	private Socket socket;
	private DataInputStream entrada;
	private DataOutputStream salida;
	private Usuario usuario;
	private Sala sala;

	public Cliente(Socket clienteIn, Socket clienteOut) {
		this.socket = clienteIn;
		try {
			entrada = new DataInputStream(clienteIn.getInputStream());

			salida = new DataOutputStream(clienteOut.getOutputStream());
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
				String cadena = this.entrada.readUTF();
				Message message = (Message) new Gson().fromJson(cadena, Message.class);
				System.out.println("RECIBIDO" + message.getType());
				switch (message.getType()) {
				// LOGIN
				case Constantes.LOGIN_REQUEST:
					properties = new Gson().fromJson((String) message.getData(), Properties.class);

					usuario = UsuarioDAO.loguear(properties.getProperty("username"),
							properties.getProperty("password"));

					if (usuario == null) {
						this.salida.flush();
						this.salida.writeUTF(new Message(Constantes.INCORRECT_LOGIN, null).toJson());
						System.out.println("[LOGIN] El usuario " + properties.getProperty("username")
								+ " fracaso en su inicio de sesion.");
					} else {
						boolean usuarioDuplicado = false;
						for (Usuario usuarioActivo : Servidor.getUsuariosActivos()) {

							if (usuarioActivo.getId() == usuario.getId()) {
								this.salida.flush();
								this.salida.writeUTF(new Message(Constantes.DUPLICATED_LOGIN, null).toJson());
								usuarioDuplicado = true;
								System.out.println("[LOGIN] El usuario " + properties.getProperty("username")
										+ " intento loguearse sin haber cerrado sesion.");
								break;
							}

						}
						if (!usuarioDuplicado) {
							Servidor.agregarAUsuariosActivos(usuario);
							this.salida.flush();
							this.salida.writeUTF(
									new Message(Constantes.CORRECT_LOGIN, new Gson().toJson(usuario)).toJson());
							System.out.println("[LOGIN] El usuario " + properties.getProperty("username")
									+ " ingreso correctamente.");
						}
					}
					break;
				// REGISTRO
				case Constantes.REGISTER_REQUEST:
					properties = new Gson().fromJson((String) message.getData(), Properties.class);

					int resultado = UsuarioDAO.registrar(properties.getProperty("username"),
							properties.getProperty("password"));

					switch (resultado) {
					case -1:
						this.salida.writeUTF(new Message(Constantes.REGISTER_INCORRECT, null).toJson());
						System.out.println("[REGISTRO] Se intento registrar el usuario "
								+ properties.getProperty("username") + "y ocurrio un error.");
						break;
					case 0:
						this.salida.writeUTF(new Message(Constantes.REGISTER_CORRECT, null).toJson());
						System.out.println("[REGISTRO] Se registro correctamente el usuario "
								+ properties.getProperty("username"));
						break;
					case 1:
						this.salida.writeUTF(new Message(Constantes.REGISTER_DUPLICATED, null).toJson());
						System.out.println("[REGISTRO] Se intento registrar el usuario "
								+ properties.getProperty("username") + ", pero el nombre esta duplicado en la DB.");
						break;
					}
					break;
				case Constantes.INDEX_SALAS:
					this.salida.writeUTF(new Message(Constantes.INDEX_SALAS, Servidor.getIndexSalas()).toJson());
					break;
				case Constantes.CREATE_ROOM_REQUEST:
					ArrayList<String> dataSala = (ArrayList<String>) message.getData();

					// Primero debo chequear que no exista una sala con el nombre
					if (Servidor.getSalaPorNombre(dataSala.get(0)) == null) {
						// Creo la sala
						if (dataSala.size() == 3) {
							sala = usuario.crearSala(dataSala.get(0), dataSala.get(1),
									Integer.valueOf(dataSala.get(2)));
						} else {
							sala = usuario.crearSala(dataSala.get(0), Integer.valueOf(dataSala.get(1)));
						}
						usuario.setSala(sala);

						// La agrego al array de control
						Servidor.agregarASalasActivas(sala);
						this.salida.writeUTF(new Message(Constantes.CREATE_ROOM_CORRECT, true).toJson());
					} else {
						this.salida.writeUTF(new Message(Constantes.CREATE_ROOM_INCORRECT, false).toJson());
					}

					break;
				case Constantes.LEAVE_ROOM_REQUEST:
					usuario.salirDeSala();
					sala = Servidor.getSalaPorNombre((String) message.getData());
					sala.sacarUsuarioDeSala(usuario);

					// En caso de que sea el ultimo usuario en la sala, se va a destruir la misma
					if (sala.getCapacidadActual() == 0 || sala.getJugadorCreador().equals(usuario)) {
						Servidor.eliminarSalaActiva(sala);
						// TODO: No deberia avisar o algo a los demas jugadores? se puede romper todo
						Servidor.informarSalaTermina(sala);

					}
					break;
				case Constantes.JOIN_ROOM_REQUEST:
					sala = Servidor.getSalaPorNombre((String) message.getData());
					if (sala.getCapacidadActual() + 1 <= sala.getCapacidadMaxima()) {
						usuario.setSala(sala);

						this.salida.writeUTF(
								new Message(Constantes.JOIN_ROOM_CORRECT, sala.agregarUsuario(usuario)).toJson());
					} else {

						this.salida.writeUTF(new Message(Constantes.JOIN_ROOM_INCORRECT, false).toJson());
					}
					break;
				case Constantes.LOGOUT_REQUEST:
					usuario = new Gson().fromJson((String) message.getData(), Usuario.class);
					for (Usuario usuarioEnServer : Servidor.getUsuariosActivos()) {
						if (usuario != null && usuarioEnServer.getId() == usuario.getId()) {
							Servidor.removerUsuarioActivo(usuarioEnServer);
							this.salida.flush();
							this.salida.writeUTF(new Message(Constantes.CORRECT_LOGOUT, null).toJson());
							break;
						} else {
							this.salida.flush();
							this.salida.writeUTF(new Message(Constantes.INCORRECT_LOGOUT, usuario).toJson());
						}
					}
					break;
				default:
					break;
				}
			} catch (IOException ex) {
				String mensaje = "";
				if (this.usuario != null) {
					mensaje = "[CLIENTE] El usuario " + this.usuario.getUsername() + " se ha desconectado.";
				} else {
					mensaje = "[CLIENTE] Cliente con la IP " + socket.getInetAddress().getHostName() + " desconectado.";
				}

				System.out.println(mensaje);
				conectado = false;
				for (Usuario usuarioActivo : Servidor.getUsuariosActivos()) {

					if (usuarioActivo.getId() == usuario.getId()) {
						Servidor.getUsuariosActivos().remove(usuario);
						break;
					}

				}
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
