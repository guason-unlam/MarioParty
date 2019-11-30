package juego.ventana;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cliente.Cliente;
import cliente.Musica;
import juego.Constantes;
import juego.lobby.Usuario;

public class VentanaLobby extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9099821102113802071L;
	private JButton btnEntrarEnSala;
	private JButton btnCrearSala;
	private VentanaLobby ventanaLobby;
	private Usuario usuario;
	private JButton btnHistorial;
	private Musica musica;

	public VentanaLobby(Musica musica) {
		if (musica == null) {
			musica = new Musica(Constantes.MUSICA_LOGIN);
			musica.loop();
		} else {
			this.musica = musica;

		}
		// Obtengo el usuario!
		usuario = Cliente.getConexionInterna().getUsuario();

		this.ventanaLobby = this;
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		setTitle("Mario Party");

		this.btnCrearSala = new JButton("CREAR SALA");
		btnCrearSala.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCrearSala.setForeground(Color.BLACK);
		btnCrearSala.setBackground(Color.GREEN);
		btnCrearSala.setFocusPainted(false);

		this.btnCrearSala.setBounds(164, 171, 120, 58);
		this.getContentPane().add(this.btnCrearSala);
		btnEntrarEnSala = new JButton("UNIRSE A SALA");
		btnEntrarEnSala.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnEntrarEnSala.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEntrarEnSala.setForeground(Color.BLACK);
		btnEntrarEnSala.setBackground(Color.GREEN);
		btnEntrarEnSala.setBounds(294, 171, 120, 58);
		btnEntrarEnSala.setFocusPainted(false);

		getContentPane().add(btnEntrarEnSala);

		btnHistorial = new JButton("HISTORIAL");
		btnHistorial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHistorial.setForeground(Color.BLACK);
		btnHistorial.setBackground(Color.GREEN);
		btnHistorial.setFont(new Font("Tahoma", Font.PLAIN, 11));

		btnHistorial.setBounds(34, 171, 120, 58);
		btnHistorial.setFocusPainted(false);
		getContentPane().add(btnHistorial);

		JLabel lblBienvenida = new JLabel("Hola " + usuario.getUsername() + "!");
		lblBienvenida.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblBienvenida.setBounds(34, 56, 380, 68);
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenida.setVerticalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblBienvenida);
		// Sin esto, el yes/no dialog no sirve
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setBounds(0, 0, Constantes.LOGIN_WIDTH, Constantes.LOGIN_HEIGHT);
		this.setLocationRelativeTo(null);
		addListener();

	}

	private void addListener() {

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				int opcion = JOptionPane.showConfirmDialog(getContentPane(), "Desea cerrar la ventana?", "Atención!",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (opcion == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		btnCrearSala.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaCrearSala ventanaCrearSala = new VentanaCrearSala(ventanaLobby, musica);

				// Muestro la ventana de unir sala
				ventanaCrearSala.setVisible(true);

				// Oculto la sala actual
				ventanaLobby.setVisible(false);

			}
		});

		btnHistorial.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String datosEstadisticos = Cliente.getConexionInterna().obtenerEstadisticas(usuario.getUsername());
				String datosTabla = Cliente.getConexionInterna().obtenerEstadisticasTabla(usuario.getUsername());

				if (datosEstadisticos != null && datosTabla != null) {
					Map<String, Object> estadisticas = new Gson().fromJson(datosEstadisticos,
							new TypeToken<HashMap<String, Object>>() {
							}.getType());
					JsonReader jsonReader = Json.createReader(new StringReader(datosTabla));
					JsonObject entradaJson = jsonReader.readObject();
					JsonArray datosTablaFormateados = entradaJson.getJsonArray("datosHistorial");
					VentanaHistorial ventanaHistorial = new VentanaHistorial(estadisticas, datosTablaFormateados,
							ventanaLobby);

					// Muestro la ventana de historial
					ventanaHistorial.setVisible(true);

					// Oculto la sala actual
					ventanaLobby.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Ocurrio un error, por favor intentelo nuevamente.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnEntrarEnSala.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				abrirVentanaUnirSala();

			}
		});

	}

	protected void abrirVentanaUnirSala() {
		VentanaElegirSala ventanaUnirSala = new VentanaElegirSala(this);
		Coordinador.setVentanaUnirSala(ventanaUnirSala);

		JsonObject paqueteIngresoVentanaUnirSala = Json.createObjectBuilder().add("type", Constantes.INDEX_ROOM_REQUEST)
				.build();

		// Le aviso al sv que me actualice las salas, el cliente se las auto-actualiza
		Cliente.getConexionServidor().enviarAlServidor(paqueteIngresoVentanaUnirSala);

		ventanaUnirSala.setVisible(true);
		this.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String comStr = ae.getActionCommand();

		if (comStr == "Salir") {
			int opcion = JOptionPane.showConfirmDialog(getContentPane(), "Desea cerrar la ventana?", "Atención!",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			System.out.println(opcion);
			if (opcion == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}

		System.out.println(comStr + " Selected");
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JButton getBtnEntrarEnSala() {
		return btnEntrarEnSala;
	}

	public JButton getBtnCrearSala() {
		return btnCrearSala;
	}

	public JFrame getVentanaLobby() {
		return ventanaLobby;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public JButton getBtnHistorial() {
		return btnHistorial;
	}

	public Musica getMusica() {
		return musica;
	}
}
