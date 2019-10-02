package juego;
import org.junit.Assert;
import org.junit.Test;

import juego.misc.Dado;

public class DadoTest {
	@Test
	public void numeroEnRango() {
		Dado d = new Dado(6);
		for(int i=0; i<100; i++)
		{
			int resultado = d.tirar();
			Assert.assertTrue("Error, valor excede el maximo", 6 >= resultado);
			Assert.assertTrue("Error, valor excede el minimo", 1 <= resultado);
		}
	}
}
