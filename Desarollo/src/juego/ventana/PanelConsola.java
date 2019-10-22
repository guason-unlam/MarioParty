package juego.ventana;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JTextPane;

public class PanelConsola extends JPanel {

	/**
	 * Create the panel.
	 */
	
	private JTextPane textPane;
	private JScrollPane scroller;

	
	public PanelConsola(int x,int y) {
		setBackground(Color.PINK);
		setLayout(null);
		this.setBounds(x, y, 400, 110);
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(5, 5, this.getWidth()-10, this.getHeight()-10);
		scroller = new JScrollPane(textPane);
		scroller.setBounds(5, 5, this.getWidth()-10, this.getHeight()-10);
		this.add(scroller);
//		this.add(textPane);

	}
	
	public void agregarTexto(String texto) {
		this.textPane.setText(this.textPane.getText()+"\n"+texto);
	}
}
