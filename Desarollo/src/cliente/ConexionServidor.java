package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.json.JsonObject;

public class ConexionServidor extends Thread {

	private Socket socketIn;
	private Socket socketOut;

	private DataInputStream entrada;
	private DataOutputStream salida;

	public ConexionServidor(Socket servidorOut, Socket servidorIn) {
		this.socketIn = servidorIn;
		this.socketOut = servidorOut;
		try {
			this.entrada = new DataInputStream(this.socketIn.getInputStream());
			this.salida = new DataOutputStream(this.socketOut.getOutputStream());

		} catch (IOException ex) {
			System.out.println("Error al crear los stream de entrada y salida : " + ex.getMessage());
		}
	}

	public void enviarAlServidor(JsonObject paquete) {
		try {
			System.out.println(paquete.toString());
			this.salida.writeUTF(paquete.toString());
		} catch (IOException e) {
			System.out.println("Error " + paquete.toString());
		}

	}

	public DataInputStream getEntrada() {
		return entrada;
	}


}
