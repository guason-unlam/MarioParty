package juego.ventana;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.json.Json;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cliente.Cliente;
import cliente.Musica;
import juego.Constantes;
import juego.lobby.Usuario;

public class VentanaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3673971401919801676L;
	private JTextField username;
	private JTextField password;
	private JButton btnRegistrarse;
	private JButton btnCrearUsuario;
	public static JLabel lblEstado;
	private Musica musica;
	public static String nombreUser;

	/**
	 * Create the application.
	 */
	public VentanaLogin() {
		this.setTitle("Mario Party");

		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		JLabel usernameLabel = new JLabel("Usuario");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		usernameLabel.setToolTipText("");
		usernameLabel.setBounds(114, 133, 92, 14);
		this.getContentPane().add(usernameLabel);

		JLabel passwordLabel = new JLabel("Contrase\u00F1a");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		passwordLabel.setBounds(99, 167, 92, 14);
		this.getContentPane().add(passwordLabel);

		this.username = new JTextField();
		this.username.setToolTipText("Ingrese su usuario");
		this.username.setBounds(257, 130, 129, 20);
		this.username.setColumns(10);
		this.getContentPane().add(this.username);

		this.password = new JPasswordField();

		this.password.setToolTipText("Ingrese su contrase\u00F1a");
		this.password.setBounds(257, 161, 129, 20);
		this.password.setColumns(10);
		this.getContentPane().add(this.password);

		this.btnCrearUsuario = new JButton("Iniciar sesi\u00F3n");
		this.btnCrearUsuario.setBounds(257, 206, 129, 23);

		this.getContentPane().add(this.btnCrearUsuario);

		JLabel labelJuego = new JLabel("");
		labelJuego.setIcon(new ImageIcon(Constantes.LOGO_PATH));
		labelJuego.setFont(new Font("Tahoma", Font.BOLD, 17));
		labelJuego.setBounds(10, 11, 428, 139);
		this.getContentPane().add(labelJuego);

		this.btnRegistrarse = new JButton("Registrarse");
		this.btnRegistrarse.setBounds(77, 206, 129, 23);

		this.getContentPane().add(this.btnRegistrarse);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, Constantes.LOGIN_WIDTH, Constantes.LOGIN_HEIGHT);
		this.setLocationRelativeTo(null);
		musica = new Musica(Constantes.MUSICA_LOGIN);
		musica.loop();

		addListener();
	}

	protected void iniciarSession() throws IOException {

		if (this.username.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Falta ingresar el usuario!", "Error login",
					JOptionPane.WARNING_MESSAGE);
			this.username.setFocusable(true);
			this.password.setText("");
			return;
		}

		if (this.password.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Falta ingresar la contrase\u00F1a!", "Error login",
					JOptionPane.WARNING_MESSAGE);
			this.password.setFocusable(true);
			return;
		}

		Usuario usuario = Cliente.getConexionInterna().logear(this.username.getText(), this.password.getText());

		Cliente.getConexionServidor().enviarAlServidor(Json.createObjectBuilder()
				.add("type", Constantes.LOGIN_REQUEST_SV).add("username", this.username.getText()).build());

		if (usuario != null && usuario.getId() != -1) {
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					try {
						VentanaLobby frame = new VentanaLobby(musica);
						nombreUser = username.getText();
						frame.setVisible(true);
						dispose();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

		} else if (usuario != null && usuario.getId() == -1) {
			JOptionPane.showMessageDialog(null, "Usuario ya logeado", "Error login", JOptionPane.ERROR_MESSAGE);
			this.username.setText("");
			this.password.setText("");
			this.username.setFocusable(true);
		} else {
			JOptionPane.showMessageDialog(null, "Usted ha introducido un usuario y/o clave incorrecta", "Error login",
					JOptionPane.ERROR_MESSAGE);
			this.username.setText("");
			this.password.setText("");
			this.username.setFocusable(true);
		}
	}

	private void addListener() {

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(getContentPane(), "Desea cerrar la ventana?", "Atenci�n!",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		this.btnRegistrarse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				registrarUsuario();
			}
		});

		this.username.addActionListener(iniciarSessionPerformed());

		this.password.addActionListener(iniciarSessionPerformed());

		this.btnCrearUsuario.addActionListener(iniciarSessionPerformed());

	}

	private void registrarUsuario() {
		new VentanaRegistro(this).setVisible(true);
	}

	private ActionListener iniciarSessionPerformed() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					iniciarSession();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
	}
}
