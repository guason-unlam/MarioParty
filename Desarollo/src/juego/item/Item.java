package juego.item;

import juego.random_number_generator.Dado;

public abstract class Item {
	protected String nombre;
	protected String descripcion;
	protected Dado dado;
	protected int carasDado = 6;

	public Item(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		Dado dado = new Dado(this.carasDado); // Lo uso como default, hace un random en 6
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
