package juego;

import javax.swing.JOptionPane;

import juego.item.Recompensa;
import juego.lobby.Partida;
import juego.personas.Jugador;
import juego.ventana.PanelConsola;

public class ControladorJuego {

	private Partida partida;
	private int numeroRonda;

	private int numeroJugadorActual;
	private Jugador jugadorActual;

	public ControladorJuego(Partida partida) {
		this.partida = partida;
		numeroJugadorActual = 0;
		numeroRonda = 1;
		siguienteJugador();
		partida.cambioArbolito(null);

		return;
	}

	public void avanzarJugador(int cant) {
		for (int i = cant; i > 0; i--) {
			
		}
		jugadorActual.getPosicion().setTieneRecompensa(false);
		
	}

	public void continuar() {
		siguienteJugador();
	}

	private void siguienteJugador() {
		numeroJugadorActual++;
		if (numeroJugadorActual > partida.getJugadoresEnPartida().size()) {// aca termino la ronda, se lanzaria el
																			// minijuego
			numeroJugadorActual = 1;
		}
		jugadorActual = partida.getJugadoresEnPartida().get(numeroJugadorActual - 1);
	}

	public void usarItem() {

	}

	public Partida getPartida() {
		return partida;
	}

	public Jugador getJugadorActual() {
		return partida.getJugadoresEnPartida().get(numeroJugadorActual - 1);
	}

}
