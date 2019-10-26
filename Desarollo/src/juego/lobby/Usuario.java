package juego.lobby;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import juego.personas.Jugador;

public class Usuario {
	private int id;
	private String username;
	private String password;
	private ArrayList<Partida> partidasJugadas;
	private int puntaje;
	private Sala sala;
	private Jugador jugador;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public Usuario() {
	}

	public Usuario(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Usuario(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public Usuario(JsonObject jsonObject) {
		this.id = Integer.valueOf(jsonObject.get("id").toString());
		this.username = jsonObject.get("username").toString();
		this.password = jsonObject.get("password").toString();
	}

	public Usuario(int id) {
		this.id = id;
	}

	public ArrayList<Partida> getPartidasJugadas() {
		return partidasJugadas;
	}

	public void setPartidasJugadas(ArrayList<Partida> partidasJugadas) {
		this.partidasJugadas = partidasJugadas;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	//Constructor simple, sin contraseña
	public Sala crearSala(String nombreSala, int capacidadMaxima) {
		Sala sala = new Sala(nombreSala, capacidadMaxima, this);
		sala.agregarUsuario(this);
		return sala;
	}
	
	//Constructor con contraseña
	public Sala crearSala(String nombreSala, String password, int capacidadMaxima) {
		Sala sala = new Sala(nombreSala, password, capacidadMaxima, this);
		sala.agregarUsuario(this);
		return sala;
	}

	public boolean conectarseALaSala(Sala sala) {
		if (sala.getCapacidadActual() < sala.getCapacidadMaxima()) {
			this.setSala(sala);
			sala.agregarUsuario(this);
			sala.setCapacidadActual(sala.getCapacidadActual() + 1);
			return true;
		}
		sala.setSalaLlena(true);
		return false;
	}

	public void salirDeSala() {
		/*
		 * Si no lo saco, queda el usuario sin sala, pero figura como usuario activo, en
		 * al sala
		 */
		if (this.jugador != null) {
			this.sala.getJugadoresActivos().remove(this.jugador);
			this.setJugador(null);
		}
		this.sala.getUsuariosActivos().remove(this);
		this.sala = null;
	}

	public Jugador getJugador() {
		if (this.jugador != null) {
			return this.jugador;
		}

		if (this.sala != null && this.sala.getPartidaActual() != null
				&& this.sala.getPartidaActual().getUsuariosActivosEnSala() != null) {
			for (Usuario user : this.sala.getPartidaActual().getUsuariosActivosEnSala()) {
				if (user == this) {
					return user.getJugador();
				}
			}
		}
		return null;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
}
