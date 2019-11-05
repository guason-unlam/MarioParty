package juego.lobby;

import java.util.ArrayList;
import java.util.List;

import juego.personas.Jugador;
import juego.tablero.MiniJuego;
import juego.ventana.VentanaJuego;

/*
 * Esta clase igual se podria reemplazar por el loop en la clase partida y 
 * lanzar despues del loop el minijuego, pero bueno, ya estaba 
 * implementada por algo supongo.
 * */
public class Ronda {
//	Falta la referencia al minijuego, pero como aun no esta creado no lo pongo, pero deber�a ser un
//	ArrayList de minijuegos, y elejis uno al azar que no haya salido
	private int id;
	private MiniJuego minijuego;
	private List<Jugador> jugadoresEnPartida;
	private VentanaJuego ventanaJuego;
	private int jugadoresRestantes;

	public Ronda(ArrayList<Jugador> jugadoresEnPartida) {

		this.jugadoresEnPartida = jugadoresEnPartida;
		this.jugadoresRestantes = jugadoresEnPartida.size();
//		minijuego = new MejorDeDiez(this.jugadoresEnPartida);
	}

	/*
	 * Devuelve un int para devolver distintos tipos de error se puede cambiar a
	 * void y lanzar excepciones
	 */
	public void iniciar(boolean primeraRonda) {
//		jugadoresRestantes = jugadoresEnPartida.size();
//		if(primeraRonda) {//al ser la primera ronda, los jugadores tiran el dado para decidir el orden de los turnos
//			for (Jugador jugador : jugadoresEnPartida) {
//				JPanel panel = new JPanel();
//				JLabel lblNombre = new JLabel(jugador.getNombre());
//				panel.setBounds(0, 400, 300, 100);
//				panel.setBackground(Color.BLACK);
//				lblNombre.setBounds(10, 10, 100, 50);
//				lblNombre.setText("JAMON");
//				lblNombre.setVisible(true);
//				panel.setVisible(true);
//				panel.add(lblNombre);
//				ventanaJuego.getPanelJuego().add(panel);
//			}
//		}
//		for (Jugador jug : jugadoresEnPartida) {
//			Turno t = new Turno(jug);
//			t.iniciarTurno();
//			System.out.println("");
//			// jug.tirarDado();
//		}
//		((MejorDeDiez) minijuego).iniciar();
//		Lanzo minijuego cuando lo tenga
	}

	public void setVentanaJuego(VentanaJuego ventanaJuego) {
		this.ventanaJuego = ventanaJuego;
	}
//	public class decidirOrdenTurnos{
//		SortedList<resultadoLanzamiento> orden;
//		
//		public void agregarResultado(resultadoLanzamiento resultado) {
////			this.orden.add(resultado);
////			resultadosDados.values().
//		}
//	}

//	public class resultadoLanzamiento implements Comparable<resultadoLanzamiento>{
//		Jugador jugador;
//		int resultado;
//		
//		public int getResultado() { return this.resultado;}
//
//		@Override
//		public int compareTo(resultadoLanzamiento resultado) {
//			return this.resultado - resultado.getResultado();
//		}
//		
//		
//	}
//	public void decidirOrdenTurnos() {
//		ventanaJuego.pedirAccion(jugadoresEnPartida.get(jugadoresRestantes-1));
//	}

}
