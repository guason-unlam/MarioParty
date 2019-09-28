package juego;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import juego.lobby.Partida;
import juego.lobby.Sala;
import juego.lobby.Usuario;
import juego.personas.Jugador;

public class UsuarioTest {
	
	private Usuario usuario1;
	private Usuario usuario2;
	private Usuario usuario3;
	private Sala sala1;
	
	private Partida partida;
	private Jugador jugador1;
	private Jugador jugador2;
	
	@Before
	public void iniciar() {
		usuario1 = new Usuario("Esteban", "1234");
		usuario2 = new Usuario("Alexis", "4321");
		usuario3 = new Usuario("Lucas", "1111");
		sala1 = usuario1.crearSala();
;
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(usuario1);
		usuarios.add(usuario2);
		// 1 Ronda
		partida = new Partida(usuarios, 1);
		jugador1 = usuario1.getJugador();
		jugador2 = usuario2.getJugador();
	}
	
	@Test
	public void perteneceASala() {
		Assert.assertTrue(usuario1.getSala() == sala1);
		usuario2.conectarseALaSala(sala1);
		Assert.assertTrue(usuario2.getSala() == sala1);
	}
	
	@Test
	public void obtenerJugador() {
		Jugador j1 = usuario1.getJugador();
		Assert.assertTrue(j1 != null);
		Assert.assertEquals("Esteban", usuario1.getJugador().getNombre());
	}
	
	@Test
	public void asignarJugador() {
		Assert.assertTrue(null != usuario2.getJugador());
		usuario2.setJugador(null);
		Assert.assertTrue(null == usuario2.getJugador());
	}

	@Test
	public void conectarseASala() {
		
		Assert.assertTrue(usuario3.getSala() == null);
		usuario3.conectarseALaSala(sala1);
		Assert.assertTrue(usuario3.getSala() == sala1);
		
	}
	
	@Test
	public void salirDeSala() {
		Sala sala2 = usuario3.crearSala();
		Assert.assertTrue(usuario3.getSala() != null);
		usuario3.salirDeSala();
		Assert.assertTrue(usuario3.getSala() == null);
		
	}
	
}
