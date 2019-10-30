package graphics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;


/* Representacion grafica de un casillero */
public class Spot extends GameObject {

	private int type;
	Texture tex = Game.getInstance();
	
	public Spot(float x, float y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}

	public void tick(LinkedList<GameObject> object) {
	}

	public void render(Graphics g) {
		if(type == 0) {
			g.drawImage(tex.spots[0], (int)x, (int)y, null);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y, 32, 32);
	}

}
