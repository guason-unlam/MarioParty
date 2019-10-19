package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import hibernate.Usuario;

public class Cliente extends Thread {
	private Socket socket;
	private DataInputStream entrada;
	private DataOutputStream salida;
	private static Session session;

	public Cliente(Socket socket, Session session) {
		this.socket = socket;
		Cliente.session = session;
		try {
			entrada = new DataInputStream(socket.getInputStream());

			salida = new DataOutputStream(socket.getOutputStream());

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
				System.out.println(tipoMensaje);
				// Intento de login.
				if (tipoMensaje == 0) {
					String usuario = recepcion[1];
					String contrasena = recepcion[2];
					boolean resultado = login(usuario, contrasena);

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

	public static boolean login(String usuario, String contrasena) {
		boolean acceso = false;
		Query q = session.createQuery(
				"SELECT p.id FROM Usuario p WHERE user = '" + usuario + "' AND password = '" + contrasena + "'");
		List<Usuario> listaDePersonas = q.getResultList();
		if (listaDePersonas.size() > 0) {
			System.out.println("Usuario: " + usuario + " logeado");
			acceso = true;
		}

		return acceso;
	}
}
