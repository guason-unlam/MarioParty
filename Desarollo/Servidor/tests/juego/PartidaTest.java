package juego;

import java.util.ArrayList;

import org.junit.Before;

import juego.lobby.Partida;
import juego.lobby.Usuario;

public class PartidaTest {
	private Usuario user1;
	private Usuario user2;
	private Partida partida;

	@Before
	public void inicio() {
		// Uso dos usuarios, condicion necesaria para arrancar una partida
		user1 = new Usuario("usuario", "contrasena");
		user2 = new Usuario("usuario2", "contrasena2");
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(user1);
		usuarios.add(user2);
		// 5 Rondas
		partida = new Partida(usuarios, "MONEDAS", "chico", 1);
	}

//	@Test
//	public void partidaTest() {
//		try {
//			partida.iniciarPartida();
//		}catch(ExcepcionJugadoresInsuficientes e) {
//			Main.mostrar(e.getMessage());
//		}
//		Assert.assertTrue(partida.getGanadorPartida() != null);
//	}
}
