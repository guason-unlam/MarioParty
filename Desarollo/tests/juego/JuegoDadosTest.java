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
		//Si cierro System.in, despues no puedo volver a usarlo
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("leer: ");
		int puntaje = sc.nextInt();
		
		assertEquals(puntaje, minijuego.getPuntajeMinijuego(), 0.1);
		
		//sc.close();
	}
	
}
