package juego.item;

import java.util.ArrayList;
import java.util.Iterator;

public class Inventario {
	private ArrayList<Item> items;
	private int capacidad;

	public void listarItems() {
		Iterator<Item> it = this.items.iterator();
		// Voy mostrando los items
		while (it.hasNext()) {
			Item itemActual = it.next();
			System.out.println(itemActual.getNombre());
		}

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
