package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import org.hibernate.Session;

import hibernate.HibernateUtils;
import juego.Constantes;
import juego.lobby.Sala;
import juego.lobby.Usuario;

public class Servidor {
	private static Session sessionHibernate = HibernateUtils.getSessionFactory().openSession();

	private static ArrayList<Sala> salasActivas = new ArrayList<Sala>();
	private static ArrayList<Usuario> usuariosActivos = new ArrayList<Usuario>();
	private static ArrayList<Cliente> clientesConectados = new ArrayList<Cliente>();
	private static ArrayList<ConexionServidor> servidoresConectados = new ArrayList<ConexionServidor>();

	public static void main(String[] args) {
		// El socket con el cual el cliente me envia datos
		ServerSocket ssClienteIn = null;
		// El socket con el cual el cliente recibe datos
		ServerSocket ssClienteOut = null;

		/// ESTOS DOS SE PIENSAN AL REVES, PUED SER CONFUSO
		// El socket donde mi servidor envia los datos al cliente
		ServerSocket ssServidorIn = null;
		// El socket donde le pega el cliente (lo externo)
		ServerSocket ssServidorOut = null;

		Socket servidorIn = null;
		Socket servidorOut = null;
		Socket clienteIn = null;
		Socket clienteOut = null;

		try {
			ssClienteIn = new ServerSocket(Constantes.PUERTO_SALIDA_CLIENTE, Constantes.MAXIMAS_CONEXIONES_SIMULTANEAS);

			ssClienteOut = new ServerSocket(Constantes.PUERTO_ENTRADA_CLIENTE,
					Constantes.MAXIMAS_CONEXIONES_SIMULTANEAS);

			ssServidorIn = new ServerSocket(Constantes.PUERTO_SALIDA_SERVIDOR,
					Constantes.MAXIMAS_CONEXIONES_SIMULTANEAS);

			ssServidorOut = new ServerSocket(Constantes.PUERTO_ENTRADA_SERVIDOR,
					Constantes.MAXIMAS_CONEXIONES_SIMULTANEAS);

			System.out.println(
					"Servidor corriendo en " + ssServidorOut.getInetAddress() + ":" + ssServidorOut.getLocalPort());
			// Queda corriendo
			while (true) {
				clienteIn = ssClienteIn.accept();
				clienteOut = ssClienteOut.accept();

				Cliente conexionCliente = new Cliente(clienteIn, clienteOut);

				// Arranco a ejecutar el thread
				conexionCliente.start();
				clientesConectados.add(conexionCliente);

				servidorIn = ssServidorIn.accept();
				servidorOut = ssServidorOut.accept();

				ConexionServidor conexionServidor = new ConexionServidor(servidorIn, servidorOut);

				// Arranco a ejecutar el thread
				conexionServidor.start();
				servidoresConectados.add(conexionServidor);

				String mensajeNuevaConexion = "[NUEVA CONEXION] Cliente con IP "
						+ clienteIn.getInetAddress().getHostAddress();

				System.out.println(mensajeNuevaConexion);
			}
		} catch (IOException ex) {
			// Muestra de errores por pantalla y por archivo.
			String mensajeError = ex.getMessage();
			System.out.println(mensajeError);
		} finally {
			try {
				// Cierro la conexion con el cliente
				if (ssClienteIn != null) {

					ssClienteIn.close();
				}
				if (clienteIn != null) {
					clienteIn.close();
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

	public static void desconectarServidor(final ConexionServidor conexionServidor) {
		conexionServidor.interrupt();
		servidoresConectados.remove(conexionServidor);
	}

	public static Sala getSalaPorNombre(String nombreSala) {
		for (Sala sala : salasActivas) {
			if (sala.getNombre().equals(nombreSala)) {
				return sala;
			}
		}
		return null;
	}

	public static JsonArray getIndexSalas() {
		JsonArrayBuilder datosDeSalas = Json.createArrayBuilder();
		for (Sala sala : salasActivas) {
			JsonObjectBuilder oSala = Json.createObjectBuilder();
			oSala.add("nombre", sala.getNombre()).add("capacidadActual", String.valueOf(sala.getCapacidadActual()))
					.add("capacidadMaxima", String.valueOf(sala.getCapacidadMaxima()))
					.add("admin", sala.getJugadorCreador().getUsername()).build();
			datosDeSalas.add(oSala);
		}
		return datosDeSalas.build();
	}

	public static boolean agregarASalasActivas(Sala sala) {
		return salasActivas.add(sala);
	}
	public static boolean eliminarSalaActiva(Sala sala) {
		return salasActivas.remove(sala);
	}
}
