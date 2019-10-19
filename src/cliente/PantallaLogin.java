package cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3673971401919801676L;
	private JFrame frame;
	private JTextField textUsuario;
	private JPasswordField textPassword;
	private Socket socket;
	public static JLabel lblEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaLogin window = new PantallaLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaLogin() {
		// Nombre de la ventana.
		super("Login");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(12, 25, 70, 15);
		frame.getContentPane().add(lblUsuario);

		JLabel lblContrasena = new JLabel("Contrasena");
		lblContrasena.setBounds(12, 89, 83, 15);
		frame.getContentPane().add(lblContrasena);

		textUsuario = new JTextField();
		textUsuario.setBounds(117, 23, 114, 19);
		frame.getContentPane().add(textUsuario);
		textUsuario.setColumns(10);

		textPassword = new JPasswordField();
		textPassword.setBounds(113, 87, 118, 19);
		frame.getContentPane().add(textPassword);

		// Se crea el socket para conectar con el Servidor del juego.
		try {
			this.socket = new Socket("0.0.0.0", 7777);
		} catch (UnknownHostException ex) {
			System.out.println("No se ha podido conectar con el servidor (" + ex.getMessage() + ").");
		} catch (IOException ex) {
			System.out.println("No se ha podido conectar con el servidor (" + ex.getMessage() + ").");
		}
		// Creo la conexion al servidor.
		ConexionServidor con = new ConexionServidor(socket);
		// La conexion al servidor se ejecuta paralelamente en otro thread.
		Thread escucha = new Thread(con);
		escucha.start();

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = textUsuario.getText();
				String contrasena = String.valueOf(textPassword.getPassword());
				// Intento logear.
				con.logear(usuario, contrasena);
			}
		});
		btnIngresar.setBounds(12, 221, 117, 25);
		frame.getContentPane().add(btnIngresar);

		lblEstado = new JLabel("");
		lblEstado.setBounds(12, 245, 426, 15);
		frame.getContentPane().add(lblEstado);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(321, 221, 117, 25);
		frame.getContentPane().add(btnRegistrar);
		// Clickear sobre el boton registrar.
//		btnRegistrar.addMouseListener(new MouseAdapter() 
//		{
//			@Override
//			public void mouseClicked(MouseEvent e) 
//			{
//				//Encripto ambos campos.
//				String usuario = textUsuario.getText();
//				String contrasena = String.valueOf(textPassword.getPassword();
//				//Intento registrar.
//				con.registrar(usuario, contrasena);
//			}
//		});
	}
}
