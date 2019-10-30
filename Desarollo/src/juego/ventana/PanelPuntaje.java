package juego.ventana;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelPuntaje extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6006360571206851380L;
	private JTable tableSalas;

	public PanelPuntaje(int x, int y) {
		this.setBounds(x, y, 163, 110);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 6, 143, 99);
		this.add(scrollPane);

		tableSalas = new JTable();
		scrollPane.setViewportView(tableSalas);
		setBackground(Color.BLACK);
		setLayout(null);
	}
}
