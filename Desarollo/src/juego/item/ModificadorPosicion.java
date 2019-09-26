package juego.item;

import juego.personas.Jugador;

public class ModificadorPosicion extends Item {

	/*
	 * El multiplicador puede ser cualquier numero distinto de cero, ya que me
	 * permite por ejemplo hacer bonus, etc
	 * 
	 * @param cantMaxima, multiplicador
	 * 
	 * @return modificarPosicion
	 */
	public ModificadorPosicion(String nombre, String descripcion, int cantMaxima, int multiplicador) {
		super(nombre, descripcion, cantMaxima, multiplicador);
	}

	@Override
	public void activarItem(Jugador jugador) {
		int cant = this.cantidadMaxima>this.carasDado?this.cantidadMaxima:this.carasDado;
		this.dado.tirar(cant);
	}

}
