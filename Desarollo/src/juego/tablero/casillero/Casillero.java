package juego.tablero.casillero;

import java.awt.Color;
import java.util.ArrayList;

import juego.item.Item;
import juego.item.ItemBifurcacion;
import juego.personas.Jugador;
import juego.personas.Personaje;

public class Casillero {
	// Id interno
	private int id;
	// Color del casillero
	private Color color;
	// Posicion fisica en el tablero
	private int posicionX;
	private int posicionY;
	// En caso de ser verdadero, le da las recompensas
	private boolean primeraVez;

	// Para el movimiento
	private ArrayList<Casillero> siguientes;
	private ArrayList<Casillero> anteriores;
	// Personajes actualmente en el casillero
	private ArrayList<Jugador> jugadores;
	// El casillero puede, o no, tener un item
	private Item item;
	private Bifurcacion bifurcacion;

	// Constructor
	public Casillero() {
		// Significa que tengo una bifurcacion, entonces le asigno el tipo especial
		if (this.siguientes != null && this.siguientes.size() > 1) {
			this.setItem(new ItemBifurcacion(this.siguientes));
		}
	}
	/*
	 * Permite agregar un personaje al actual casillero
	 * 
	 * @param Personaje
	 * 
	 * @return void
	 */

	public boolean agregarJugador(Jugador j) {
		this.jugadores.add(j);
		return true;
	}

	// Setters y getters default
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	public boolean isPrimeraVez() {
		return primeraVez;
	}

	public void setPrimeraVez(boolean primeraVez) {
		this.primeraVez = primeraVez;
	}

	public ArrayList<Casillero> getSiguiente() {
		return siguientes;
	}

	public void setSiguiente(ArrayList<Casillero> siguiente) {
		this.siguientes = siguiente;
	}

	public ArrayList<Casillero> getAnteriores() {
		return anteriores;
	}

	public void setAnterior(ArrayList<Casillero> anteriores) {
		this.anteriores = anteriores;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean esBifurcacion() {
		return this.bifurcacion != null;
	}

}
