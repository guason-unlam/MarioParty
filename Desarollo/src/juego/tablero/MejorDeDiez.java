package juego.tablero;

import java.util.Iterator;
import java.util.TreeSet;

import juego.misc.Dado;

public class MejorDeDiez extends MiniJuego {
	private String []nombreJugadores;
	private Dado dado;
	private boolean enPartida;
	String resultados = ""; // Solo es para saber que estaba funcionando bien, ya que aca guardo los totales de cada uno
	TreeSet<JugadorMinijuego> resumen = new TreeSet<JugadorMinijuego>();
	public MejorDeDiez(String nombre[]) {
		super.setDescripcion("Cada jugador lanza 10 veces 1 dado,"
				+ " el que saque la mayor suma gana.");
		super.setNombre("MejorDeDiez");
		this.nombreJugadores = nombre;
		dado = new Dado(6);
		enPartida = false;
	}
	@Override
	public void iniciar() {
		muestraInstrucciones();
		JugadorMinijuego nuevoJugador;
		for(String nombre: nombreJugadores) {
			nuevoJugador = new JugadorMinijuego(nombre);
			resultados += nuevoJugador.getNombre() + "\t";
			nuevoJugador.setRes(jugar());
			resumen.add(nuevoJugador);
		}
		guardarResultados();
	}
	
	
	private void guardarResultados() {

		int i = 0;
		String nombreRes = "";
		for(String nombre: getResumen()) {
			i++;
			nombreRes += i + ") " + nombre + "\n";
		}
		//Hay que escribirlo en algun archivo
		System.out.println(resultados);
		
	}
	
	public int jugar() {
		int res = 0;
		for(int i = 0; i < 10; i++) {
			res +=dado.tirar();;
		}
		resultados += "" + res + "\n";
		return res;
	}
	
	private void muestraInstrucciones() {
		// TODO Auto-generated method stub
		String instruciones = "El juego consta de varios jugadores, los cuales tirarán 10 veces,"
				+ "un dado, al final la suma de todos los tiros, sera el resultado por jugador,"
				+ "a más puntuación, mejor.";
		System.out.println("Instrucciones:\n" +instruciones);
	}
	

	public String[] getResumen(){
		int i=0;
		String[]nombresOrdenados = new String[resumen.size()];
		Iterator it = resumen.descendingIterator();
		while(it.hasNext()) {
			nombresOrdenados[i++] = ((JugadorMinijuego)(it.next())).getNombre();
		}
		return nombresOrdenados;
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
