package juego.item;

import juego.random_number_generator.Dado;

public abstract class Item {
	private String nombre;
	private String descripcion;
	private Dado dado;

	public Item(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		Dado dado = new Dado(6); // Lo uso como default, hace un random en 6
	}

	public Dado getDado() {
		return dado;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public abstract void activarItem();
}
