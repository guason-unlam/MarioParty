package juego.personas;

import java.awt.Color;
import java.util.Random;

import juego.item.Inventario;
import juego.item.Item;
import juego.lobby.Usuario;
import juego.lobby.Partida;

import juego.misc.Dado;
import juego.tablero.Tablero;
import juego.tablero.casillero.Casillero;

public class Jugador {
	// Necesito tener una referencia a la partida, es el objeto padre de todo
	private Partida partida;
	private String nombre;
	private int puntaje;
	private Color color;
	private int puntosEnPartida;
	private Inventario inventario;
	private Personaje personaje;
	private Casillero posicion;
	private Dado dado;

	public Jugador(Usuario usuario, Tablero tablero) {
		this.nombre = usuario.getUsername();
		this.puntaje = 0;
		this.puntosEnPartida = 0;
		// Mi seed
		Random rand = new Random(System.currentTimeMillis());
		this.color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
		dado = new Dado(6);
	}

	public int tirarDado() {
		// Recupero mi referencia a Tablero
		/*
		 * Creo que para tirar el dado y avanzar como no tiene fin el mapa 
		 * no hace falta la referencia a la posicion
		 * porque no me puedo salir del mapa, ya que este no tiene bordes
		 * así que se puede sacar
		int casillerosRestantes = this.partida.getTablero().casillerosRestantes(this.posicion);
		 */
		return avanzar(dado.tirar());
	}

	private int avanzar(int d) {
		for (int i = 0; i < d; i++) {
			avanzarUnCasillero();
			// Si llego a una bifurcacion, tengo que ver que hago
			// IMPORTANTE: que no sea el ultimo casillero!
			if (i < d - 1 && this.posicion.getItem() instanceof Item) {
				this.posicion.getItem().activarItem(this);
			}
		}

		// Una vez que "termine", ejecuto el item
		if (this.posicion.getItem() instanceof Item)
			this.posicion.getItem().activarItem(this);

		return 0;
	}

	private void avanzarUnCasillero() {
		// Aca modifico la posicion del chabon
		this.posicion = partida.getTablero().getCasilleros().get(this.posicion.getSiguiente().get(0).getId());
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

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return this.nombre + "            " + this.puntaje;
	}

	public int getPuntosEnPartida() {
		return this.puntosEnPartida;
	}

	public void setPuntosEnPartida(int puntosEnPartida) {
		this.puntosEnPartida = puntosEnPartida;
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
		return dado;
	}

	public void setDado(Dado dado) {
		this.dado = dado;
	}
/*
 * Hay que ver como encaramos la etapa de accion para que un jugador use un item.
 * */
	public void etapaAccion() {
		// TODO Auto-generated method stub
		
	}
	
}
