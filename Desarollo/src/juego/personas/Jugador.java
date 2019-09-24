package juego.personas;

import java.awt.Color;
import java.util.Random;

import juego.administracion.Usuario;

public class Jugador {

	private String nombre;
	private int puntaje;
	private Color color;
	private int puntosEnPartida;

	public Jugador(Usuario usuario) {
		this.nombre = usuario.getUsername();
		this.puntaje = 0;
		this.puntosEnPartida = 0;
		Random rand = new Random(System.currentTimeMillis());
		this.color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return this.nombre + "            " + this.puntaje;
	}

	public int getPuntosEnPartida() {
		return this.puntosEnPartida;
	}

	public void setPuntosEnPartida(int puntosEnPartida) {
		this.puntosEnPartida = puntosEnPartida;
	}

}
