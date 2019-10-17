package juego.tablero;

public abstract class MiniJuego {
	protected String nombre;
	protected String descripcion;

	public abstract void iniciar();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
