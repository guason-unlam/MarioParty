package juego.ventana;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class VentanaHistorial extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 295124723881461720L;
	private JFrame lobby;
	private JPanel panel;
	private JButton btnVolver;
	private JTable tableSalas;

	public VentanaHistorial(JFrame lobby) {
		// Me guardo la referencia para hacerlo visible, etc
		this.lobby = lobby;

		setTitle("Historial de partidas");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setBounds(0, 0, 456, 400);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnVolver.setBounds(10, 314, 111, 46);
		panel.add(btnVolver);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 430, 280);
		panel.add(scrollPane);

		tableSalas = new JTable();
		scrollPane.setViewportView(tableSalas);

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
