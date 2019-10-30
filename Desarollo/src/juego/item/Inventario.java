package juego.item;

import java.util.ArrayList;
import java.util.Iterator;

public class Inventario {

	private ArrayList<Item> items;
	private int capacidad;
	private int cantItems;

	public void setCantItems(int cantItems) {
		this.cantItems = cantItems;
	}


	public Inventario(int capacidad) {
		items = new ArrayList<Item>();
		this.capacidad = capacidad;
		cantItems = 0;
	}

	
	public Iterator<Item> listarItems() {
		return this.items.iterator();
	}

	/*
	 * Agrega un item, si hay lugar (caso contrario devuelve un false)
	 * 
	 * @param Item
	 * 
	 * @return boolean
	 */
	public boolean agregarItem(Item item) {
		if (items.size() + 1 > capacidad) {
			return false;
		}

		this.items.add(item);
		cantItems++;
		return true;
	}

	public ArrayList<Item> getItems() {
		return items;
	}


	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	public int getCantItems() {
		return this.cantItems;
	}


	public int getCapacidad() {
		return capacidad;
	}


	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
}
