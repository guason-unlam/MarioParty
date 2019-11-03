package juego.ventana;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Color;
import javax.swing.JTextPane;

public class PanelConsola extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6310865210047016874L;
	/**
	 * Create the panel.
	 */

	private JTextArea textArea;
	private JScrollPane scroller;

	public PanelConsola(int x, int y) {
		setBackground(Color.BLACK);
		setLayout(null);
		this.setBounds(x, y, 350, 110);
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(5, 5, this.getWidth() - 10, this.getHeight() - 10);
		scroller = new JScrollPane(textArea);
		scroller.setBounds(5, 5, this.getWidth() - 10, this.getHeight() - 10);
		this.add(scroller);

	}

	public void agregarTexto(String texto) {
		this.textArea.append("\n"+texto);
	}
}
