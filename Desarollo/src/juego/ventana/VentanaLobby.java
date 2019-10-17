package juego.ventana;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
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
import juego.lobby.Partida;
import juego.lobby.Usuario;

import java.awt.Color;

public class VentanaLobby extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9099821102113802071L;
	private JButton btnSalir;
	private JButton btnEmpezar;
	private JMenuBar javaMenuBar = null;
	private JMenu jmFile = null;
	private JMenu jmOptions = null;
	private Usuario usuario;

	public VentanaLobby() {
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

		this.btnEmpezar = new JButton("INICIAR PARTIDA");
		btnEmpezar.setForeground(Color.BLACK);
		btnEmpezar.setBackground(Color.GREEN);

		this.btnEmpezar.setBounds(121, 66, 194, 84);
		this.getContentPane().add(this.btnEmpezar);

		this.btnSalir = new JButton("Salir");
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.RED);
		this.btnSalir.setBounds(257, 206, 129, 23);
		getContentPane().add(this.btnSalir);
		

		// Sin esto, el yes/no dialog no sirve
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setBounds(0, 0, Constantes.LOGIN_WIDTH, Constantes.LOGIN_HEIGHT);
		this.setLocationRelativeTo(null);
		addListener();

		// Voy a crear mi usuario
		// Por ahora hardcodeado
		usuario = new Usuario("admin", "admin");
		//Creo la sala
		usuario.crearSala();
		
		//Seteo el nombre
		JLabel nombreSala = new JLabel(usuario.getSala().getNombre());
		nombreSala.setBounds(10, 11, 46, 14);
		getContentPane().add(nombreSala);

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

		this.btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int opcion = JOptionPane.showConfirmDialog(getContentPane(), "Desea cerrar la ventana?", "Atención!",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (opcion == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			
			}
		});

		btnEmpezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Aca debo iniciar la partida
				//Por ahora es un single player
				Partida p = new Partida(usuario.getSala().getUsuariosActivos(),1);
				new VentanaJuego(p).setVisible(true);
				setVisible(false);

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
