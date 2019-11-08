package juego.ventana.minijuego;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.json.JsonArray;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Musica;
import juego.Constantes;
import juego.ventana.Coordinador;

public class VentanaResultadosFinal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8704958894111944322L;
	private Musica musica;
	private JPanel panel;
	private JButton btnVolver;
	private JLabel logo;

	public VentanaResultadosFinal(JsonArray datosDeFinMinijuego, JFrame instrucciones, Musica musica) {
		if (musica != null) {
			// Freno la musica anterior
			musica.stop();
		}

		this.musica = new Musica(Constantes.MUSICA_WIN);
		this.musica.loop();

		setTitle("Ganadores del minijuego");
		setBounds(0, 0, 456, 400);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnVolver.setBounds(10, 314, 101, 46);
		panel.add(btnVolver);

		JLabel puesto1Label = new JLabel("Puesto 1");
		puesto1Label.setFont(new Font("Tahoma", Font.PLAIN, 19));
		puesto1Label.setBounds(28, 120, 83, 46);
		panel.add(puesto1Label);

		JLabel puesto2Label = new JLabel("Puesto 2");
		puesto2Label.setFont(new Font("Tahoma", Font.PLAIN, 19));
		puesto2Label.setBounds(28, 177, 124, 46);
		panel.add(puesto2Label);

		JLabel puesto3Label = new JLabel("Puesto 3");
		puesto3Label.setFont(new Font("Tahoma", Font.PLAIN, 19));
		puesto3Label.setBounds(28, 234, 124, 46);
		panel.add(puesto3Label);

		logo = new JLabel("");
		logo.setIcon(new ImageIcon(Constantes.LOGO_PATH));
		logo.setFont(new Font("Tahoma", Font.BOLD, 17));
		logo.setBounds(10, 0, 428, 139);
		this.getContentPane().add(logo);
		JLabel puesto1WinnerLabel = new JLabel("");
		puesto1WinnerLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		puesto1WinnerLabel.setBounds(148, 120, 275, 46);

		if (datosDeFinMinijuego.size() > 0) {
			puesto1WinnerLabel.setText(datosDeFinMinijuego.getJsonObject(0).getString("nombre") + " - "
					+ String.valueOf(datosDeFinMinijuego.getJsonObject(0).getInt("puntos")) + "ptos.");
		} else {
			puesto1WinnerLabel.setText("-");
		}
		panel.add(puesto1WinnerLabel);

		JLabel puesto2WinnerLabel = new JLabel("");
		puesto2WinnerLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		puesto2WinnerLabel.setBounds(148, 177, 275, 46);
		if (datosDeFinMinijuego.size() > 1) {
			puesto2WinnerLabel.setText(datosDeFinMinijuego.getJsonObject(1).getString("nombre") + " - "
					+ String.valueOf(datosDeFinMinijuego.getJsonObject(1).getInt("puntos")) + "ptos.");
		} else {
			puesto2WinnerLabel.setText("-");
		}
		panel.add(puesto2WinnerLabel);

		JLabel puesto3WinnerLabel = new JLabel("");
		puesto3WinnerLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		puesto3WinnerLabel.setBounds(148, 234, 275, 46);
		if (datosDeFinMinijuego.size() > 2) {
			puesto3WinnerLabel.setText(datosDeFinMinijuego.getJsonObject(2).getString("nombre") + " - "
					+ String.valueOf(datosDeFinMinijuego.getJsonObject(2).getInt("puntos")) + "ptos.");
		} else {
			puesto3WinnerLabel.setText("-");
		}
		panel.add(puesto3WinnerLabel);

		this.addListener();
	}

	private void addListener() {
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Vuelvo a la ventana principal
				// Por ahora no esta seteado, falta estadio intermedio
				Coordinador.getVentanaJuego().setVisible(true);
				// Lo oculto
				setVisible(false);
				// Cierro
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
}
