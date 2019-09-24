package juego.item;

public class ModificadorPosicion extends Item {
	private int cantidadMaxima;
	private int multiplicador;

	/*
	 * El multiplicador puede ser cualquier numero distinto de cero, ya que me
	 * permite por ejemplo hacer bonus, etc
	 * 
	 * @param cantMaxima, multiplicador
	 * 
	 * @return modificarPosicion
	 */
	public ModificadorPosicion(String nombre, String descripcion, int cantMaxima, int multiplicador) {
		super(nombre, descripcion);
		this.cantidadMaxima = cantMaxima;
		this.multiplicador = multiplicador;
	}

	@Override
	public void activarItem() {
		int cant = this.cantidadMaxima>this.carasDado?this.cantidadMaxima:this.carasDado;
		this.dado.tirar(cant);
	}

	public int getCantidadMaxima() {
		return cantidadMaxima;
	}

	public int getMultiplicador() {
		return multiplicador;
	}

}
