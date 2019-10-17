package juego.personas;

import java.awt.Image;

public class Personaje {
	private String nombre;
	private String descripcion;
	private String pathSkin;
	private int idCharacter;

	public Personaje(String nombre, String desc,int idCharacter,String path) {
		this.nombre = nombre;
		this.descripcion = desc;
		this.pathSkin = path;
		this.idCharacter = idCharacter;
	}

	
	public int getIdCharacter() {
		return idCharacter;
	}

	public void setIdCharacter(int idCharacter) {
		this.idCharacter = idCharacter;
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

	public Image getImage() {
		
		return null;
	}
}
