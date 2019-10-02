package juego;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import juego.personas.Personaje;

public class PersonajeTest {

	@Test
	public void hayPersonajeDescripcion() {
		
		Personaje per = new Personaje("Mario","el numero 1","./src/main/");
		
		assertEquals("el numero 1",per.getDescripcion());
		
	}
	
	@Test
	public void hayPersonajeNombre() {
		
		Personaje per = new Personaje("Mario","el numero 1","./src/main/");
		
		assertEquals("el numero 1",per.getDescripcion());

	}
	
	@Test
	public void hayPersonajePath() {
		
		Personaje per = new Personaje("Mario","el numero 1","./src/main/");
		
		assertEquals("./src/main/",per.getPathSkin());
	}
	@Test
	public void noHayPersonajeNombreFalso() {
		
		Personaje per = new Personaje("Mario","el numero 1","./src/main/");
		
		assertNotEquals("Wario",per.getNombre());
	}
	
	@Test
	public void noHayPersonajeDescripcionFalsa() {
		
		Personaje per = new Personaje("Mario","el numero 1","./src/main/");
		
		assertNotEquals("el numero 2",per.getDescripcion());
		
	}
	
	@Test
	public void noHayPersonajePathFalso() {
		
		Personaje per = new Personaje("Mario","el numero 1","./src/main/");
		
		assertNotEquals("./src/mein/",per.getPathSkin());
	
	}

}
