package juego.ventana;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Ayuda extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -544962718198408492L;
	private static final Border Border = null;
	JMenuBar javaMenuBar = null;
	JMenu jmFile = null;
	JMenu jmOptions = null;
	Icon upIcon = new ImageIcon("31838.png");

	public Ayuda() {

		// Se crea la pantalla con su tamaño
		this.setResizable(false);
		this.setSize(640, 480);

		Container content = this.getContentPane();
		content.setLayout(new GridLayout(2, 2));
		Border border = LineBorder.createGrayLineBorder();
		this.setTitle("Ayuda de Mario Party");
		JLabel label1 = new JLabel(upIcon);
		label1.setHorizontalTextPosition(JLabel.LEFT);
		label1.setVerticalTextPosition(JLabel.BOTTOM);
		label1.setBorder(border);
		content.add(label1);

		label1.setText("The buttons for the game are: ");
		// pantalla.add(label1);
		label1.setVisible(true);

		// JLabel label2 = new JLabel("UP\n Down\n Right\n Left\n Space\n Enter\n
		// Escape\n");
		// pantalla.add(label2);
		// label2.setVisible(true);

		// Se definen las opciones por default
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		javaMenuBar = new JMenuBar();

		jmFile = new JMenu("Archivo");

		// Se crean los items de la pantalla
		JMenuItem jmiExit = new JMenuItem("Salir");

		// Se agregan los items al archivo
		jmFile.add(jmiExit);
		javaMenuBar.add(jmFile);

		jmiExit.addActionListener(this);

		this.setJMenuBar(javaMenuBar);
		this.setVisible(true);
	}


	public static void main(String args[]) {
		new Ayuda();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String comStr = ae.getActionCommand();

		if (comStr == "Salir") {
			this.dispose();
		} else if (comStr == "Ayuda de Mario Party") {
			new Ayuda().setVisible(true);
		}

		System.out.println(comStr + " Selected");
	}
}
