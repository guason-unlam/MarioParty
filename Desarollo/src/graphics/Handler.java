package graphics;

import java.awt.Graphics;
import java.util.LinkedList;



public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	
	public void tick() {
		for(int i = 0; i< object.size(); i++) {
			
			tempObject = object.get(i);
			tempObject.tick(object);
			
			if(object.get(i).enMovimientoX) {
				object.get(i).velX = 0.5f;
			}
			if(object.get(i).enMovimientoY) {
				object.get(i).velY = 0.5f;
			}
		}
	}
	
	/* Llama al dibujador de cada objeto */
	public void render(Graphics g) {
		
		for(int i = 0; i< object.size(); i++) {
			
			tempObject = object.get(i);
			
			tempObject.render(g);
			
		}
		
	}
	
	/* Carga un objeto a la lista de objetos a mostrar */
	public void addObject(GameObject object) {
		this.object.add(object);
	}

	/* Saca un objeto a la lista de objetos a mostrar */
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
}
