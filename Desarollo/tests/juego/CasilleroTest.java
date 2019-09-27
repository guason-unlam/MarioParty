package juego;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Test;

import juego.lobby.Usuario;
import juego.personas.Jugador;
import juego.tablero.casillero.Casillero;
import junit.framework.Assert;

public class CasilleroTest {

	@SuppressWarnings("deprecation")
	@Test
	public void main() {
		Casillero cas = new Casillero();
		Assert.assertEquals(cas.getId(), 0);
		cas = TestSaltarCasillero(cas, 3);
		Assert.assertEquals(cas.getId(), 3);

	}

	public static Casillero TestSaltarCasillero(Casillero casillero, int salto) {
		for (int i = 0; i < salto; i++) {
			casillero.setId(casillero.getId() + i);
		}

		return casillero;
	}

	@Test
	public void hayId() {
		
		Casillero cas = new Casillero();
		
		cas.setId(15);
		
		assertTrue(15 == cas.getId());
	}

	@Test
	public void noHayId() {
		
		Casillero cas = new Casillero();
		
		cas.setId(14);
		
		assertFalse(15 == cas.getId());
	}
	
	@Test
	public void hayColor() {
		
		Casillero cas = new Casillero();
		
		Color col = new Color(255);
		
		cas.setColor(col);
		
		Color col2 = new Color(255);
		
		cas.setColor(col2);
		
		assertTrue(col2 == cas.getColor());
	}

	@Test
	public void noHayColor() {
		
		Casillero cas = new Casillero();
		
		Color col = new Color(123);
		
		cas.setColor(col);
		
		Color col2 = new Color(255);
		
		cas.setColor(col2);
		
		assertTrue(col2 == cas.getColor());
	}


	
	@Test 
	public void hayPosicionX() {
		Casillero cas = new Casillero();
		
		cas.setPosicionX(10);
		
		assertTrue(10 == cas.getPosicionX());
		
	}

	@Test 
	public void noHayPosicionX() {
		Casillero cas = new Casillero();
		
		cas.setPosicionX(10);
		
		assertFalse(11 == cas.getPosicionX());
	}
	
	@Test 
	public void hayPosicionY() {
		Casillero cas = new Casillero();
		
		cas.setPosicionY(10);
		
		assertTrue(10 == cas.getPosicionY());
	}
	
	@Test 
	public void noHayPosicionY() {
		Casillero cas = new Casillero();
		
		cas.setPosicionY(10);
		
		assertFalse(11 == cas.getPosicionY());
	}
	
	@Test 
	public void esPrimeraVez() {
		Casillero cas = new Casillero();
		
		cas.setPrimeraVez(true);
		
		assertTrue(true == cas.isPrimeraVez());
	}
	
	@Test 
	public void noEsPrimeraVez() {
		Casillero cas = new Casillero();
		
		cas.setPrimeraVez(false);
		
		assertFalse(true == cas.isPrimeraVez());
	}

}
