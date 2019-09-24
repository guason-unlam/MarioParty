package juego.item;

import java.util.ArrayList;
import java.util.Iterator;

public class Inventario {
	private ArrayList<Item> items;
	private int capacidad;

	public Inventario(int capacidad) {
		items = new ArrayList<Item>();
		this.capacidad = capacidad;
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
		return true;
	}
}
