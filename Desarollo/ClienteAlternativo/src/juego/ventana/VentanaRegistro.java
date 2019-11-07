package juego.ventana;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cliente.Cliente;
import juego.Constantes;
import servidor.Message;

public class VentanaRegistro extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3407065648836821113L;
	private JTextField username;
	private JTextField password;
	private JTextField confirmPassword;
	private JButton btnCrearUsuario;
	private JButton btnVolver;
	private JFrame pantallaLogin;

	public VentanaRegistro(JFrame pantallaLogin) {

		this.pantallaLogin = pantallaLogin;
		pantallaLogin.setVisible(false);

		this.setTitle("Registro | Mario Party");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, Constantes.LOGIN_WIDTH, Constantes.LOGIN_HEIGHT + 75);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		this.setResizable(false);

		JLabel usernameLabel = new JLabel("Usuario");
		usernameLabel.setBounds(53, 161, 122, 14);
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(usernameLabel);

		JLabel passwordLabel = new JLabel("Contrase\u00F1a");
		passwordLabel.setBounds(53, 192, 132, 14);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(passwordLabel);

		JLabel confirmPasswordLabel = new JLabel("Confirmar contrase\u00F1a");
		confirmPasswordLabel.setBounds(53, 223, 188, 14);
		confirmPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		getContentPane().add(confirmPasswordLabel);

		this.username = new JTextField();

		this.username.setToolTipText("Ingrese el usuario que desee aqu\u00ED . Solo pueden contener letras y numeros.");
		this.username.setBounds(271, 161, 100, 20);
		getContentPane().add(username);
		this.username.setColumns(10);

		this.password = new JPasswordField();

		this.password
				.setToolTipText("Ingrese la contrase\u00F1a que desee aqui. Solo pueden contener letras y numeros.");
		this.password.setBounds(271, 192, 100, 20);
		getContentPane().add(password);
		this.password.setColumns(10);

		this.confirmPassword = new JPasswordField();

		this.confirmPassword.setToolTipText("Repita la contrase\u00F1a nuevamente.");
		this.confirmPassword.setBounds(271, 223, 100, 20);
		getContentPane().add(confirmPassword);
		this.confirmPassword.setColumns(10);

		this.btnCrearUsuario = new JButton("Crear Cuenta");
		this.btnCrearUsuario.setBounds(242, 281, 129, 23);
		getContentPane().add(btnCrearUsuario);

		this.btnVolver = new JButton("Volver");
		this.btnVolver.setBounds(53, 281, 129, 23);
		getContentPane().add(btnVolver);
		JLabel labelJuego = new JLabel("");
		labelJuego.setIcon(new ImageIcon(Constantes.LOGO_PATH));
		labelJuego.setFont(new Font("Tahoma", Font.BOLD, 17));
		labelJuego.setBounds(10, 11, 428, 139);
		this.getContentPane().add(labelJuego);
		addListener();
	}

	protected void registrarUsuario(JFrame ventanaLogin) throws IOException {

		if (this.password.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese una contrase\u00F1a", "Error", JOptionPane.WARNING_MESSAGE);
			this.password.setFocusable(true);
			this.confirmPassword.setFocusable(true);
			this.confirmPassword.setText("");
			return;
		}

		if (this.confirmPassword.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Confirme la contrase\u00F1a", "Error", JOptionPane.WARNING_MESSAGE);
			this.username.setFocusable(true);
			this.confirmPassword.setFocusable(true);
			this.password.setFocusable(true);
			this.password.setText("");
			this.confirmPassword.setText("");
			return;
		}

		if (this.username.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese el nombre de usuario", "Error", JOptionPane.WARNING_MESSAGE);
			this.username.setFocusable(true);
			this.confirmPassword.setFocusable(true);
			this.password.setFocusable(true);
			this.password.setText("");
			this.confirmPassword.setText("");
			return;
		}

		if (!this.username.getText().matches("[a-zA-Z0-9]+")) {
			JOptionPane.showMessageDialog(null, "Los nombres de usuario solo pueden contener letras y numeros.",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			this.username.setText("");
			this.confirmPassword.setFocusable(true);
			this.password.setFocusable(true);
			this.password.setText("");
			this.confirmPassword.setText("");
			this.username.setFocusable(true);
			return;
		}

		if (!this.password.getText().equals(this.confirmPassword.getText())) {
			JOptionPane.showMessageDialog(null, "Las contrase\u00F1as no coinciden, por favor intentelo nuevamente.",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			this.username.setText("");
			this.password.setText("");
			this.confirmPassword.setText("");
			this.username.setFocusable(true);
			this.confirmPassword.setFocusable(true);
			this.password.setFocusable(true);
			return;
		}

		Message message = Cliente.getConexionInterna().registrar(this.username.getText(), this.password.getText());

		switch (message.getType()) {
		case Constantes.REGISTER_INCORRECT:
			JOptionPane.showMessageDialog(null, "Registro incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
			this.username.setText("");
			this.password.setText("");
			this.confirmPassword.setText("");
			this.username.setFocusable(true);
			break;

		case Constantes.REGISTER_CORRECT:
			JOptionPane.showMessageDialog(null, "Registro correcto", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			ventanaLogin.setVisible(true);
			break;

		case Constantes.REGISTER_DUPLICATED:
			JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe", "Aviso", JOptionPane.WARNING_MESSAGE);
			this.username.setText("");
			this.password.setText("");
			this.confirmPassword.setText("");
			this.username.setFocusable(true);
			break;
		}
	}

	private void addListener() {

		btnCrearUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					registrarUsuario(pantallaLogin);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				pantallaLogin.setVisible(true);
			}
		});
	}
}
