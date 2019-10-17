package juego;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Test;

import juego.tablero.casillero.Casillero;

public class CasilleroTest {

	@Test
	public void hayId() {
		
		Casillero cas = new Casillero(15);
		
		
		assertTrue(15 == cas.getId());
	}

	@Test
	public void noHayId() {
		
		Casillero cas = new Casillero(14);
		
		
		assertFalse(15 == cas.getId());
	}
	
	@Test
	public void hayColor() {
		
		Casillero cas = new Casillero(0);
		
		Color col = new Color(255);
		
		cas.setColor(col);
		
		Color col2 = new Color(255);
		
		cas.setColor(col2);
		
		assertTrue(col2 == cas.getColor());
	}

	@Test
	public void noHayColor() {
		
		Casillero cas = new Casillero(0);
		
		Color col = new Color(123);
		
		cas.setColor(col);
		
		Color col2 = new Color(255);
		
		cas.setColor(col2);
		
		assertTrue(col2 == cas.getColor());
	}


	
	@Test 
	public void hayPosicionX() {
		Casillero cas = new Casillero(0);
		
		cas.setPosicionX(10);
		
		assertTrue(10 == cas.getPosicionX());
		
	}

	@Test 
	public void noHayPosicionX() {
		Casillero cas = new Casillero(0);
		
		cas.setPosicionX(10);
		
		assertFalse(11 == cas.getPosicionX());
	}
	
	@Test 
	public void hayPosicionY() {
		Casillero cas = new Casillero(0);
		
		cas.setPosicionY(10);
		
		assertTrue(10 == cas.getPosicionY());
	}
	
	@Test 
	public void noHayPosicionY() {
		Casillero cas = new Casillero(0);
		
		cas.setPosicionY(10);
		
		assertFalse(11 == cas.getPosicionY());
	}
	
	@Test 
	public void esPrimeraVez() {
		Casillero cas = new Casillero(0);
		
		cas.setPrimeraVez(true);
		
		assertTrue(true == cas.isPrimeraVez());
	}
	
	@Test 
	public void noEsPrimeraVez() {
		Casillero cas = new Casillero(0);
		
		cas.setPrimeraVez(false);
		
		assertFalse(true == cas.isPrimeraVez());
	}

}
