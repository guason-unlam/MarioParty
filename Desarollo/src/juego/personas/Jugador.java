package juego.personas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import graphics.Game;
import graphics.GameObject;
import graphics.ObjectId;
import graphics.Texture;
import juego.item.Inventario;
import juego.item.Item;
import juego.lobby.Partida;
import juego.lobby.Usuario;
import juego.misc.Dado;
import juego.tablero.Tablero;
import juego.tablero.casillero.Casillero;

public class Jugador extends GameObject implements Comparable<Jugador> {
	// Necesito tener una referencia a la partida, es el objeto padre de todo
	Partida partida;
	private String nombre;
	private int pesos;
	private Color color;
	private int dolares;
	private Inventario inventario;
	private Casillero posicion;
	private int personaje;
	private Dado dado;
	private float width = 32, height = 32;
	Texture tex = Game.getInstance();
	/* Mas cosas para tratar de mover a mario */
	private Queue<Casillero> movimientos = new LinkedList<Casillero>();
	public int xObjetivo = 0;
	public int yObjetivo = 0;

	public Jugador(/* Usuario usuario, */Casillero posicionInicial, int personaje, ObjectId id) {
//		this.nombre = usuario.getUsername();
		super(posicionInicial.getPosicionX(), posicionInicial.getPosicionY(), id);
		this.pesos = 100;
		this.dolares = 0;
		Random rand = new Random(System.currentTimeMillis());
		this.color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
		this.dado = new Dado(6);
		this.inventario = new Inventario(10); // 10 items maximos
	}

	public void tick(LinkedList<GameObject> object) {

		if (movimientos.size() > 0) {
			System.out.println("Cola siguientes: ");

			for (Casillero cas : movimientos) {
				System.out.print(cas.getId() + " ");
			}
			System.out.println("");
		}

		if (movimientos.size() > 0) {
			Casillero sig = movimientos.peek();
			// System.out.println("[X]desde " + this.x + " hasta " + sig.getPosicionX());
			// System.out.println("[Y]desde " + this.y + " hasta " + sig.getPosicionY());

			// System.out.println("posY: " + this.y + " objetivoY: "+sig.getPosicionY()+"
			// hay camino: "+posicion.isCaminoAbajo());
//			System.out.println("posX: " + this.x + " objetivoX: "+sig.getPosicionX()+" hay camino: "+posicion.isCaminoDerecha());
			// MOVIMIENTO PARA LA DERECHA
			if (this.x < sig.getPosicionX() - 1 && Game.hayCamino(this.x + 1.5, this.y) == 1) {
				// System.out.println("derecha");
				this.x += 1.5f;
			} else {
				// System.out.println("Intento previo entre " + (this.x + 1.5) + " y " +
				// this.y);
				if (this.x > sig.getPosicionX() + 1 && Game.hayCamino(this.x - 1.5, this.y) == 1) {
					// System.out.println("izquierda");
					this.x -= 1.5f;
				} else {
					// System.out.println("Intento previo entre " + (this.x - 1.5) + " y " +
					// this.y);

					if (this.y < sig.getPosicionY() - 1 && Game.hayCamino(this.x, this.y + 1.5) == 1) {
						// System.out.println("abajo");
						this.y += 1.5f;
					} else {
						// System.out.println("Intento previo entre " + (this.x) + " y " + (this.y +
						// 1.5));

						if (this.y > sig.getPosicionY() + 1) {
							// System.out.println("arriba");
							this.y -= 1.5f;
						} else {
							// System.out.println("Intento previo entre " + (this.x) + " y " + (this.y -
							// 1.5));

							// System.out.println("[X FINAL]desde " + this.x + " hasta " +
							// sig.getPosicionX());
							// System.out.println("[Y FINAL]desde " + this.y + " hasta " +
							// sig.getPosicionY());
							if (sig.getAnteriores().get(0).getSiguiente().size() == 1)
								this.posicion = sig;
							sig.agregarJugador(this);
							movimientos.remove();
						}
					}
				}
			}
		}

	}
//	public void tick(LinkedList<GameObject> object) {
//		/* Lugar donde deber�a ir la l�gica para mover a mario */
//		if( enMovimientoX && x > xObjetivo-1 && x < xObjetivo+1) {
//			velX = 0;
//			enMovimientoX = false;
//		}
//		if(enMovimientoY && y > yObjetivo-1 && y < yObjetivo+1) {
//			velY = 0;
//			enMovimientoY = false;
//		}
//		x += velX;
//		y += velY;
//	}

