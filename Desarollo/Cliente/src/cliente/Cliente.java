package cliente;

import java.io.IOException;
import java.net.Socket;

import juego.Constantes;
import juego.ventana.Coordinador;
import juego.ventana.VentanaLogin;

public class Cliente {
	private static Socket servidorIn;
	private static Socket servidorOut;
	private static Socket clienteIn;
	private static Socket clienteOut;
	private static ConexionServidor conexionServidor;
	private static ConexionInterna conexionInterna;
	private static Coordinador coordinador;

	public static void main(String[] args) {
		new Cliente();
	}

	public Cliente() {
		try {
			clienteOut = new Socket(Constantes.IP, Constantes.PUERTO_1);
			clienteIn = new Socket(Constantes.IP, Constantes.PUERTO_2);

			conexionInterna = new ConexionInterna(clienteOut, clienteIn);

			// Ahora creo el par para el server
			servidorOut = new Socket(Constantes.IP, Constantes.PUERTO_3);
			servidorIn = new Socket(Constantes.IP, Constantes.PUERTO_4);
			conexionServidor = new ConexionServidor(servidorOut, servidorIn);

			conexionServidor.start();
			coordinador = new Coordinador();

			VentanaLogin login = new VentanaLogin();
			login.setVisible(true);

		} catch (IOException ex) {
			System.out.println("No se ha podido conectar con el servidor (" + ex.getMessage() + ").");
		}
	}

	public static ConexionInterna getConexionInterna() {
		return conexionInterna;
	}

	public static Socket getSocket() {
		return clienteOut;
	}

	public static ConexionServidor getConexionServidor() {
		return conexionServidor;
	}

	public static Coordinador getCoordinador() {
		return coordinador;
	}

}
