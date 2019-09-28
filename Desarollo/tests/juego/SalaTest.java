package juego;

import org.junit.Assert;
import org.junit.Test;

import juego.lobby.Sala;
import juego.lobby.Usuario;

public class SalaTest {

	@Test
	public void TestSalaLlena() {
		Usuario usuarioCreador = new Usuario("prueba", "1234");
		Usuario usuarioParticipante1 = new Usuario("conectarse1", "12345");
		Usuario usuarioParticipante2 = new Usuario("conectarse2", "123456");
//		Sala sala = new Sala("SalaTest", 2, usuarioCreador);
		Sala sala = usuarioCreador.crearSala();
		usuarioParticipante1.conectarseALaSala(sala);
		Assert.assertFalse(usuarioParticipante2.conectarseALaSala(sala)); // compruebo que al querer agregar un tercer

	}

	@Test
	public void TestCreadorSala() {
		Usuario usuarioCreador = new Usuario("prueba", "1234");
		Sala sala = usuarioCreador.crearSala();
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
	}
}
