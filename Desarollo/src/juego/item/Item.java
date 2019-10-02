package juego.item;

import java.util.Iterator;

import juego.personas.Jugador;

public abstract class Item extends Recompensa {
	protected String nombre;
	protected String descripcion;
	protected Jugador duenio;

	public Item(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public abstract void activarItem(Jugador objetivo);// Es PROTECTED, pero lo paso a public para que permita el
														// testeo.

	public void usarItem() {
		activarItem(elegirObjetivo());
		int posicionEnInventario = 0;
		Iterator<Item> i = this.duenio.getInventario().getItems().iterator();
		while (i.hasNext() && !i.next().equals(this))
			posicionEnInventario++;
		this.duenio.getInventario().getItems().remove(posicionEnInventario);
		this.duenio.getInventario().setCantItems(this.duenio.getInventario().getCantItems() - 1);
	}

	protected abstract Jugador elegirObjetivo();

	public Jugador getDuenio() {
		return this.duenio;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public void darRecompensa(Jugador jugador) {
		this.duenio = jugador;
		jugador.getInventario().agregarItem(this);
		this.duenio = jugador;
	}
}
