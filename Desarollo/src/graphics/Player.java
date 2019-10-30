package graphics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import juego.tablero.casillero.Casillero;

public class Player extends GameObject{

	private int personaje;
	private float width = 32, height = 32;
	Texture tex = Game.getInstance();
	/* Mas cosas para tratar de mover a mario */
	public int xObjetivo = 0;
	public int yObjetivo = 0;
	private Casillero posicion; //el casillero en el q esta el jugador
	
	public Player(Casillero posicionInicial, int personaje, ObjectId id) {
		super(posicionInicial.getPosicionX(), posicionInicial.getPosicionY(), id);
		this.posicion = posicionInicial;
		this.personaje = personaje;
		/* Aun mas cosas para tratar de mover a mario */
		xObjetivo = (int)x;
		yObjetivo = (int)y;
	}

	public void tick(LinkedList<GameObject> object) {
		/* Lugar donde debería ir la lógica para mover a mario */
		if( enMovimientoX && x > xObjetivo-1 && x < xObjetivo+1) {
			velX = 0;
			enMovimientoX = false;
//			enMovimientoY = true;
		}
		if(enMovimientoY && y > yObjetivo-1 && y < yObjetivo+1) {
			velY = 0;
			enMovimientoY = false;
		}
		x += velX;
		y += velY;
	}

	/* dibujador de personajes */
	public void render(Graphics g) {
		g.drawImage(tex.characters[personaje], (int)x, (int)y, null);
	}
	
	/* Despues sirve para el tema de las colisiones */
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y);
	}
	
	/*	Intento de mover a mario/cualquierPj	*/
	public void moverDeCasilleroACasillero(){
		int x = posicion.getPosicionX();
		int y = posicion.getPosicionY();
		this.xObjetivo = posicion.getSiguiente().get(0).getPosicionX();
		this.yObjetivo = posicion.getSiguiente().get(0).getPosicionY();
		if(x > posicion.getSiguiente().get(0).getPosicionX()) {
			this.setVelX(-1.5f);
		}else if(x < posicion.getSiguiente().get(0).getPosicionX()){
			this.setVelX(1.5f);
		}
		this.enMovimientoX = true;
		if(y > posicion.getSiguiente().get(0).getPosicionY()) {
			this.setVelY(-1.5f);
		}else if(y < posicion.getSiguiente().get(0).getPosicionY()){
			this.setVelY(1.5f);
		}
		this.posicion = posicion.getSiguiente().get(0);
		
	}

}
