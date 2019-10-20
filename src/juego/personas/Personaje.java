package juego.personas;

public class Personaje {
	private String nombre;
	private String descripcion;
	private String pathSkin;

	public Personaje(String nombre, String desc, String path) {
		this.nombre = nombre;
		this.descripcion = desc;
		this.pathSkin = path;
	}

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

	public String getPathSkin() {
		return pathSkin;
	}

	public void setPathSkin(String pathSkin) {
		this.pathSkin = pathSkin;
	}
}
