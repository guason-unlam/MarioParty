package juego.lobby;

import java.util.ArrayList;

import juego.personas.Jugador;
import juego.tablero.MiniJuego;
/*
 * Esta clase igual se podría reemplazar por el loop en la clase partida y 
 * lanzar después del loop el minijuego, pero bueno, ya estaba 
 * implementada por algo supongo.
 * */
public class Ronda {
//	Falta la referencia al minijuego, pero como aun no esta creado no lo pongo, pero debería ser un
//	ArrayList de minijuegos, y elejis uno al azar que no haya salido
	private ArrayList<Jugador> jugadoresEnPartida;
	public Ronda(ArrayList<Jugador> jugadoresEnPartida) {
		this.jugadoresEnPartida = jugadoresEnPartida;
	}
	/*
	 * Devuelve un int para devolver distintos tipos de error se puede cambiar a void
	 * y lanzar excepciones
	 * */
	public int iniciar() {
		for(Jugador jug:jugadoresEnPartida) {
			jug.tirarDado();
			jug.etapaAccion();
		}
//		Lanzo minijuego cuando lo tenga
		return 0;
	}
	
}
