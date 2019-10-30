package juego;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import juego.item.Recompensa;
import juego.lobby.Partida;
import juego.personas.Jugador;
import juego.tablero.casillero.Casillero;
import juego.ventana.PanelConsola;
import juego.ventana.VentanaJuego;

public class ControladorJuego {
	
	private Partida partida;
	private VentanaJuego ventana;
	private int numeroRonda;
	
	private int numeroJugadorActual;
	private Jugador jugadorActual;
	
	
	
	public ControladorJuego(Partida partida, VentanaJuego ventana) {
		this.partida = partida;
		this.ventana = ventana;
		numeroJugadorActual = 0;
		numeroRonda = 1;
		siguienteJugador();
		partida.cambioArbolito(null);
		
		return;
	}
	
	public void avanzarJugador(int cant) {
		PanelConsola consola = ventana.getPanelConsola();
		consola.agregarTexto(jugadorActual.getNombre()+" lanzo el dado y saco "+cant);
		for(int i = cant; i>0; i--)
		{
			if(jugadorActual.caminosDisponibles()==1)
				jugadorActual.avanzarUnCasillero();
			else {
				String[] caminos = new String[jugadorActual.caminosDisponibles()]; 
				for(int j = 0; j< jugadorActual.caminosDisponibles();j++) {
					caminos[j] = "Camino "+ jugadorActual.getPosicion().getSiguiente().get(j).getId();
				}
				int camino;
				do
				camino =  JOptionPane.showOptionDialog(null, "Elegir camino", "Elija un camino",
						JOptionPane.WARNING_MESSAGE, 0, null, caminos, caminos[0]);
				while(camino == JOptionPane.CLOSED_OPTION);
				jugadorActual.avanzarUnCasillero(camino);
				
			}
			if(jugadorActual.getPosicion().isTieneArbolito()) { // pregunto si quiere comprar dolar
				int respuesta = JOptionPane.showConfirmDialog(ventana.getContentPane(), "Desea comprar un dolar?", "Atenci�n!",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (respuesta == JOptionPane.YES_OPTION) {
					if(jugadorActual.comprarDolar()) 
						consola.agregarTexto(jugadorActual.getNombre()+" ha comprado un dolar!");
				}
			}
		}
		if(jugadorActual.getPosicion().isTieneRecompensa()) {
			Recompensa recompensa = jugadorActual.getPosicion().getRecompensa();
			recompensa.darRecompensa(jugadorActual);
			consola.agregarTexto(jugadorActual.getNombre()+" ha obtenido "+ recompensa.getNombre());
			jugadorActual.getPosicion().setRecompensa(null);
			jugadorActual.getPosicion().setTieneRecompensa(false);
		}
	}
	
	public void continuar() {
		siguienteJugador();
		ventana.repaint();
	}
	
	private void siguienteJugador() {
		numeroJugadorActual++;
		if(numeroJugadorActual > partida.getJugadoresEnPartida().size()) {//aca termino la ronda, se lanzaria el minijuego
			numeroJugadorActual = 1;
		}
		jugadorActual = partida.getJugadoresEnPartida().get(numeroJugadorActual-1);
		ventana.getPanelJugador().setNombreJugador("Turno de "+jugadorActual.getNombre());
		ventana.getPanelConsola().agregarTexto("Comienza turno de "+jugadorActual.getNombre());
	}
	
	public void usarItem() {
		
	}
	
	public Partida getPartida() {
		return partida;
	}
	public Jugador getJugadorActual() {
		return partida.getJugadoresEnPartida().get(numeroJugadorActual-1);
	}
	
	
}
