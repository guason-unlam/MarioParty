package juego.item;

import java.util.Iterator;

import juego.personas.Jugador;

public abstract class Item extends Recompensa {
	protected String nombre;
	protected String descripcion;
	protected Jugador due�o;

	public Item(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	protected abstract void activarItem();
	
	public void usarItem() {
		activarItem();
		int posicionEnInventario=0;
		Iterator<Item> i = this.due�o.getInventario().getItems().iterator();
		while(i.hasNext() && !i.next().equals(this))
			posicionEnInventario++;
		this.due�o.getInventario().getItems().remove(posicionEnInventario);
	}
	
	public abstract Jugador elegirObjetivo();
	
	public Jugador getDue�o(){
		return this.due�o;
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
	

	@Override
	public void darRecompensa(Jugador jugador) {
		jugador.getInventario().agregarItem(this);
		
	}
}
