package juego.tablero;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import juego.misc.Dado;
import juego.personas.Jugador;

public class MejorDeDiez extends MiniJuego {
	private ArrayList<Jugador> nombreJugadores;
	private Dado dado;
	private boolean enPartida;
	String resultados = ""; // Solo es para saber que estaba funcionando bien, ya que aca guardo los totales
							// de cada uno
	TreeSet<JugadorMinijuego> resumen = new TreeSet<JugadorMinijuego>();

	public MejorDeDiez(ArrayList<Jugador> jugadores) {
		super.setDescripcion("Cada jugador lanza 10 veces 1 dado," + " el que saque la mayor suma gana.");
		super.setNombre("MejorDeDiez");
		dado = new Dado(6);
		enPartida = false;
		this.nombreJugadores = jugadores;
	}

	@Override
	public void iniciar() {
		enPartida = true;
		muestraInstrucciones();
		JugadorMinijuego nuevoJugador;
		for (Jugador jugador : nombreJugadores) {
			nuevoJugador = new JugadorMinijuego(jugador.getNombre());
			resultados += String.format("%-15s", nuevoJugador.getNombre());
			nuevoJugador.setRes(jugar());
			resumen.add(nuevoJugador);
		}
		guardarResultados();
		enPartida = false;
	}

	private void guardarResultados() {

		int i = 0;
		String nombreRes = "";
		for (String nombre : getResumen()) {
			i++;
			nombreRes += i + ") " + nombre + "\n";
		}
		// Hay que escribirlo en algun archivo
		juego.Main.mostrar(resultados);

	}

	public int jugar() {
		int res = 0;
		for (int i = 0; i < 10; i++) {
			res += dado.tirar();
			;
		}
		resultados += "" + res + "\n";
		return res;
	}

	private void muestraInstrucciones() {
		// TODO Auto-generated method stub
		String instruciones = "El juego consta de varios jugadores, los cuales tirarán 10 veces "
				+ "un dado y se realizará la suma de todos los tiros.";
		juego.Main.mostrar("Instrucciones:\n" + instruciones);
		juego.Main.mostrar("El ganador será aquel que obtenga la mayor suma.");
	}

	public String[] getResumen() {
		int i = 0;
		String[] nombresOrdenados = new String[resumen.size()];
		Iterator<JugadorMinijuego> it = resumen.descendingIterator();
		while (it.hasNext()) {
			nombresOrdenados[i++] = (it.next()).getNombre();
		}
		return nombresOrdenados;
	}

	public ArrayList<Jugador> getNombreJugadores() {
		return nombreJugadores;
	}

	public void setNombreJugadores(ArrayList<Jugador> nombreJugadores) {
		this.nombreJugadores = nombreJugadores;
	}

	public String getResultados() {
		return resultados;
	}

}

class JugadorMinijuego implements Comparable<JugadorMinijuego> {
	private String nombre;
	private int res;

	public JugadorMinijuego(String nombre) {
		this.nombre = nombre;
		res = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setRes(int r) {
		this.res = r;
	}

	public int getRes() {
		return this.res;
	}

	@Override
	public int compareTo(JugadorMinijuego jugadorMJ) {
		/*
		 * En caso de empate por puntos, desempata por nombre alfabeticamente
		 */
		int difNom = this.getNombre().compareTo(((JugadorMinijuego) jugadorMJ).getNombre());
		int dif = this.getRes() - ((JugadorMinijuego) jugadorMJ).getRes();
		return ((dif > 0) ? 1 : ((dif < 0) ? -1 : ((difNom > 0)) ? 1 : -1));
	}

}
