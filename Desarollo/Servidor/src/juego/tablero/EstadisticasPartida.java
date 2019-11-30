package juego.tablero;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class EstadisticasPartida {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;

	private int usuario;
	private int partida;
	private int cantidadMonedas;
	private int cantidadDolares;
	private int minijuegosGanados;
	private int puntosTotales;

	private EstadisticasPartida(int usuario, int partida, int cantidadMonedas, int cantidadDolares,
			int minijuegosGanados, int puntosTotales) {
		this.usuario = usuario;
		this.partida = partida;
		this.cantidadMonedas = cantidadMonedas;
		this.cantidadDolares = cantidadDolares;
		this.minijuegosGanados = minijuegosGanados;
		this.puntosTotales = puntosTotales;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public int getPartida() {
		return partida;
	}

	public void setPartida(int partida) {
		this.partida = partida;
	}

	public int getCantidadMonedas() {
		return cantidadMonedas;
	}

	public void setCantidadMonedas(int cantidadMonedas) {
		this.cantidadMonedas = cantidadMonedas;
	}

	public int getCantidadDolares() {
		return cantidadDolares;
	}

	public void setCantidadDolares(int cantidadDolares) {
		this.cantidadDolares = cantidadDolares;
	}

	public int getMinijuegosGanados() {
		return minijuegosGanados;
	}

	public void setMinijuegosGanados(int minijuegosGanados) {
		this.minijuegosGanados = minijuegosGanados;
	}

	public int getPuntosTotales() {
		return puntosTotales;
	}

	public void setPuntosTotales(int puntosTotales) {
		this.puntosTotales = puntosTotales;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
