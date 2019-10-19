package juego.lobby;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import juego.misc.ExcepcionArchivos;
import juego.personas.Jugador;
import juego.tablero.Tablero;
import juego.tablero.casillero.Casillero;

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
	private int puntajeMaximo;
	private int cantidadDeRondasAJugar;
	private int precioDolar = 60;
	// Los dolares son las estrellas del mario party, el q mas dolares tiene gana la
	// partida, de momento hardcodeo el precio aca

//	Para saber cuando termin� una Partida, por defecto, es por estrellas, a cinco.
	private CondicionVictoria condicionVictoria = new CondicionVictoria(TipoCondicionVictoria.RONDAS, 5);

	public Partida(ArrayList<Usuario> usuariosActivosEnSala, int cantidadTotalRondas) {
		this.usuariosActivosEnSala = usuariosActivosEnSala;
		try {
			this.tablero = new Tablero("../Mapas/tablero03.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ExcepcionArchivos e) {
			e.printStackTrace();
		}

		for (Usuario usuario : usuariosActivosEnSala) {
			Jugador jugador;

			jugador = new Jugador(usuario, tablero, this);
			// Lo seteo al primer casillero
			jugador.setPosicion(this.tablero.getCasilleros().get(0));
			this.jugadoresEnPartida.add(jugador);

			usuario.setJugador(jugador);
		}
		if (cantidadTotalRondas != 0) {
			condicionVictoria = new CondicionVictoria(TipoCondicionVictoria.RONDAS, cantidadTotalRondas);
		}
		this.puntajeMaximo = 0;
		this.ganador = null;
	}

	public int iniciarPartida() throws ExcepcionJugadoresInsuficientes {
		if (jugadoresEnPartida.size() < 2) {
			throw new ExcepcionJugadoresInsuficientes(0);
		}
		// Inicio
		this.partidaEnCurso = true;

		/*
		 * >> Falta validar aca, o en la sala, si se cumplen las condiciones para
		 * iniciar una partida >> Hay que definir como interpretar la condicion de
		 * victoria, y en caso de que sea como en el juego por estrellas, hay que ver si
		 * considerarlas como items o de alguna otra forma.
		 */
		do {
			numeroRonda++;
			rondaEnCurso = new Ronda(jugadoresEnPartida);
			System.out.println("RONDA " + numeroRonda);
			System.out.println("===========");
			rondaEnCurso.iniciar();
			rondasJugadas.add(rondaEnCurso);
			evaluarEstadoPartida();
			System.out.println("");
		} while (this.ganador == null && this.partidaEnCurso == true);
		System.out.println("Con un total de $" + this.ganador.getPesos() + " el ganador es .... "
				+ this.ganador.getNombre() + "!!!");
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

	public int getPrecioDolar() {
		return precioDolar;
	}

	public void calcularGanadorPartidaPorRondas() {
		for (Jugador jug : this.jugadoresEnPartida) {
			if (jug.getPesos() > this.puntajeMaximo) {
				this.ganador = jug;
				this.puntajeMaximo = jug.getPesos();
			}
		}
	}

	/*
	 * Depende de como decidamos tratar las estrellas
	 */
	public Jugador calcularGanadorPorEstrellas() {
		for (Jugador jug : jugadoresEnPartida) {
			if ((jug.getDolares() == this.condicionVictoria.getCantidad())) {
				/*
				 * Si a�n nadie cumple condicionVictoria ganador es jug Si alguien ya cumple
				 * la condici�nVictoria desempato por cantidad de pesos que tiene cada uno.
				 */
				ganador = ((ganador == null) ? jug : ((jug.getPesos() > ganador.getPesos()) ? jug : ganador));
			}
		}
		return ganador;
	}

	public CondicionVictoria getCondicionVictoria() {
		return condicionVictoria;
	}

	public void setCondicionVictoria(CondicionVictoria condicionVictoria) {
		this.condicionVictoria = condicionVictoria;
	}

	public void evaluarEstadoPartida() {
		if (condicionVictoria.getTipo() == TipoCondicionVictoria.RONDAS) {
			if (this.numeroRonda == condicionVictoria.getCantidad()) {
				calcularGanadorPartidaPorRondas();
				this.partidaEnCurso = false;
			}
		} else if (condicionVictoria.getTipo() == TipoCondicionVictoria.ESTRELLAS) {
			this.partidaEnCurso = (calcularGanadorPorEstrellas() == null);
		}
	}

	/*
	 * Saco el arbolito del casillero en que estaba Elijo uno nuevo, distinto del
	 * anterior y pongo el arbolito ah�
	 */
	public void cambioArbolito(Casillero pos) {
		if (pos != null) {
			pos.setTieneArbolito(false);
		}
		int nuevaPos;
		do {
			nuevaPos = (int) (Math.random() * this.tablero.getCasilleros().size()) + 1;
		} while (pos.getId() == nuevaPos);

		this.tablero.getCasilleros().get(nuevaPos).setTieneArbolito(true);
	}

}
