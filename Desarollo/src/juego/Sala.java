package juego;

import java.util.ArrayList;

public class Sala {
	private String nombre;
	private boolean salaLlena = false;
	private int capacidadMaxima = 0;
	private int capacidadActual;
	private ArrayList<Jugador> jugadoresActivos = new ArrayList<Jugador>();
	private Jugador jugadorCreador;

	public Sala(String nombreSala, int capacidadMaxima, Jugador jugadorCreador) {
		this.nombre = nombreSala;
		this.capacidadMaxima = capacidadMaxima;
		this.jugadorCreador = jugadorCreador;
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

	public ArrayList<Jugador> getJugadoresActivos() {
		return jugadoresActivos;
	}

	public void setJugadoresActivos(ArrayList<Jugador> jugadoresActivos) {
		this.jugadoresActivos = jugadoresActivos;
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

	public Jugador getJugadorCreador() {
		return jugadorCreador;
	}

	public void setJugadorCreador(Jugador jugadorCreador) {
		this.jugadorCreador = jugadorCreador;
	}

	public boolean esAdmin(Jugador user) {
		return this.jugadorCreador.getUsername().equals(user.getUsername());
	}

	public boolean conectarseALaSala(Jugador jugador) {
		if (this.capacidadActual < this.capacidadMaxima) {
			this.jugadoresActivos.add(jugador);
			this.capacidadActual++;
			return true;
		}
		this.salaLlena = true;
		return false;
	}
	
	public boolean sacarJugadorDeSala(Jugador jugador) {
		if (this.jugadoresActivos.remove(jugador)) {
			this.capacidadActual--;
			return true;
		}
		if (this.salaLlena) {
			this.salaLlena = false;
		}

		return false;

	}

}
