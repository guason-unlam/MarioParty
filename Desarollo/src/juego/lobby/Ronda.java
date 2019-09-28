package juego.lobby;

import java.util.ArrayList;

import juego.personas.Jugador;
import juego.tablero.MejorDeDiez;
import juego.tablero.MiniJuego;
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
	private ArrayList<Jugador> jugadoresEnPartida;
	public Ronda(ArrayList<Jugador> jugadoresEnPartida) {
		this.jugadoresEnPartida = jugadoresEnPartida;
		String []nombres = new String[this.jugadoresEnPartida.size()];
		int i = 0;
		for(Jugador j: this.jugadoresEnPartida) {
			nombres[i] = j.getNombre();
			i++;
		}
		minijuego = new MejorDeDiez();
		((MejorDeDiez)minijuego).setJugadores(nombres);
	}
	/*
	 * Devuelve un int para devolver distintos tipos de error se puede cambiar a void
	 * y lanzar excepciones
	 * */
	public void iniciar() {
		for(Jugador jug:jugadoresEnPartida) {
			Turno t = new Turno(jug);
			t.iniciarTurno();
			//jug.tirarDado();
		}
		((MejorDeDiez)minijuego).iniciar();
//		Lanzo minijuego cuando lo tenga
	}
	
}
