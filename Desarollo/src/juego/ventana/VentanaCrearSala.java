package juego.ventana;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import cliente.Musica;
import juego.Constantes;

public class VentanaCrearSala extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3252807040559842501L;
	private JFrame lobby;
	private JPanel panel;
	private JButton btnCrear;
	private JButton btnVolver;
	private JTextField textFieldSlots;
	private JPasswordField textFieldPassword;
	private JTextField textFieldName;
	private JLabel logo;
	private JLabel lb;
	int flagPw = 0;
	private VentanaAdministracionSala ventanaAdministracionSala;
	private Musica musica;

	public VentanaCrearSala(JFrame ventanaLobby, Musica musica) {
		this.musica = musica;
		// Me guardo la referencia para hacerlo visible, etc
		this.lobby = ventanaLobby;

		setTitle("Crear Sala");
		setBounds(0, 0, 456, 400);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		btnCrear = new JButton("Crear");
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnCrear.setBounds(339, 314, 101, 46);
		panel.add(btnCrear);
		setLocationRelativeTo(this.lobby);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnVolver.setBounds(10, 314, 101, 46);
		panel.add(btnVolver);

		JLabel lblName = new JLabel("Nombre");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblName.setBounds(28, 120, 83, 46);
		panel.add(lblName);

		JLabel lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblPassword.setBounds(28, 177, 124, 46);
		panel.add(lblPassword);

		JLabel lblSlots = new JLabel("Capacidad");
		lblSlots.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSlots.setBounds(28, 234, 124, 46);
		panel.add(lblSlots);

		textFieldSlots = new JTextField();
		textFieldSlots.setBounds(317, 251, 46, 20);
		panel.add(textFieldSlots);
		textFieldSlots.setColumns(10);

		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(275, 194, 124, 20);
		panel.add(textFieldPassword);
		textFieldPassword.setColumns(10);

		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(275, 137, 124, 20);
		panel.add(textFieldName);

		logo = new JLabel("");
		logo.setIcon(new ImageIcon(Constantes.LOGO_PATH));
		logo.setFont(new Font("Tahoma", Font.BOLD, 17));
		logo.setBounds(10, 0, 428, 139);
		this.getContentPane().add(logo);
		lb = new JLabel("");
		lb.setIcon(new ImageIcon(
				new ImageIcon(Constantes.EYE_ICON).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
		lb.setFont(new Font("Tahoma", Font.BOLD, 17));
		lb.setBounds(409, 189, 32, 32);
		lb.setVisible(false);

		this.getContentPane().add(lb);

		this.addListener();
	}

	private void addListener() {
		btnCrear.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					crearSala();
				}
			}
		});

		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearSala();
			}
		});
		textFieldPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					crearSala();
				}
				if (textFieldPassword.getPassword().length > 0) {
					lb.setVisible(true);
				} else {
					lb.setVisible(false);
					flagPw = 0;
					textFieldPassword.setEchoChar('*');
				}
			}
		});

		textFieldName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					crearSala();
				}
			}
		});

		textFieldSlots.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					crearSala();
				}
			}
		});
		// Listener que se encarga de mostrar u ocultar la contraseña
		lb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (flagPw == 0) {
					textFieldPassword.setEchoChar((char) 0);
					flagPw = 1;
				} else {
					textFieldPassword.setEchoChar('*');
					flagPw = 0;
				}
			}
		});
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lobby.setVisible(true);
				// Lo oculto, puede ser de utilidad luego
				setVisible(false);
			}
		});

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(getContentPane(), "Desea cerrar la ventana?", "Atencion!",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

	}

	private void crearSala() {
		// Primero hago todas las validaciones correspondientes
		if (this.textFieldName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Inserte un nombre de sala", "Atención", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (this.textFieldSlots.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "La cantidad de usuarios no puede ser nula", "Atención",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (Integer.valueOf(this.textFieldSlots.getText()) < 2
				|| Integer.valueOf(this.textFieldSlots.getText()) > 100) {
			JOptionPane.showMessageDialog(null, "La cantidad de usuarios máximos debe ser entre 2 y 100", "Atención",
					JOptionPane.WARNING_MESSAGE);
			// Reseteo el campo
			this.textFieldSlots.setText("");
			return;
		}
		if (this.textFieldPassword.getPassword().length > 0
				&& !(String.valueOf(this.textFieldPassword.getPassword())).matches("[a-zA-Z0-9]+")) {
			JOptionPane.showMessageDialog(null, "Ingrese una contraseña alfanumerica", "Atención",
					JOptionPane.WARNING_MESSAGE);
			// Reseteo el campo
			this.textFieldPassword.setText("");
			return;
		}
		// Me fijo con una regex si estoy cumpliendo con la regla de letras y numeros
		if (this.textFieldName.getText().matches("[a-zA-Z0-9]+")) {

			ArrayList<String> datosSala = new ArrayList<String>();

			datosSala.add(this.textFieldName.getText());
			datosSala.add(String.valueOf(this.textFieldPassword.getPassword()));
			datosSala.add(this.textFieldSlots.getText());

			if (Cliente.getConexionInterna().crearSala(datosSala)) {
				// El boolean es para indicar que se trata de un ADMIN
				// Cuando me uno, esto es falso, entoncs no le permito la edicion de la sala
				// Tampoco puedo perder la referencia al menu anterior!
				// Con ese me "muevo" por todos lados
				this.ventanaAdministracionSala = new VentanaAdministracionSala(this.lobby, this.textFieldName.getText(),
						true);

				this.ventanaAdministracionSala.setVisible(false);
				JsonObject paqueteCrearSala = Json.createObjectBuilder().add("type", Constantes.CREATE_ROOM_SV_REQUEST)
						.add("sala", datosSala.get(0)).build();
				Cliente.getConexionServidor().enviarAlServidor(paqueteCrearSala);
				musica.stop();
				this.ventanaAdministracionSala.setVisible(true);
				Coordinador.setVentanaAdministracionSala(ventanaAdministracionSala);

				this.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Error al crear sala", "Error", JOptionPane.WARNING_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(null, "El nombre de la sala solamente puede contener letras y numeros.",
					"Atención", JOptionPane.WARNING_MESSAGE);

			this.textFieldName.setText("");
		}
	}
}
