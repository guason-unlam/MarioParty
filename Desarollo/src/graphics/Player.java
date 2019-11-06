package graphics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Player extends GameObject{

	private int personaje;
	private float width = 32, height = 32;
	Texture tex = Game.getInstance();
	/* Mas cosas para tratar de mover a mario */
	public float xObjetivo = 0;
	public float yObjetivo = 0;
	
	public Player(float x, float y, int personaje, ObjectId id) {
		super(x, y, id);
		this.personaje = personaje;
		/* Aun mas cosas para tratar de mover a mario */
		xObjetivo = x;
		yObjetivo = y;
	}

	public void tick(LinkedList<GameObject> object) {
		/* Lugar donde debería ir la lógica para ver a donde se mueve mario */
		
		if(this.x < this.xObjetivo) {
			x += 2;
		}else {
			if( this.y != this.yObjetivo) {
				y+=((this.y < this.yObjetivo)?2:-2);
			}else {
				if(this.y > this.yObjetivo) {
					y -= 2;
				}else {
					if( this.x > this.xObjetivo )
					x+=((this.x < this.xObjetivo)?2:-2);
				}
				}
		}
	}

	/* dibujador de personajes */
	public void render(Graphics g) {
		g.drawImage(tex.characters[personaje], (int)x, (int)y, 32, 32, null);
	}
	
	/* Despues sirve para el tema de las colisiones */
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y);
	}

}
