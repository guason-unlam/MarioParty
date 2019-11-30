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

public class VentanaSalaPrivada extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3252807040559842501L;
	private VentanaElegirSala ventanaElegirSala;
	private JPanel panel;
	private JButton btnEntrar;
	private JButton btnVolver;
	private JPasswordField textFieldPassword;
	private JTextField textFieldName;
	private JLabel logo;
	private JLabel lb;
	int flagPw = 0;

	public VentanaSalaPrivada(VentanaElegirSala ventana, String nombreSala) {
		// Me guardo la referencia para hacerlo visible, etc
		this.ventanaElegirSala = (VentanaElegirSala) ventana;

		setTitle("Ingresar a sala privada");
		setBounds(0, 0, 456, 350);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		btnEntrar = new JButton("Entrar!");
		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnEntrar.setBounds(326, 264, 101, 46);
		panel.add(btnEntrar);
		setLocationRelativeTo(this.ventanaElegirSala);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnVolver.setBounds(10, 264, 101, 46);
		panel.add(btnVolver);

		JLabel lblName = new JLabel("Nombre");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblName.setBounds(28, 120, 83, 46);
		panel.add(lblName);

		JLabel lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblPassword.setBounds(28, 177, 124, 46);
		panel.add(lblPassword);

		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(275, 194, 124, 20);
		panel.add(textFieldPassword);
		textFieldPassword.setColumns(10);

		textFieldName = new JTextField();
		textFieldName.setEditable(false);
		textFieldName.setEnabled(false);
		textFieldName.setText(nombreSala);
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
		btnEntrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					entrarEnSala();
				}
			}
		});

		btnEntrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				entrarEnSala();
			}
		});
		textFieldPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					entrarEnSala();
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
					entrarEnSala();
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
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ventanaElegirSala.setVisible(true);
				dispose();
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

	private void entrarEnSala() {
		if (this.textFieldPassword.getPassword().length > 0
				&& !(String.valueOf(this.textFieldPassword.getPassword())).matches("[a-zA-Z0-9]+")) {
			JOptionPane.showMessageDialog(null, "Ingrese una contraseña alfanumerica", "Atención",
					JOptionPane.WARNING_MESSAGE);
			// Reseteo el campo
			this.textFieldPassword.setText("");
			return;
		}

		this.ventanaElegirSala.setearPw(this.textFieldName.getText(),
				String.valueOf(this.textFieldPassword.getPassword()));
		dispose();

	}
}
