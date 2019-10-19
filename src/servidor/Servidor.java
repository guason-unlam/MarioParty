package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.hibernate.Session;

import hibernate.HibernateUtils;
import juego.Constantes;
import lobby.Sala;
import lobby.Usuario;

public class Servidor {
	private static Session sessionHibernate = HibernateUtils.getSessionFactory().openSession();

	private static ArrayList<Sala> salasActivas = new ArrayList<Sala>();
	private static ArrayList<Usuario> usuariosActivos = new ArrayList<Usuario>();
	private static ArrayList<Cliente> clientesConectados = new ArrayList<Cliente>();

	public static void main(String[] args) {
		ServerSocket servidor = null;
		Socket socket = null;

		try {
			// Se crea el serverSocket para empezar a escuchar a los clientes.
			servidor = new ServerSocket(Constantes.PUERTO, Constantes.MAXIMAS_CONEXIONES_SIMULTANEAS);
			System.out.println("Servidor corriendo en " + servidor.getInetAddress() + ":" + servidor.getLocalPort());
			// Queda corriendo
			while (true) {
				// Acepto las conexiones que llegan
				socket = servidor.accept();

				// Cada cliente tendra un thread dedicado
				Cliente conexionCliente = new Cliente(socket);

				// Arranco a ejecutar el thread
				conexionCliente.start();
				clientesConectados.add(conexionCliente);

				String mensajeNuevaConexion = "Cliente con IP " + socket.getInetAddress().getHostAddress();

				System.out.println(mensajeNuevaConexion);
			}
		} catch (IOException ex) {
			// Muestra de errores por pantalla y por archivo.
			String mensajeError = ex.getMessage();
			System.out.println(mensajeError);
		} finally {
			try {
				if (socket != null) {

					socket.close();
				}
				if (servidor != null) {
					servidor.close();
				}
			} catch (IOException ex) {
				String mensajeError = ex.getMessage();
				System.out.println(mensajeError);
			}
		}
	}

	public static Session getSessionHibernate() {
		return sessionHibernate;
	}

	public static ArrayList<Sala> getSalasActivas() {
		return salasActivas;
	}

	public static ArrayList<Usuario> getUsuariosActivos() {
		return usuariosActivos;
	}

	public static boolean agregarAUsuariosActivos(Usuario usuario) {
		return usuariosActivos.add(usuario);
	}

	public static boolean removerUsuarioActivo(final Usuario usuario) {
		return usuariosActivos.remove(usuario);
	}

	public static void desconectar(final Cliente cliente) {
		cliente.interrupt();
		clientesConectados.remove(cliente);
	}
}
