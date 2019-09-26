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
}
