package juego.tablero;

import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import juego.lobby.Partida;
import juego.lobby.Sala;
import juego.misc.Dado;

public class JuegoDados extends MiniJuego{

	Dado dadoJuego;
	int tiroDado;
	int puntajeMinijuego;
	
	@Override
	public void iniciar() {
		// El minijuego JuegoDados consiste en que cada jugador arrojara el dado 
		// en un turno y sumara puntos. El ganador sera quien sume el mayor puntaje
		this.setDescripcion("El minijuego JuegoDados consiste en que cada jugador arrojara el dado en un turno y sumara puntos. El ganador sera quien sume el mayor puntaje");
		this.setNombre("Juego Dados");
		int puntajeTotal = 0, valorIngresado;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("MiniJuego Dados Iniciado :)");
		dadoJuego = new Dado(6);
		
		
		
		System.out.println("Presione cualquier tecla para arrojar el dado");
		sc.next();
		tiroDado = dadoJuego.tirar();
		System.out.println(tiroDado);
		System.out.println("Ya arrojaste el dado, ahora tenes que adivinar.. que numero salio? 1-2-3-4-5-6??");
		valorIngresado = sc.nextInt();
		while(valorIngresado > 6 || valorIngresado < 1) {
			System.out.println("valor erroneo, reingresa el valor correcto. Que numero salio? 1-2-3-4-5-6??");
			valorIngresado = sc.nextInt();
		}
		
		
		if(valorIngresado == tiroDado)
			puntajeTotal+=tiroDado;
		
		
		System.out.println("Presione cualquier tecla para arrojar el dado");
		sc.next();
		tiroDado = dadoJuego.tirar();
		System.out.println(tiroDado);
		System.out.println("Ya arrojaste el dado, ahora tenes que adivinar.. que numero salio? 1-2-3-4-5-6??");
		valorIngresado = sc.nextInt();
		while(valorIngresado > 6 || valorIngresado < 1) {
			System.out.println("valor erroneo, reingresa el valor correcto. Que numero salio? 1-2-3-4-5-6??");
			valorIngresado = sc.nextInt();
		}
		System.out.println(tiroDado);
		if(valorIngresado == tiroDado)
			puntajeTotal+=tiroDado;
		
		
		sc.close();
		
		System.out.println("Puntaje total jugador " + puntajeTotal);
		setPuntajeMinijuego(puntajeTotal);
	}
	
	public int getPuntajeMinijuego() {
		return this.puntajeMinijuego;
	}
	
	public void setPuntajeMinijuego(int puntaje) {
		this.puntajeMinijuego = puntaje;
	}
	
}
