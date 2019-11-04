package juego;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import juego.lobby.Sala;
import juego.lobby.Usuario;

public class SalaTest {
	Usuario usuarioCreador;
	Usuario usuarioParticipante1;
	Usuario usuarioParticipante2;

	@Before
	public void iniciar() {
		usuarioCreador = new Usuario("prueba", "1234");
		usuarioParticipante1 = new Usuario("conectarse1", "12345");
		usuarioParticipante2 = new Usuario("conectarse2", "123456");

	}

	@Test
	public void TestSalaLlena() {
		Sala sala = usuarioCreador.crearSala("SalaTest", 2);
		usuarioParticipante1.conectarseALaSala(sala);
		Assert.assertFalse(usuarioParticipante2.conectarseALaSala(sala)); // compruebo que al querer agregar un tercer
	}

	@Test
	public void TestCreadorSala() {
		Usuario usuarioCreador = new Usuario("prueba", "1234");
		Sala sala = usuarioCreador.crearSala("SalaTest", 2);
		Assert.assertEquals(usuarioCreador, sala.getUsuarioCreador());
	}

	@Test
	public void TestCreadorSalaSinPw() {
		Usuario usuarioCreador = new Usuario("prueba", "1234");
		Sala sala = new Sala("Sala 1", 10, usuarioCreador);
		Assert.assertEquals(usuarioCreador, sala.getUsuarioCreador());
		Assert.assertEquals(10, sala.getCapacidadMaxima());
	}

	@Test
	public void esCreadorAdminTest() {
		Usuario usuarioCreador = new Usuario("prueba", "1234");
		Sala sala = new Sala("Sala 1", 10, usuarioCreador);
		Assert.assertEquals(true, sala.esAdmin(usuarioCreador));
	}

	@Test
	public void sacarUsuarioDeSalaTest() {
		Usuario usuarioCreador = new Usuario("prueba", "1234");
		Sala sala = new Sala("Sala 1", 10, usuarioCreador);
		sala.sacarUsuarioDeSala(usuarioCreador);
		Assert.assertEquals(null, usuarioCreador.getSala());
		usuarioParticipante1.conectarseALaSala(sala);
		Assert.assertTrue(sala.sacarUsuarioDeSala(usuarioParticipante1));
		Assert.assertFalse(sala.sacarUsuarioDeSala(usuarioParticipante1));
		Assert.assertTrue(sala.getCapacidadActual() == 0);
	}
}
