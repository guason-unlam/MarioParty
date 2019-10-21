package graphics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Player extends GameObject{

	private int personaje;
	private float width = 32, height = 32;
	Texture tex = Game.getInstance();
	/* Mas cosas para tratar de mover a mario */
	public int xObjetivo = 0;
	public int yObjetivo = 0;
	
	public Player(float x, float y, int personaje, ObjectId id) {
		super(x, y, id);
		this.personaje = personaje;
		/* Aun mas cosas para tratar de mover a mario */
		xObjetivo = (int)x;
		yObjetivo = (int)y;
	}

	public void tick(LinkedList<GameObject> object) {
		/* Lugar donde debería ir la lógica para mover a mario */
		if( enMovimientoY && (int)x == xObjetivo) {
			velX = 0;
			enMovimientoX = false;
			enMovimientoY = true;
		}
		if(enMovimientoY && (int)y == yObjetivo) {
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

}
