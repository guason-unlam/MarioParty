package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente extends Thread {
	private Socket socket;
	private DataInputStream entrada;
	private DataOutputStream salida;

	public Cliente(Socket socket) {
		this.socket = socket;
		try {
			entrada = new DataInputStream(socket.getInputStream());

			salida = new DataOutputStream(socket.getOutputStream());
			System.out.println(entrada.readUTF());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		String mensajeRecibido;
		boolean conectado = true;

		while (conectado) {
			try {
				// Lee un mensaje enviado por el cliente
				mensajeRecibido = entrada.readUTF();
				String[] recepcion = mensajeRecibido.split(";");
				int tipoMensaje = Integer.valueOf(recepcion[0]);
				// Intento de login.
				if (tipoMensaje == 0) {
					String usuario = recepcion[1];
					String contrasena = recepcion[2];

					boolean resultado = true;
					salida.writeUTF("0;" + ((resultado) ? "ok" : "nok"));
				}
			} catch (IOException ex) {
				String mensaje = "Cliente con la IP " + socket.getInetAddress().getHostName() + " desconectado.";
				System.out.println(mensaje);
				conectado = false;

				try {
					entrada.close();
					salida.close();
				} catch (IOException ex2) {
					String mensajeError2 = "Error al cerrar los stream de entrada y salida :" + ex2.getMessage();
					System.out.println(mensajeError2);
				}
			}
		}
	}
}
