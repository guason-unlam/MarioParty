package juego.ventana;

import javax.swing.*;

import juego.Constantes;
import juego.lobby.Partida;
import juego.tablero.casillero.Casillero;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Map.Entry;

class VentanaMiniJuego extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9099821102113802071L;
	private JMenuBar javaMenuBar = null;
	private JMenu jmFile = null;
	private JMenu jmOptions = null;

	public VentanaMiniJuego() {
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html><div style='text-align: center;'>El juego consta de varios jugadores,</br>\r\n los cuales tirar\u00E1n 10 veces un dado y se </br>\r\nrealizar\u00E1 la suma de todos los tiros.</div></html>", SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblNewLabel.setBounds(48, 98, 488, 275);
		getContentPane().add(lblNewLabel);
		setTitle("Mario Party");

		// Sin esto, el yes/no dialog no sirve
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, Constantes.MAPA_WIDTH, Constantes.MAPA_HEIGHT);
		this.setLocationRelativeTo(null);

	}

	public static void main(String[] args) {
		new VentanaMiniJuego().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String comStr = ae.getActionCommand();

		System.out.println(comStr + " Selected");
	}
}
