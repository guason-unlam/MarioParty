package juego.item;

import juego.personas.Jugador;

public abstract class Item extends Recompensa {
	protected String nombre;
	protected String descripcion;
	protected Jugador duenio;

	public Item(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public abstract void activarItem(Jugador objetivo);

	public abstract Jugador elegirObjetivo();

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
