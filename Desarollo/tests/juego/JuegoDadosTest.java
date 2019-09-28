package juego;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import org.junit.Test;

import juego.tablero.JuegoDados;

public class JuegoDadosTest {

	@Test
	public void ProbarMinijuegoDados(){
		
		JuegoDados minijuego = new JuegoDados();
		
		minijuego.iniciar();
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("leer: ");
		int puntaje = sc.nextInt();
		
		assertEquals(puntaje, minijuego.getPuntajeMinijuego(), 0.1);
		
		sc.close();
	}
	
}
