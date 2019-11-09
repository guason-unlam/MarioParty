package graphics.elements;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {

@Override
	public String toString() {
		return "GameObject [x=" + x + ", y=" + y + ", id=" + id + ", velX=" + velX + ", velY=" + velY
				+ ", enMovimientoX=" + enMovimientoX + ", enMovimientoY=" + enMovimientoY + "]";
	}

	//	posicion de los objetos
	protected float x, y;
	protected ObjectId id;
	protected float velX = 0, velY = 0;
	/* Mas cosas para tratar de mover a mario */
	protected boolean enMovimientoX = false;
	protected boolean enMovimientoY = false;

	public GameObject(float x, float y, ObjectId id) {

		this.x = x;
		this.y = y;
		this.id = id;

	}

	public abstract void tick(LinkedList<GameObject> object);

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelX(float velX) {
		this.velX = velX;
		this.enMovimientoX = velX != 0;
	}

	public void setVelY(float velY) {
		this.velY = velY;
		this.enMovimientoY = velY != 0;
	}

	public ObjectId getId() {
		return id;
	}

}
