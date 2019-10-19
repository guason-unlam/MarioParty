package cliente;

import java.io.IOException;
import java.net.Socket;

import juego.Constantes;

public class Cliente {
	private static Socket socketOut;
	private static Socket socketIn;
	private static ConexionServidor conexionServidor;

	public static void main(String[] args) {
		new Cliente();
	}

	public Cliente() {
		try {
			socketOut = null;
			socketIn = new Socket(Constantes.IP, Constantes.ENTRADA_CLIENTE);

			conexionServidor = new ConexionServidor(socketOut, socketIn);

			PantallaLogin login = new PantallaLogin();
			login.setVisible(true);

		} catch (IOException ex) {
			System.out.println("No se ha podido conectar con el servidor (" + ex.getMessage() + ").");
		}
	}

	public static ConexionServidor getConexionServidor() {
		return conexionServidor;
	}

	public static Socket getSocket() {
		return socketOut;
	}
}
