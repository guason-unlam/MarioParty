package graphics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Road extends GameObject{

	Texture tex = Game.getInstance();
	private int type;
	
	public Road(float x, float y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}

	public void tick(LinkedList<GameObject> object) {
		
	}

	public void render(Graphics g) {
		if(type == 0) {
			g.drawImage(tex.roads[0], (int)x, (int)y, null);
		}
	}

	public Rectangle getBounds() {
		/* TODO: Change dimensions*/
		return new Rectangle((int)x,(int)y, 32, 32);
	}

}
