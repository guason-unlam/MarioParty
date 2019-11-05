package graphics.elements;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import graphics.Game;
import juego.tablero.casillero.Casillero;

/* Representacion grafica de un casillero */
public class Spot extends GameObject {

	private int type;
	Texture tex = Game.getInstance();
	Casillero casillero;

	public Spot(float x, float y, int type, ObjectId id, Casillero casillero) {
		super(x, y, id);
		this.type = type;
		this.casillero = casillero;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		if (this.casillero.isTieneArbolito())
			type++;
	}

	@Override
	public void render(Graphics g) {
		if (this.casillero.isTieneRecompensa())
			type = 2;
		else
			type = 0;
//		if(this.casillero.isTieneArbolito())
//			type++;
		g.drawImage(tex.spots[type], (int) x, (int) y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
