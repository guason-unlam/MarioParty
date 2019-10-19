package graphics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;


public abstract class GameObject {
	
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
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}

	public float getVelX(){
		return velX;
	}
	public float getVelY(){
		return velY;
	}
	public void setVelX(float velX){
		this.velX = velX;
	}
	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	public ObjectId getId() {
		return id;
	}
	
}
