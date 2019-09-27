package juego;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		iniciarJuego();
	}

	public static void iniciarJuego() {
		System.out.println("Iniciando juego...");
	}
	
	public static void mostrar(String texto) {
		System.out.println(texto);
	}
	
	public static int leer() {
		try {
			return System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
}
