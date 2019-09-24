package juego.administracion;

import java.util.ArrayList;

import juego.Jugador;

public class Usuario {
	private int id;
	private String username;
	private String password;
	private ArrayList<Partida> partidasJugadas;
	private int puntaje;
	private Sala sala;

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

	public Usuario(String username, String password) {
		this.username = username;
		this.password = password;
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

	public Sala crearSala(String nombreSala, String password, int cantDeUsrMaximos) {
		Sala sala = new Sala(nombreSala, cantDeUsrMaximos, this);
		sala.conectarseALaSala(this);
		return sala;
	}

	public void salirDeSala() {
		this.sala = null;
	}

	public void setJugador(Jugador jugador) {
		// TODO Auto-generated method stub
		
	}

}
