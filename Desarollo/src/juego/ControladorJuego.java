package juego;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import juego.lobby.Partida;
import juego.personas.Jugador;
import juego.ventana.VentanaJuego;

public class ControladorJuego {
	
	private Partida partida;
	private VentanaJuego ventana;
	
	private int numeroJugadorActual;
	private Jugador jugadorActual;
	
	private boolean avanzando;
	private int pasosRestantes;
	
	
	public ControladorJuego(Partida partida, VentanaJuego ventana) {
		this.partida = partida;
		this.ventana = ventana;
		numeroJugadorActual = 0;
		siguienteJugador();
		
		return;
	}
	public void avanzarJugador(int cant) {
		for(int i = cant; i>0; i--)
		{
			if(jugadorActual.caminosDisponibles()==1)
				jugadorActual.avanzarUnCasillero();
			else {
				String[] caminos = new String[jugadorActual.caminosDisponibles()]; 
				for(int j = 0; j< jugadorActual.caminosDisponibles();j++) {
					caminos[j] = "Camino "+ jugadorActual.getPosicion().getSiguiente().get(j).getId();
				}
				
				int camino =  JOptionPane.showOptionDialog(null, "Elegir camino", "Elija un camino",
						JOptionPane.WARNING_MESSAGE, 0, null, caminos, caminos[0]);
				jugadorActual.avanzarUnCasillero(camino);
				
			}
			if(jugadorActual.getPosicion().isTieneArbolito()) { // pregunto si quiere comprar dolar
				int respuesta = JOptionPane.showConfirmDialog(ventana.getContentPane(), "Desea comprar un dolar?", "Atención!",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (respuesta == JOptionPane.YES_OPTION) {
					jugadorActual.comprarDolar();
				}
			}
		}
		if(jugadorActual.getPosicion().isTieneRecompensa())
			jugadorActual.getPosicion().getRecompensa().darRecompensa(jugadorActual);
	}
	public void continuar() {
		if(!avanzando)
			siguienteJugador();
	}
	
	private void siguienteJugador() {
		numeroJugadorActual++;
		if(numeroJugadorActual > partida.getJugadoresEnPartida().size()) {
			numeroJugadorActual = 1;
		}
		jugadorActual = partida.getJugadoresEnPartida().get(numeroJugadorActual-1);
		ventana.getPanelJugador().setNombreJugador("Turno de "+jugadorActual.getNombre());
	}
	
	public Partida getPartida() {
		return partida;
	}
	public Jugador getJugadorActual() {
		return partida.getJugadoresEnPartida().get(numeroJugadorActual-1);
	}
	
	
}
