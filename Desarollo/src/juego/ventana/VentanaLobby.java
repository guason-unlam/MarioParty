package juego.ventana;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.json.Json;
import javax.json.JsonObject;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import cliente.Cliente;
import juego.Constantes;

public class VentanaLobby extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9099821102113802071L;
	private JMenuBar javaMenuBar = null;
	private JMenu jmFile = null;
	private JMenu jmOptions = null;
	private JButton btnEntrarEnSala;
	private JButton btnCrearSala;
	private JFrame ventanaLobby;

	public VentanaLobby() {
		this.ventanaLobby = this;
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

		this.btnCrearSala = new JButton("CREAR SALA");
		btnCrearSala.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnCrearSala.setForeground(Color.BLACK);
		btnCrearSala.setBackground(Color.GREEN);

		this.btnCrearSala.setBounds(124, 38, 194, 66);
		this.getContentPane().add(this.btnCrearSala);
		btnEntrarEnSala = new JButton("UNIRSE A SALA");
		btnEntrarEnSala.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnEntrarEnSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEntrarEnSala.setForeground(Color.BLACK);
		btnEntrarEnSala.setBackground(Color.GREEN);
		btnEntrarEnSala.setBounds(124, 113, 194, 66);
		getContentPane().add(btnEntrarEnSala);
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

		btnCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Crear sala
				// setVisible(false);

			}
		});
		btnEntrarEnSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Voy al menu que selecciona las salas
				VentanaElegirSala ventanaUnirSala = new VentanaElegirSala(ventanaLobby);

				// Creo el objeto
				JsonObject listarSalasRequest = Json.createObjectBuilder().add("type", Constantes.INDEX_SALAS).build();

				// Hago el request
				Cliente.getconexionServidor().enviarAlServidor(listarSalasRequest);

				// Muestro la ventana de unir sala
				ventanaUnirSala.setVisible(true);

				// Oculto la sala actual
				ventanaLobby.setVisible(false);

			}
		});

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
