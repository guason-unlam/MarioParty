package juego.lobby;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.google.gson.JsonObject;

import juego.personas.Jugador;

@Table(name = "USER", uniqueConstraints = { @UniqueConstraint(columnNames = "ID") })
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;
	private String username;
	private String password;
	// ACA HAGO EL N A N
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "partida", joinColumns = { @JoinColumn(referencedColumnName = "ID") }, inverseJoinColumns = {
			@JoinColumn(referencedColumnName = "ID") })
	private ArrayList<Partida> partidasJugadas;
	private int puntaje;
	private Sala sala;
	private Jugador jugador;
	private boolean estaJugando = false;

	public boolean isEstaJugando() {
		return estaJugando;
	}

	public void setEstaJugando(boolean estaJugando) {
		this.estaJugando = estaJugando;
	}

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

	// Constructor simple, sin contraseña
	public Sala crearSala(String nombreSala, int capacidadMaxima) {
		Sala sala = new Sala(nombreSala, capacidadMaxima, this);
		sala.agregarUsuario(this);
		return sala;
	}

	// Constructor con contraseña
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (estaJugando ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((sala == null) ? 0 : sala.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Usuario other = (Usuario) obj;
		if (estaJugando != other.estaJugando)
			return false;
		if (id != other.id)
			return false;
		if (sala == null) {
			if (other.sala != null)
				return false;
		} else if (!sala.equals(other.sala))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
}
