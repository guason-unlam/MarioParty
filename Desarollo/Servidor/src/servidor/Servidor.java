package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.hibernate.Session;

import hibernate.HibernateUtils;
import juego.Constantes;
import juego.lobby.Ronda;
import juego.lobby.Sala;
import juego.lobby.Usuario;
import juego.personas.Jugador;
import juego.tablero.MejorDeDiez;

public class Servidor {
	private static Session sessionHibernate = HibernateUtils.getSessionFactory().openSession();

	private static ArrayList<Sala> salasActivas = new ArrayList<Sala>();
	private static ArrayList<Usuario> usuariosActivos = new ArrayList<Usuario>();
	private static ArrayList<Cliente> clientesConectados = new ArrayList<Cliente>();
	private static ArrayList<ConexionServidor> servidoresConectados = new ArrayList<ConexionServidor>();
	private static Map<String, Integer> minijuego = new HashMap<String, Integer>();

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
			ssClienteIn = new ServerSocket(Constantes.PUERTO_1, Constantes.MAXIMAS_CONEXIONES_SIMULTANEAS);

			ssClienteOut = new ServerSocket(Constantes.PUERTO_2, Constantes.MAXIMAS_CONEXIONES_SIMULTANEAS);

			ssServidorIn = new ServerSocket(Constantes.PUERTO_3, Constantes.MAXIMAS_CONEXIONES_SIMULTANEAS);

			ssServidorOut = new ServerSocket(Constantes.PUERTO_4, Constantes.MAXIMAS_CONEXIONES_SIMULTANEAS);

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
				getServidoresConectados().add(conexionServidor);

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
		getServidoresConectados().remove(conexionServidor);
	}

	public static Sala getSalaPorNombre(String nombreSala) {
		for (Sala sala : salasActivas) {
			if (sala.getNombre().equals(nombreSala)) {
				return sala;
			}
		}
		return null;
	}

	public static Usuario getUsuarioPorNombre(String nombreUsuario) {
		for (Usuario user : usuariosActivos) {
			if (user.getUsername().equals(nombreUsuario)) {
				return user;
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
					.add("admin", sala.getJugadorCreador().getUsername())
					.add("salaPrivada", sala.getPassword().equals("") ? "No" : "Si");
			datosDeSalas.add(oSala.build());
		}

		return datosDeSalas.build();
	}

	public static boolean agregarASalasActivas(Sala sala) {
		return salasActivas.add(sala);
	}

	public static boolean eliminarSalaActiva(Sala sala) {
		return salasActivas.remove(sala);
	}

	public static ArrayList<ConexionServidor> getServidoresConectados() {
		return servidoresConectados;
	}

	public static void setServidoresConectados(ArrayList<ConexionServidor> servidoresConectados) {
		Servidor.servidoresConectados = servidoresConectados;
	}

	public static void informarSalaTermina(Sala sala) {
		JsonObject paqueteSalaTerminada = Json.createObjectBuilder().add("type", Constantes.FIN_SALA).build();

		for (Usuario userSala : sala.getUsuariosActivos()) {
			for (ConexionServidor cliente : servidoresConectados) {
				if (cliente.getUsuario().equals(userSala)) {
					cliente.escribirSalida(paqueteSalaTerminada);
				}
			}
		}
	}

	public static boolean actualizarJuego(final Ronda ronda) {
		List<Jugador> jugadores = ronda.getJugadoresEnPartida();
		String entrada = ronda.toJson().toString();
		Message message = new Message(Constantes.REQUEST_UPDATE_MAPA, entrada);
		boolean enviar = false;
		for (Cliente conexionCliente : clientesConectados) {
			enviar = false;
			Usuario usuario = conexionCliente.getUsuario();
			if (usuario != null && conexionCliente.getSalida() != null && jugadores != null) {
				for (Jugador jugadorMapa : jugadores) {
					Jugador jugador = usuario.getJugador();
					if (jugador != null && jugador.equals(jugadorMapa)) {
						enviar = true;
						break;
					}
				}

				if (enviar) {
					try {
						conexionCliente.getSalida().flush();
						String salida = new String().concat(message.toJson());
						conexionCliente.getSalida().writeUTF(salida);
					} catch (IOException e) {
						System.out.println("Error al actualizar el mapa ");
						return false;
					}
				}

			}

		}
		return true;
	}

	public static JsonArray informarTiradaDados(Sala sala) {
		JsonArrayBuilder datosMinijuego = Json.createArrayBuilder();

		for (Sala salaActiva : salasActivas) {
			if (salaActiva.equals(sala)) {
				JsonObjectBuilder oSala = Json.createObjectBuilder();

				// Por cada uno ,le agrego su puntaje
				for (String cliente : minijuego.keySet()) {
					oSala.add("nombre", cliente).add("puntos", minijuego.get(cliente));
					datosMinijuego.add(oSala.build());

				}

			}
		}

		return datosMinijuego.build();
	}

	public static void tirarDado(Sala sala, Usuario user) {
		int valor = Integer.valueOf(MejorDeDiez.jugar());
		String nombreUser = user.getUsername();
		System.out.println("[TIRADA DE DADOS]" + nombreUser + " - " + valor);
		minijuego.put(nombreUser, valor);
	}

	public static int cantidadParticipantesDado(Sala sala) {
		return minijuego.keySet().size();
	}

	public static void limpiarMinijuego(Sala sala) {
		minijuego.clear();
	}

	public static Map<String, Integer> ordenarGanadores() {
		return sortByValue(minijuego, false);
	}

	public static JsonArray informarGanadores(Sala sala) {
		JsonArrayBuilder datosMinijuego = Json.createArrayBuilder();
		Map<String, Integer> mapOrdenado = ordenarGanadores();
		int i = 0;
		for (Sala salaActiva : salasActivas) {
			if (salaActiva.equals(sala)) {
				JsonObjectBuilder oSala = Json.createObjectBuilder();

				// Por cada uno ,le agrego su puntaje
				for (String cliente : mapOrdenado.keySet()) {

					if (i == 3)
						break;

					System.out.println(cliente + " - " + mapOrdenado.get(cliente));
					oSala.add("nombre", cliente).add("puntos", mapOrdenado.get(cliente));
					i++;

					datosMinijuego.add(oSala.build());

				}

			}
		}
		return datosMinijuego.build();
	}

	private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap, final boolean order) {
		List<Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

		// Sorting the list based on values
		list.sort((o1, o2) -> order
				? o1.getValue().compareTo(o2.getValue()) == 0 ? o1.getKey().compareTo(o2.getKey())
						: o1.getValue().compareTo(o2.getValue())
				: o2.getValue().compareTo(o1.getValue()) == 0 ? o2.getKey().compareTo(o1.getKey())
						: o2.getValue().compareTo(o1.getValue()));
		return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));

	}
}
