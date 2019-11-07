package graphics.elements;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import graphics.Game;

public class Road extends GameObject {

	Texture tex = Game.getInstance();
	private int type;

	public Road(float x, float y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {

	}

	@Override
	public void render(Graphics g) {
		if (type == 0) {
			g.drawImage(tex.roads[0], (int) x, (int) y, null);
		}
	}

	@Override
	public Rectangle getBounds() {
		/* TODO: Change dimensions */
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
