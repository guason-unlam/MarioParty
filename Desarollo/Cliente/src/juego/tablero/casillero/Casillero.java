package juego.tablero.casillero;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import juego.item.ItemRobar;
import juego.item.ModificadorDado;
import juego.item.Pesos;
import juego.item.Recompensa;
import juego.personas.Jugador;

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

	// Para saber si la estrella esta en este casillero
	private boolean tieneArbolito;
	private boolean tieneRecompensa;

	// Para el movimiento
	private ArrayList<Casillero> siguientes;
	private ArrayList<Casillero> anteriores;
	boolean caminoIzquierda, caminoDerecha, caminoArriba, caminoAbajo;
	// Personajes actualmente en el casillero
	private Set<Jugador> jugadores = new TreeSet<Jugador>();

	private Recompensa recompensa;
	/*
	 * Permite agregar un personaje al actual casillero
	 * 
	 * @param Personaje
	 * 
	 * @return void
	 */

	public Casillero(int id) {
		this.id = id;
		tieneArbolito = false;
		tieneRecompensa = false;
		primeraVez = true;
		this.color = Color.GREEN;
		if (id != 0) {// el 0 es la posicion inicial, no va a tener recompensa
			ponerRecompensa();
		}
	}

	private void ponerRecompensa() {
		double ran = Math.random();
		if (ran > 0.5) { // 50% de prob de que el casillero no tenga recompensa
			this.color = Color.YELLOW;
			tieneRecompensa = true;
			if (ran > 0.85) {// 15% de prob de q la recompensa sea un item
				if (ran > 0.9)
					recompensa = new ItemRobar(); // 10% de que sea un Robar
				else
					recompensa = new ModificadorDado(); // 5% de que sea modificadorDado
			} else
				recompensa = new Pesos(50); // 35% de prob de que la recompensa sean 50p, de momento hardcodeado
		}
	}

	public void removerJugador(Jugador j) {
		this.jugadores.remove(j);
	}

	public void agregarJugador(Jugador j) {
		this.jugadores.add(j);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Recompensa getRecompensa() {
		return this.recompensa;
	}

	public void setRecompensa(Recompensa recompensa) {
		this.recompensa = recompensa;
	}

	public boolean isTieneArbolito() {
		return tieneArbolito;
	}

	public void setTieneArbolito(boolean tieneArbolito) {
		if (!tieneArbolito) {
			if (tieneRecompensa)
				this.color = Color.yellow;
			else
				this.color = Color.green;
		} else
			this.color = Color.red;
		this.tieneArbolito = tieneArbolito;
	}

	public boolean isTieneRecompensa() {
		return tieneRecompensa;
	}

	public void setTieneRecompensa(boolean tieneRecompensa) {
		this.tieneRecompensa = tieneRecompensa;
	}

	public Set<Jugador> getJugadores() {
		return jugadores;
	}

	public boolean isCaminoIzquierda() {
		return caminoIzquierda;
	}

	public void setCaminoIzquierda(boolean caminoIzquierda) {
		this.caminoIzquierda = caminoIzquierda;
	}

	public boolean isCaminoDerecha() {
		return caminoDerecha;
	}

	public void setCaminoDerecha(boolean caminoDerecha) {
		this.caminoDerecha = caminoDerecha;
	}

	public boolean isCaminoArriba() {
		return caminoArriba;
	}

	public void setCaminoArriba(boolean caminoArriba) {
		this.caminoArriba = caminoArriba;
	}

	public boolean isCaminoAbajo() {
		return caminoAbajo;
	}

	public void setCaminoAbajo(boolean caminoAbajo) {
		this.caminoAbajo = caminoAbajo;
	}

}
