package juego.item;

import juego.random_number_generator.Dado;

public abstract class Item {
	protected Dado dado;
	protected String nombre;
	protected String descripcion;
	protected int carasDado = 6;
	protected int cantidadMaxima;
	protected int multiplicador;

	public Item(String nombre, String descripcion, int cantMaxima, int multiplicador) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidadMaxima = cantMaxima;
		this.multiplicador = multiplicador;
		this.dado = new Dado(this.carasDado); // Lo uso como default, hace un random en 6
	}

	public abstract void activarItem();

	public Dado getDado() {
		return dado;
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

	public void setDado(Dado dado) {
		this.dado = dado;
	}

	public void setCarasDado(int carasDado) {
		this.carasDado = carasDado;
	}

	public void setCantidadMaxima(int cantidadMaxima) {
		this.cantidadMaxima = cantidadMaxima;
	}

	public void setMultiplicador(int multiplicador) {
		this.multiplicador = multiplicador;
	}
}