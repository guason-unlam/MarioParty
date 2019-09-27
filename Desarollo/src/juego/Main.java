package juego;

import java.io.IOException;
import java.util.Scanner;

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
	public static int leerInt() {
		Scanner in = new Scanner(System.in);
		int valor =  in.nextInt();
		in.close();
		return valor;
	}
	
}
