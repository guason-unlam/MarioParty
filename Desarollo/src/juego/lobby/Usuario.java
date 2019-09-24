package juego.lobby;

import java.util.ArrayList;
import java.util.Scanner;

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

	public Sala crearSala() {
		Scanner lector = new Scanner(System.in);
		String nombreSala;
		String password;
		int cantDeUsrMaximos;

		// Voy ingresando los parametros
		System.out.println("Ingrese el nombre de la sala: ");
		nombreSala = lector.nextLine();
		// Voy ingresando la pw, PUEDE SER VACIA
		System.out.println("Ingrese la contrase�a de la sala: ");
		password = lector.nextLine();

		do {
			// Por ahora salas entre 2 y 5
			System.out.println("Ingrese la cantidad maxima de usuarios: ");
			cantDeUsrMaximos = lector.nextInt();
		} while (cantDeUsrMaximos < 2 && cantDeUsrMaximos > 5);

		lector.close();
		// Creo la sala
		Sala sala = new Sala(nombreSala, password, cantDeUsrMaximos, this);
		// Conecto al usuario a la misma
		sala.conectarseALaSala(this);
		return sala;
	}

	public void salirDeSala() {
		this.sala = null;
	}

	public Jugador getJugador() {
		if (this.jugador != null) {
			return this.jugador;
		}

		if (this.sala != null && this.sala.getPartidaActual() != null
				&& this.sala.getPartidaActual().getUsuariosActivosEnSala() != null) {
			// TO DO: Revisar bien que no quede asi
			return this.sala.getPartidaActual().getUsuariosActivosEnSala().get(0).getJugador();
		}
		return null;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

}