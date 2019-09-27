package juego.tablero;

import java.util.Iterator;
import java.util.TreeSet;

import juego.misc.Dado;

public class MejorDeDiez extends MiniJuego {
	private String []nombreJugadores;
	private Dado dado;
	TreeSet<JugadorMinijuego> resumen = new TreeSet<JugadorMinijuego>();
	public MejorDeDiez(String nombre[]) {
		super.setDescripcion("Cada jugador lanza 10 veces 1 dado, el que saque la mayor suma gana.");
		super.setNombre("MejorDeDiez");
		this.nombreJugadores = nombre;
		dado = new Dado(6);
	}
	@Override
	public void iniciar() {
		muestraInstrucciones();
		JugadorMinijuego nuevoJugador;
		for(String nombre: nombreJugadores) {
			nuevoJugador = new JugadorMinijuego(nombre);
			System.out.println("Juega: " + nombre + ": ");
			nuevoJugador.setRes(jugar());
			resumen.add(nuevoJugador);
		}
		Iterator it = resumen.descendingIterator();
		while(it.hasNext()) {
			System.out.println(((JugadorMinijuego)it.next()).getNombre());
		}
	}
	
	
	public int jugar() {
		int res = 0, num;
		for(int i = 0; i < 10; i++) {
			num = dado.tirar();
			System.out.println("\tTiro nro: " + i + " salio un: " + num);
			res +=num;
		}
		System.out.println("\tTotal: " + res);
		return res;
	}
	
	private void muestraInstrucciones() {
		// TODO Auto-generated method stub
		System.out.println();
	}

}

class JugadorMinijuego implements Comparable{
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



	public void setRes(int r){
		this.res = r;
	}
	
	public int getRes() {
		return this.res;
	}

	@Override
	public int compareTo(Object jugadorMJ) {
		/*
		 * En caso de empate por puntos, desempata por nombre alfabeticamente
		 * */
			int difNom = this.getNombre().compareTo(((JugadorMinijuego)jugadorMJ).getNombre());
			int dif = this.getRes() - ((JugadorMinijuego)jugadorMJ).getRes();
		return ((dif>0)?1:((dif<0)?-1:((difNom > 0))?1:-1));
	}
	
}
