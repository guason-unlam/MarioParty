package juego.lobby;

import juego.tablero.MejorDeDiez;
import juego.tablero.MiniJuego;

import java.util.ArrayList;

import juego.personas.Jugador;
/*
 * Esta clase igual se podr�a reemplazar por el loop en la clase partida y 
 * lanzar despu�s del loop el minijuego, pero bueno, ya estaba 
 * implementada por algo supongo.
 * */
public class Ronda {
//	Falta la referencia al minijuego, pero como aun no esta creado no lo pongo, pero deber�a ser un
//	ArrayList de minijuegos, y elejis uno al azar que no haya salido
	MiniJuego minijuego;
	private ArrayList<Jugador> jugadoresEnPartida;
	public Ronda(ArrayList<Jugador> jugadoresEnPartida) {
		this.jugadoresEnPartida = jugadoresEnPartida;
		String []nombres = new String[this.jugadoresEnPartida.size()];
		int i = 0;
		for(Jugador j: this.jugadoresEnPartida) {
			nombres[i] = j.getNombre();
		}
		minijuego = new MejorDeDiez();
		((MejorDeDiez)minijuego).setJugadores(nombres);
	}
	/*
	 * Devuelve un int para devolver distintos tipos de error se puede cambiar a void
	 * y lanzar excepciones
	 * */
	public int iniciar() {
		for(Jugador jug:jugadoresEnPartida) {
			Turno t = new Turno(jug);
			t.iniciarTurno();
			//jug.tirarDado();
		}
		((MejorDeDiez)minijuego).iniciar();
//		Lanzo minijuego cuando lo tenga
		return 0;
	}
	
}
