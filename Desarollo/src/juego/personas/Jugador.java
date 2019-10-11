package juego.personas;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import juego.item.Inventario;
import juego.item.Item;
import juego.lobby.Partida;
import juego.lobby.Usuario;
import juego.misc.Dado;
import juego.tablero.Tablero;
import juego.tablero.casillero.Casillero;

public class Jugador implements Comparable<Jugador> {
	// Necesito tener una referencia a la partida, es el objeto padre de todo
	private Partida partida;
	private String nombre;
	private int pesos;
	private Color color;
	private int dolares;
	private Inventario inventario;
	private Personaje personaje;
	private Casillero posicion;
	private Dado dado;

	public Jugador(Usuario usuario, Tablero tablero, Partida partida) {
		this.partida = partida;
		this.nombre = usuario.getUsername();
		this.pesos = 100;
		this.dolares = 0;
		Random rand = new Random(System.currentTimeMillis());
		this.color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
		this.dado = new Dado(6);
		this.inventario = new Inventario(10); // 10 items maximos
	}

	public int tirarDado() {
		return this.dado.tirar();
	}

	public int avanzarUnCasillero() {//este metodo avanza un casillero y devuelve la cantidad de caminos disponibles
		this.posicion = this.posicion.getSiguiente().get(0);
		return this.posicion.getSiguiente().size();
	}
	
	public int avanzarUnCasillero(int camino) {
		this.posicion = this.posicion.getSiguiente().get(camino);
		return this.posicion.getSiguiente().size();
	}

	public void darPesos(int cant) {
		this.pesos += cant;
	}

	public int quitarPesos(int cant) {
		if (this.pesos < cant) {
			int ret = this.pesos;
			this.pesos = 0;
			return ret;
		}
		this.pesos -= cant;
		return cant;
	}
	
	public void usarItem(Item item) {
		item.activarItem(item.elegirObjetivo());
		int posicionEnInventario=0;
		Iterator<Item> i = this.getInventario().getItems().iterator();
		while(i.hasNext() && !i.next().equals(item))
			posicionEnInventario++;
		this.getInventario().getItems().remove(posicionEnInventario);
		this.getInventario().setCantItems(this.getInventario().getCantItems() - 1);
	}
	
	public boolean comprarDolar() {
		if(!this.posicion.isTieneArbolito())
				return false;
		if (this.pesos < partida.getPrecioDolar()) {
			return false;
		}
		
		this.dolares++;
		this.pesos -= partida.getPrecioDolar();
		this.partida.cambioArbolito(this.posicion);
		this.posicion.setTieneArbolito(false);
		return true;
	}
	
	public int caminosDisponibles() {
		return this.posicion.getSiguiente().size();
	}

	/*
	 * SETTERS Y GETTERS
	 * 
	 */

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPesos() {
		return pesos;
	}

	public void setPesos(int pesos) {
		this.pesos = pesos;
	}

	public void setPuntaje(int pesos) {
		this.pesos = pesos;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return this.nombre + "            " + this.pesos;
	}

	public int getDolares() {
		return this.dolares;
	}

	public void setPuntosEnPartida(int dolares) {
		this.dolares = dolares;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public Personaje getPersonaje() {
		return personaje;
	}

	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}

	public Casillero getPosicion() {
		return posicion;
	}

	public void setPosicion(Casillero posicion) {
		this.posicion = posicion;
	}

	public Dado getDado() {
		return this.dado;
	}

	public Partida getPartida() {
		return this.partida;
	}

	public void setDado(Dado dado) {
		this.dado = dado;
	}

	@Override
	public int compareTo(Jugador o) {
		if (this.partida == o.partida && this.nombre == o.nombre) {
			return 0;
		}
		return 1;
	}

}
