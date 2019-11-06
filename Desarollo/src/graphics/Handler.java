package graphics;

import java.awt.Graphics;
import java.util.LinkedList;



public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	
	public void tick() {
		for(int i = 0; i< object.size(); i++) {
			
			object.get(i).tick(null);
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
