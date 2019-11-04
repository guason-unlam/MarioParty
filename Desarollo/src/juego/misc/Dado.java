package juego.misc;

public class Dado {
	private int cantidadCaras;

	public Dado(int cantCaras) {
		this.cantidadCaras = cantCaras;
	}

	public int tirar() {
		return (int) (Math.random() * this.cantidadCaras + 1);
	}

	public void setCantidadCaras(int cant) {
		this.cantidadCaras = cant;
	}

	public int getCantidadCaras() {
		return this.cantidadCaras;
	}

}