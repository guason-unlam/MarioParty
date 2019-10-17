package juego.ventana;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import juego.Constantes;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class VentanaLoginUsuario extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9099821102113802071L;
	private JTextField username;
	private JTextField password;
	private JButton btnRegistrarse;
	private JButton btnCrearUsuario;
	private JMenuBar javaMenuBar = null;
	private JMenu jmFile = null;
	private JMenu jmOptions = null;

	public VentanaLoginUsuario() {
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		setTitle("Mario Party");
		javaMenuBar = new JMenuBar();

		jmFile = new JMenu("Archivo");

		// Se crean los items de la pantalla
		JMenuItem jmiOpen = new JMenuItem("Abrir");
		JMenuItem jmiClose = new JMenuItem("Cerrar");
		JMenuItem jmiSave = new JMenuItem("Guardar");
		JMenuItem jmiExit = new JMenuItem("Salir");

		// Se agregan los items al archivo
		jmFile.add(jmiOpen);
		jmFile.add(jmiClose);
		jmFile.add(jmiSave);
		jmFile.addSeparator();
		jmFile.add(jmiExit);
		javaMenuBar.add(jmFile);

		jmOptions = new JMenu("Opciones");
		JMenu a = new JMenu("A");
		JMenuItem b = new JMenuItem("B");
		JMenuItem c = new JMenuItem("C");
		JMenuItem d = new JMenuItem("D");
		a.add(b);
		a.add(c);
		a.add(d);
		jmOptions.add(a);

		JMenu e = new JMenu("E");
		e.add(new JMenuItem("F"));
		e.add(new JMenuItem("G"));
		jmOptions.add(e);

		javaMenuBar.add(jmOptions);

		JMenu jmHelp = new JMenu("Ayuda");
		JMenuItem jmiHelp = new JMenuItem("Ayuda de Mario Party");
		jmHelp.add(jmiHelp);
		javaMenuBar.add(jmHelp);

		jmiOpen.addActionListener(this);
		jmiClose.addActionListener(this);
		jmiSave.addActionListener(this);
		jmiExit.addActionListener(this);
		b.addActionListener(this);
		c.addActionListener(this);
		d.addActionListener(this);
		jmiHelp.addActionListener(this);

		this.setJMenuBar(javaMenuBar);
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

		// Sin esto, el yes/no dialog no sirve
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

		this.btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});

		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaLobby().setVisible(true);
				//Sigue corriendo en background, deberia cerrarlo?
				setVisible(false);
			}
		});
	}

	public static void main(String[] args) {
		new VentanaLoginUsuario().setVisible(true);
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
		} else if (comStr == "Ayuda de Mario Party") {
			new Ayuda().setVisible(true);
		}

		System.out.println(comStr + " Selected");
	}
}
