package juego.lobby;

import java.util.ArrayList;

import juego.personas.Jugador;
import juego.tablero.Tablero;

public class Partida {
	// Cambiar por el estado
	private boolean partidaEnCurso = false;
	private ArrayList<Ronda> rondasJugadas = new ArrayList<Ronda>();
	private ArrayList<Jugador> jugadoresEnPartida = new ArrayList<Jugador>();
	private Ronda rondaEnCurso;
	private int numeroRonda = 0;
	private ArrayList<Usuario> usuariosActivosEnSala;
	private Tablero tablero;
	private int tipoMapa;
	private Jugador ganador;
	// ESTO ES UNA CLASE NUEVA
	private int puntajeMaximo;
	private int cantidadDeRondasAJugar;
//	Para saber cuando terminó una Partida, por defecto, es por estrellas, a cinco.
	private CondicionVictoria condicionVictoria = new CondicionVictoria(TipoCondicionVictoria.RONDAS, 5);

	public Partida(int id, ArrayList<Usuario> usuariosActivosEnSala, int cantidadTotalRondas) {
		this.usuariosActivosEnSala = usuariosActivosEnSala;
		for (Usuario usuario : usuariosActivosEnSala) {
			Jugador jugador;

			jugador = new Jugador(usuario, tablero);
			this.jugadoresEnPartida.add(jugador);

			usuario.setJugador(jugador);
		}
		this.cantidadDeRondasAJugar = cantidadTotalRondas;
		this.puntajeMaximo = 0;
		this.ganador = null;
	}

public int iniciarPartida() {
		/*
		 * >> Falta validar aca, o en la sala, si se cumplen las condiciones para iniciar una partida
		 * >> Hay que definir como interpretar la condicion de victoria, y en caso de que sea como en el
		 *    juego por estrellas, hay que ver si considerarlas como items o de alguna otra forma. 
		 */
		while(this.ganador == null) {
			numeroRonda++;
			rondaEnCurso = new Ronda(jugadoresEnPartida);
			rondaEnCurso.iniciar();
			rondasJugadas.add(rondaEnCurso);
			evaluarEstadoPartida();
		}
		return 0;
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

	public void calcularGanadorPartidaPorRondas() {
		for (Jugador jug : this.jugadoresEnPartida) {
			if (jug.getPuntosEnPartida() > this.puntajeMaximo) {
				this.ganador = jug;
				this.puntajeMaximo = jug.getPuntosEnPartida();
			}
		}
	}
	/*
	 * Depende de como decidamos tratar las estrellas
	 * */
	public boolean calcularGanadorPorEstrellas() {
		return false;
	}

	public CondicionVictoria getCondicionVictoria() {
		return condicionVictoria;
	}

	public void setCondicionVictoria(CondicionVictoria condicionVictoria) {
		this.condicionVictoria = condicionVictoria;
	}
	
	public void evaluarEstadoPartida() {
		
		if(condicionVictoria.getTipo() == TipoCondicionVictoria.RONDAS) {
			if(this.numeroRonda == condicionVictoria.getCantidad()) {
				calcularGanadorPartidaPorRondas();
				this.partidaEnCurso = false;
			}
		}else if(condicionVictoria.getTipo() == TipoCondicionVictoria.ESTRELLAS) {
			this.partidaEnCurso = calcularGanadorPorEstrellas();
		}
	}
}
		
