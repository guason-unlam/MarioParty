package juego;
import org.junit.Assert;
import org.junit.Test;

import juego.Dado;

public class DadoTest {
	@Test
	public void numeroEnRango() {
		Dado d = new Dado(10);
		int resultado = d.lanzarDado();
		System.out.println(resultado);
		Assert.assertTrue("Error, valor excede el maximo", 10 >= resultado);
		Assert.assertTrue("Error, valor excede el minimo", 1 <= resultado);
	}
}
