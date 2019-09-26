package juego;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import juego.tablero.MiniJuego;

public class MiniJuegoTest {

	@Test
	public void hayDescricion() {
		
		MiniJuego mini = new MiniJuego() {
			
			@Override
			public void iniciar() {
				// TODO Auto-generated method stub
				
			}
		};
		
		mini.setDescripcion("HOLA MUNDO");
		
		assertEquals("HOLA MUNDO", mini.getDescripcion());
	}

	@Test
	public void noHayDescricion() {
		
		MiniJuego mini = new MiniJuego() {
			
			@Override
			public void iniciar() {
				// TODO Auto-generated method stub
				
			}
		};
		
		mini.setDescripcion("HOLA MUNDO");
		
		assertNotEquals("", mini.getDescripcion());
	}
	
	@Test
	public void hayNombre() {
		
		MiniJuego mini = new MiniJuego() {
			
			@Override
			public void iniciar() {
				// TODO Auto-generated method stub
				
			}
		};
		
		mini.setNombre("Mario");
		
		assertEquals("Mario", mini.getNombre());
	}

	@Test
	public void noHayNombre() {
		
		MiniJuego mini = new MiniJuego() {
			
			@Override
			public void iniciar() {
				// TODO Auto-generated method stub
				
			}
		};
		
		mini.setNombre("Mario");
		
		assertNotEquals("", mini.getNombre());
	}


}