	/* dibujador de personajes */
	public void render(Graphics g) {
		g.drawImage(tex.characters[personaje], (int) x, (int) y, null);
	}

	/* Despues sirve para el tema de las colisiones */
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y);
	}

	public int tirarDado() {
		return this.dado.tirar();
	}

	public int avanzarUnCasillero() {
		this.x = posicion.getPosicionX();
		this.y = posicion.getPosicionY();
		this.posicion.removerJugador(this);

		System.out.println("Estoy parado en" + posicion.getId());

		System.out.println("Estoy yendo al " + posicion.getSiguiente().get(0).getId());

		movimientos.add(posicion.getSiguiente().get(0));
		return this.posicion.getSiguiente().size();
	}
//	public int avanzarUnCasillero() {//este metodo avanza un casillero y devuelve la cantidad de caminos disponibles
//		this.posicion.removerJugador(this);
//		int x = posicion.getPosicionX();
//		int y = posicion.getPosicionY();
//		xObjetivo = posicion.getSiguiente().get(0).getPosicionX();
//		yObjetivo = posicion.getSiguiente().get(0).getPosicionY();
//		if(x > xObjetivo) {
//			this.setVelX(-1.5f);
//		}else if(x < xObjetivo){
//			this.setVelX(1.5f);
//		}
//		if(y > yObjetivo) {
//			this.setVelY(-1.5f);
//		}else if(y < yObjetivo){
//			this.setVelY(1.5f);
//		}
//		this.posicion = posicion.getSiguiente().get(0);
//		this.posicion.agregarJugador(this);
//		return this.posicion.getSiguiente().size();
//	}

	public int avanzarUnCasillero(int camino) {
		this.x = posicion.getPosicionX();
		this.y = posicion.getPosicionY();
		this.posicion.removerJugador(this);
		System.out.println("[BIFURCACION]Estoy parado en " + posicion.getId() + "(" + posicion.getPosicionX() + ","
				+ posicion.getPosicionY() + ")");
		// System.out.println("[BIFURCACION]Estoy yendo al " +
		// posicion.getSiguiente().get(camino).getId());
		if (posicion.getSiguiente().get(camino) != null) {
			posicion.getSiguiente().get(camino).agregarJugador(this);
			System.out.println("[BIFURCACION]Ahora voy a" + posicion.getId() + "(" + posicion.getPosicionX() + ","
					+ posicion.getPosicionY() + ")");
			if (!movimientos.contains(posicion.getSiguiente().get(camino)))
				movimientos.add(posicion.getSiguiente().get(camino));

//		this.x = posicion.getPosicionX();
//		this.y = posicion.getPosicionY();
			this.x = posicion.getPosicionX();
			this.y = posicion.getPosicionY();
			try {
				xObjetivo = posicion.getSiguiente().get(camino).getPosicionX();
				yObjetivo = posicion.getSiguiente().get(camino).getPosicionY();
				if (x != xObjetivo) {
					if (x > xObjetivo) {
						this.setVelX(-1.5f);
					} else if (x < xObjetivo) {
						this.setVelX(1.5f);
					}

				}
				if (y != yObjetivo) {
					if (y > yObjetivo) {
						this.setVelY(-1.5f);
					} else if (y < yObjetivo) {
						this.setVelY(1.5f);
					}

				}
			} catch (Exception e) {
				System.out.println("Problema detectado" + e.getMessage());
			}
		
			this.posicion = posicion.getSiguiente().get(camino);
		}
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
		int posicionEnInventario = 0;
		Iterator<Item> i = this.getInventario().getItems().iterator();
		while (i.hasNext() && !i.next().equals(item))
			posicionEnInventario++;
		this.getInventario().getItems().remove(posicionEnInventario);
		this.getInventario().setCantItems(this.getInventario().getCantItems() - 1);
	}

	public boolean comprarDolar() {
		if (!this.posicion.isTieneArbolito())
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

	public void setPersonaje(int personaje) {
		this.personaje = personaje;
	}

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

	public Casillero getPosicion() {
		return posicion;
	}

	public void setPosicion(Casillero posicion) {
		this.posicion = posicion;
		posicion.agregarJugador(this);
	}

	public Dado getDado() {
		return this.dado;
	}

	public Partida getPartida() {
		return this.partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
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
