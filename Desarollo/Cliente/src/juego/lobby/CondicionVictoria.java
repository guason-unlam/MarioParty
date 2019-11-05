package juego.lobby;

public class CondicionVictoria {
	private TipoCondicionVictoria tipo;
	private int cantidad;

	public CondicionVictoria(TipoCondicionVictoria tipo, int cant) {
		this.tipo = tipo;
		this.cantidad = cant;
	}

	public TipoCondicionVictoria getTipo() {
		return tipo;
	}

	public void setTipo(TipoCondicionVictoria tipo) {
		this.tipo = tipo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
