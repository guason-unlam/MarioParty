package juego.lobby;

import java.util.ArrayList;

import juego.personas.Jugador;
import juego.tablero.Tablero;

public class Partida {
	//Cambiar por el estado
	private boolean partidaEnCurso = false;
	private ArrayList<Ronda> rondasJugadas = new ArrayList<Ronda>();
	private ArrayList<Jugador> jugadoresEnPartida = new ArrayList<Jugador>();
	private Ronda rondaEnCurso;
	private int numeroRonda = 0;
	private ArrayList<Usuario> usuariosActivosEnSala;
	private Tablero tablero;
	private int tipoMapa;
	private Jugador ganador;
	//ESTO ES UNA CLASE NUEVA
	private int puntajeMaximo;
	private int cantidadDeRondasAJugar;
	private int precioDolar = 60;
	//Los dolares son las estrellas del mario party, el q mas dolares tiene gana la partida, de momento hardcodeo el precio aca
	

	public Partida(int id, ArrayList<Usuario> usuariosActivosEnSala, int cantidadTotalRondas) {
		this.usuariosActivosEnSala = usuariosActivosEnSala;
		for (Usuario usuario : usuariosActivosEnSala) {
			Jugador jugador;

			jugador = new Jugador(usuario, tablero, this);
			this.jugadoresEnPartida.add(jugador);

			usuario.setJugador(jugador);
		}
		this.cantidadDeRondasAJugar = cantidadTotalRondas;
		this.puntajeMaximo = 0;
		this.ganador = null;
	}

	public boolean isPartidaEnCurso() {
		return partidaEnCurso;
	}

	public void setPartidaEnCurso(boolean partidaEnCurso) {
		this.partidaEnCurso = partidaEnCurso;
	}

	public ArrayList<Ronda> getRondasJugadas() {
		return rondasJugadas;
	}

	public void setRondasJugadas(ArrayList<Ronda> rondasJugadas) {
		this.rondasJugadas = rondasJugadas;
	}

	public ArrayList<Jugador> getJugadoresEnPartida() {
		return jugadoresEnPartida;
	}

	public void setJugadoresEnPartida(ArrayList<Jugador> jugadoresEnPartida) {
		this.jugadoresEnPartida = jugadoresEnPartida;
	}

	public Ronda getRondaEnCurso() {
		return rondaEnCurso;
	}

	public void setRondaEnCurso(Ronda rondaEnCurso) {
		this.rondaEnCurso = rondaEnCurso;
	}

	public int getCantidadDeRondasAJugar() {
		return cantidadDeRondasAJugar;
	}

	public void setCantidadDeRondasAJugar(int cantidadDeRondasAJugar) {
		this.cantidadDeRondasAJugar = cantidadDeRondasAJugar;
	}

	public int getNumeroRonda() {
		return numeroRonda;
	}

	public void setNumeroRonda(int numeroRonda) {
		this.numeroRonda = numeroRonda;
	}

	public ArrayList<Usuario> getUsuariosActivosEnSala() {
		return usuariosActivosEnSala;
	}

	public void setUsuariosActivosEnSala(ArrayList<Usuario> usuariosActivosEnSala) {
		this.usuariosActivosEnSala = usuariosActivosEnSala;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public int getTipoMapa() {
		return tipoMapa;
	}

	public void setTipoMapa(int tipoMapa) {
		this.tipoMapa = tipoMapa;
	}

	public Jugador getGanadorPartida() {
		return ganador;
	}

	public void setGanadorPartida(Jugador ganadorPartida) {
		this.ganador = ganadorPartida;
	}

	public int getPuntajeMaximo() {
		return puntajeMaximo;
	}

	public void setPuntajeMaximo(int puntajeMaximo) {
		this.puntajeMaximo = puntajeMaximo;
	}

	public int getPrecioDolar() {
		return precioDolar;
	}

}
