package juego.ventana;

import javax.swing.JPanel;

import juego.ControladorJuego;
import juego.personas.Jugador;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelJugador extends JPanel {

	
	private JLabel lblNombrejugador;
	ControladorJuego juego;
	
	/**
	 * Create the panel.
	 */
	public PanelJugador(int x, int y) {
		this.setBounds(x, y, 260, 110);
		
		setBackground(Color.PINK);
		setLayout(null);
		
		lblNombrejugador = new JLabel("nombreJugador");
		lblNombrejugador.setBounds(10, 11, 131, 14);
		add(lblNombrejugador);
		
		JButton btnLanzarDado = new JButton("Lanzar dado");
		btnLanzarDado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				juego.avanzarJugador(juego.getJugadorActual().tirarDado());
			}
		});
		btnLanzarDado.setBounds(10, 36, 106, 23);
		add(btnLanzarDado);
		
		JButton btnUsarItem = new JButton("Usar item");
		btnUsarItem.setBounds(10, 70, 106, 23);
		add(btnUsarItem);
		
		JButton btnPasar = new JButton("Pasar");
		btnPasar.setBounds(156, 36, 89, 57);
		add(btnPasar);

	}
	
	public void setNombreJugador(String nombre) {
		lblNombrejugador.setText(nombre);
	}
	
	public void setControladorJuego(ControladorJuego juego) {
		this.juego = juego;
	}
}
