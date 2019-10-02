package juego;

import java.io.FilterInputStream;
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
		// Se usa esta forma especial ya que si cierro System.in, luego falla
		// https://stackoverflow.com/a/32353284/7844735
		Scanner in = new Scanner(new FilterInputStream(System.in) {
			@Override
			public void close() {
			}
		});
		int valor = in.nextInt();
		return valor;
	}

}
