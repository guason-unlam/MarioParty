package juego.ventana;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import juego.Constantes;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class VentanaLoginUsuario extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9099821102113802071L;
	private JTextField username;
	private JTextField password;
	private JButton btnRegistrarse;
	private JButton btnCrearUsuario;

	public VentanaLoginUsuario() {
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		setTitle("Mario Party");

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

		this.username.setToolTipText("Ingrese su usuario aqu\u00ED. Maximo 20 caracteres.");
		this.username.setBounds(257, 130, 129, 20);
		this.username.setColumns(10);
		this.getContentPane().add(this.username);

		this.password = new JPasswordField();

		this.password.setToolTipText("Ingrese su contrase\u00F1a aqu\u00ED. Maximo 10 caracteres.");
		this.password.setBounds(257, 161, 129, 20);
		this.password.setColumns(10);
		this.getContentPane().add(this.password);

		this.btnCrearUsuario = new JButton("Iniciar Sesi\u00F3n");

		this.btnCrearUsuario.setBounds(77, 206, 129, 23);
		this.getContentPane().add(this.btnCrearUsuario);

		JLabel labelJuego = new JLabel("");
		labelJuego.setIcon(new ImageIcon(Constantes.LOGO_PATH));
		labelJuego.setFont(new Font("Tahoma", Font.BOLD, 17));
		labelJuego.setBounds(10, 11, 428, 139);
		this.getContentPane().add(labelJuego);

		this.btnRegistrarse = new JButton("Registrarse");
		this.btnRegistrarse.setBounds(257, 206, 129, 23);
		getContentPane().add(this.btnRegistrarse);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, Constantes.LOGIN_WIDTH, Constantes.LOGIN_HEIGHT);
		this.setLocationRelativeTo(null);
		addListener();

	}

	private void addListener() {

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(getContentPane(), "Desea cerrar la ventana?", "Atención!",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		this.btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// registrar();
			}
		});
		
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaJuego().setVisible(true);
			}
		});
	}

	public static void main(String[] args) {
		new VentanaLoginUsuario().setVisible(true);
	}
}
