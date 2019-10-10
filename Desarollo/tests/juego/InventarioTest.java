package juego;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import juego.item.Inventario;
import juego.item.Item;
import juego.item.ModificadorDado;

public class InventarioTest {
	private Inventario inventario;
	private Item item;

	@Before
	public void inicio() {
		inventario = new Inventario(3);
		item = new ModificadorDado();
	}

	@Test
	public void agregarItemsInventarioConLugarTest() {
		Assert.assertTrue(inventario.agregarItem(item));
	}

	@Test
	public void agregarItemsInventarioLlenoTest() {
		inventario = new Inventario(0);
		Assert.assertFalse(inventario.agregarItem(item));
	}

	@Test
	public void listarItemsMayorAUnoTest() {
		// Agrego un item para testear
		inventario.agregarItem(item);
		Iterator<Item> iterator = inventario.listarItems();
		int i = 0;
		while (iterator.hasNext()) {
			i++;
			iterator.next();
		}
		Assert.assertEquals(1, i);
	}

	@Test
	public void listarItemsCeroTest() {
		Iterator<Item> iterator = inventario.listarItems();
		int i = 0;
		while (iterator.hasNext()) {
			i++;
			iterator.next();
		}
		Assert.assertEquals(0, i);
	}
	
		@Test
	public void seSumaItemAInventarioTest(){
		Jugador jugador1 = new Jugador(usuario, tablero, partida);
		Item item;
		item = new ModificadorDado();
		int cantidadItems = jugador1.getInventario().getCantItems();
		item.darRecompensa(jugador1);
		Assert.assertEquals(cantidadItems+1, jugador1.getInventario().getCantItems());
	}
	
	@Test
	public void capacidadMaximaInventarioTest(){
		Jugador jugador1 = new Jugador(usuario, tablero, partida);
		Item item;
		item = new ModificadorDado();
		for (int i=0; i<200; i++){
			item.darRecompensa(jugador1);
		}
		Assert.assertEquals(jugador1.getInventario().getCapacidad(), jugador1.getInventario().getCantItems());
	}
}
