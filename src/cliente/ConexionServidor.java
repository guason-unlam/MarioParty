package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ConexionServidor extends Thread {
	private Socket socket;
	private DataOutputStream salidaDatos;

	public ConexionServidor(Socket socket) {
		this.socket = socket;
		try {
			this.salidaDatos = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException ex) {
			System.out.println("Error al crear el stream de salida : " + ex.getMessage());
		} catch (NullPointerException ex) {
			System.out.println("El socket no se creo correctamente. ");
		}
	}

	//Ya mando la pw encriptada
	
	public void logear(String usuario, String password) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

		// Intento escribir en el buffer de salida.
		try {
			salidaDatos.writeUTF("0;" + usuario + ";" + bytesToHex(hash));
			// Al realizar salidaDatos.writeUTF estaria "llamando" al
			// entradaDatos.readUTF(); del servidor.
		} catch (IOException ex) {
			System.out.println("Error al intentar enviar un mensaje: " + ex.getMessage());
		}
	}

	// Para pasar la pw a sha
	private static String bytesToHex(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

	public void recibirMensajesServidor() {
		// Obtiene el flujo de entrada del socket
		DataInputStream entradaDatos = null;
		try {
			entradaDatos = new DataInputStream(socket.getInputStream());
		} catch (IOException ex) {
			System.out.println("Error al crear el stream de entrada: " + ex.getMessage());
		} catch (NullPointerException ex) {
			System.out.println("El socket no se creo correctamente. ");
		}

		// Bucle infinito que recibe mensajes del servidor
		boolean conectado = true;
		while (conectado) {
			try {
				// Chequea si hay datos provenientes del servidor a traves del socket.
				if (entradaDatos.available() != 0) {
					String mensajeRecibido = entradaDatos.readUTF();
					System.out.println(mensajeRecibido);
				}
			} catch (IOException ex) {
				System.out.println("Error al leer del stream de entrada: " + ex.getMessage());
				conectado = false;
			} catch (NullPointerException ex) {
				System.out.println("El socket no se creo correctamente. ");
				conectado = false;
			}
		}
	}

	@Override
	public void run() {
		this.recibirMensajesServidor();
	}
}