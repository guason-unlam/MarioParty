package juego.ventana;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaAdministracionSala extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3071454957161090149L;
	private JFrame lobby;
	private JPanel panel;
	private JButton btnJoin;
	private JButton btnVolver;
	private JLabel lblNewLabel;

	public VentanaAdministracionSala(JFrame ventanaLobby, String nombreSala, boolean esAdmin) {

		// Me guardo la referencia para hacerlo visible, etc
		this.lobby = ventanaLobby;

		setTitle("Salas disponibles");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setBounds(0, 0, 456, 400);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		btnJoin = new JButton("Entrar!");
		btnJoin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnJoin.setBounds(329, 314, 111, 46);
		panel.add(btnJoin);
		setLocationRelativeTo(this.lobby);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnVolver.setBounds(10, 314, 111, 46);
		panel.add(btnVolver);
		
		lblNewLabel = new JLabel(nombreSala);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(75, 102, 285, 106);
		panel.add(lblNewLabel);

		this.addListener();
	}

	private void addListener() {
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

}
