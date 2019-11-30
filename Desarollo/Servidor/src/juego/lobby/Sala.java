package juego.lobby;

import java.util.ArrayList;

import javax.json.Json;

import juego.Constantes;
import servidor.ConexionServidor;
import servidor.Servidor;

public class Sala {
	private String nombre;
	private String password;
	private boolean salaLlena = false;
	private int capacidadMaxima = 0;
	private int capacidadActual;
	private ArrayList<Usuario> usuariosActivos = new ArrayList<Usuario>();
	private Usuario usuarioCreador;
	private Partida partidaActual;
	// Necesito llevar un control de las partidas jugadas
	private ArrayList<Partida> partidas = new ArrayList<Partida>();

	public Sala(String nombreSala, String passwordSala, int capacidadMaxima, Usuario usuarioCreador) {
		this.nombre = nombreSala;
		this.password = passwordSala;
		this.capacidadActual = 0;
		this.capacidadMaxima = capacidadMaxima;
		this.usuarioCreador = usuarioCreador;
		this.partidaActual = null;
	}

	public Sala(String nombreSala, int capacidadMaxima, Usuario usuarioCreador) {
		this.nombre = nombreSala;
		this.password = "";
		this.capacidadActual = 0;
		this.capacidadMaxima = capacidadMaxima;
		this.usuarioCreador = usuarioCreador;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Usuario getUsuarioCreador() {
		return usuarioCreador;
	}

	public void setUsuarioCreador(Usuario usuarioCreador) {
		this.usuarioCreador = usuarioCreador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isSalaLlena() {
		return salaLlena;
	}

	public void setSalaLlena(boolean salaLlena) {
		this.salaLlena = salaLlena;
	}

	public ArrayList<Usuario> getJugadoresActivos() {
		return usuariosActivos;
	}

	public void setJugadoresActivos(ArrayList<Usuario> jugadoresActivos) {
		this.usuariosActivos = jugadoresActivos;
	}

	public int getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public void setCapacidadMaxima(int capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}

	public int getCapacidadActual() {
		return capacidadActual;
	}

	public void setCapacidadActual(int capacidadActual) {
		this.capacidadActual = capacidadActual;
	}

	public Usuario getJugadorCreador() {
		return usuarioCreador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuarioCreador == null) ? 0 : usuarioCreador.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		if (usuarioCreador == null) {
			if (other.usuarioCreador != null)
				return false;
		} else if (!usuarioCreador.equals(other.usuarioCreador))
			return false;
		return true;
	}

	public void setJugadorCreador(Usuario jugadorCreador) {
		this.usuarioCreador = jugadorCreador;
	}

	public boolean esAdmin(Usuario user) {
		return this.usuarioCreador.getUsername().equals(user.getUsername());
	}

	public boolean sacarUsuarioDeSala(Usuario usuario) {
		if (this.usuariosActivos.remove(usuario)) {
			this.capacidadActual--;
			usuario.setSala(null);
			return true;
		}
		if (this.salaLlena) {
			this.salaLlena = false;
		}

		return false;

	}

	public ArrayList<Usuario> getUsuariosActivos() {
		return usuariosActivos;
	}

	public boolean agregarUsuario(Usuario usuario) {
		boolean esPrimeraPartida = this.partidas.size() > 0;
		if (esPrimeraPartida && this.partidaActual.isPartidaEnCurso() == false
				&& this.capacidadActual < this.capacidadMaxima) { // En caso deq ue quiera meterme a una partida ya
																	// empezada
			this.usuariosActivos.add(usuario);
			this.capacidadActual++;
			return true;
		} else if (this.capacidadActual < this.capacidadMaxima) { // El flujo normal
			this.usuariosActivos.add(usuario);
			this.capacidadActual++;
			return true;
		}
		this.salaLlena = true;
		return false;
	}

	/*
	 * Para llevar un control de que estoy jugando
	 */
	public Partida getPartidaActual() {
		return partidaActual;
	}

	public void setPartidaActual(Partida partidaActual) {
		this.partidaActual = partidaActual;
	}

	/**
	 * Crea la partida y la comienza
	 * 
	 * @param cantidadDeRondasDePartida
	 * @param tipoJuego
	 * 
	 * @return Boolean
	 */
	public boolean crearPartida(Usuario creador, int cantidadBots, String condicionVictoria, String mapa,
			int cantidadTotalRondas) {
		if (this.partidaActual == null || this.partidaActual.isPartidaEnCurso() == false) {
			this.partidaActual = new Partida(this.usuariosActivos, condicionVictoria, mapa, cantidadTotalRondas);

			// Preparo a todos los usuarios
			for (Usuario userActivo : this.usuariosActivos) {
				for (ConexionServidor cs : Servidor.getServidoresConectados()) {
					if (cs.getUsuario().equals(userActivo) && !cs.getUsuario().equals(creador)) {
						cs.escribirSalida(Json.createObjectBuilder().add("type", Constantes.NOTICE_EMPEZA_JUEGO_CLIENTE)
								.add("tablero", this.partidaActual.getTablero().toJson()).build());
					}
				}
			}
			return true;
		}
		return false;
	}

	public boolean comenzarPartida() {
		if (this.partidaActual == null) {
			return false;
		}
		try {
			this.partidaActual.iniciarPartida();
		} catch (ExcepcionJugadoresInsuficientes e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.partidas.add(this.partidaActual);
		return true;
	}

	public void stopPartida() {
		this.partidaActual.frenarPartida();
	}
}
