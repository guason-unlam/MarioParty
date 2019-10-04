package juego.ventana;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import juego.Constantes;
import java.awt.Panel;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class VentanaJuego extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3849520346687736542L;

	public VentanaJuego() {
		setTitle("Mario Party");

		this.setBounds(0, 0, Constantes.VENTANA_WIDTH, Constantes.VENTANA_HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);

		JPanel panelJuego = new JPanel();
		panelJuego.setBounds(103, 21, Constantes.MAPA_WIDTH, Constantes.MAPA_HEIGHT);
		getContentPane().add(panelJuego);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Constantes.TABLERO1_PATH));

		lblNewLabel.setBounds(54, 21, Constantes.MAPA_WIDTH, Constantes.MAPA_HEIGHT);
		panelJuego.add(lblNewLabel);

	}
}
