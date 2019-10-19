package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Servidor {
	private static final int PUERTO = 7777;
	private static final int MAXIMASCONEXIONESIMULTANEAS = 2;

	public static void main(String[] args) {
		ServerSocket servidor = null;
		Socket socket = null;

		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");

			SessionFactory factory = cfg.buildSessionFactory();
			Session session = factory.openSession();
			// Se crea el serverSocket para empezar a escuchar a los clientes.
			servidor = new ServerSocket(PUERTO, MAXIMASCONEXIONESIMULTANEAS);
			System.out.println("Servidor corriendo en " + servidor.getInetAddress() + ":" + servidor.getLocalPort());
			// Queda corriendo
			while (true) {
				// Acepto las conexiones que llegan
				socket = servidor.accept();
				// Muestra de informacion por pantalla y por archivo.
				String mensajeNuevaConexion = "IP " + socket.getInetAddress().getHostName();
				System.out.println(mensajeNuevaConexion);
				// Cada cliente tendra un thread dedicado
				Cliente conexionCliente = new Cliente(socket, session);

				// Arranco a ejecutar el thread
				conexionCliente.start();

			}
		} catch (IOException ex) {
			// Muestra de errores por pantalla y por archivo.
			String mensajeError = ex.getMessage();
			System.out.println(mensajeError);
		} finally {
			try {
				socket.close();
				servidor.close();
			} catch (IOException ex) {
				String mensajeError = ex.getMessage();
				System.out.println(mensajeError);
			}
		}
	}
}
