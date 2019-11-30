package juego.ventana;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import graphics.Game;

public class PanelJugador extends JPanel {

	private JLabel lblNombrejugador;
	private static JButton btnLanzarDado;
	private static JButton btnUsarItem;
	private static JButton btnPasar;
//	ControladorJuego juego;
	Game juego;
	protected static boolean botonesActivados = true;

	/**
	 * Create the panel.
	 */
	public PanelJugador(int x, int y) {
		this.setBounds(x, y, 260, 110);

		setBackground(Color.BLACK);
		setLayout(null);

		lblNombrejugador = new JLabel("nombreJugador");
		lblNombrejugador.setBounds(10, 11, 131, 14);
		add(lblNombrejugador);

		btnLanzarDado = new JButton("Lanzar dado");
		btnLanzarDado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				juego.avanzarJugador(juego.getJugadorActual().tirarDado());
//				juego.continuar();

				/*
				 * while (botonesActivados) { System.out.println("DESACTIVAR"); }
				 * System.out.println("botones desactivados");
				 */
				// juego.continuar();
				// System.out.println(juego.jugadorActual.getId());
				juego.avanzarJugador(juego.jugadorActual.tirarDado());
			}
		});
		btnLanzarDado.setBounds(10, 36, 106, 23);
		add(btnLanzarDado);

		btnUsarItem = new JButton("Usar item");
		btnUsarItem.setBounds(10, 70, 106, 23);
		btnUsarItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				juego.usarItem();
			}
		});
		add(btnUsarItem);

		btnPasar = new JButton("Pasar");
		btnPasar.setBounds(156, 36, 89, 57);
		btnPasar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				juego.continuar();
			}
		});
		add(btnPasar);

		btnLanzarDado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == VentanaConfiguracion.getLanzarDadoId()) {
					juego.avanzarJugador(juego.jugadorActual.tirarDado());
				}
			}
		});
		
		
		btnPasar.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent e){
				if(e.getKeyCode() == VentanaConfiguracion.getPasarId()){
					
				}
			}
		});
		
		btnUsarItem.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent e){
				if(e.getKeyCode() == VentanaConfiguracion.getUsarItemId()){
					
				}
			}
		});
	}
	
	
	
	

	public synchronized static void desactivarBotones() {
		btnLanzarDado.setEnabled(false);
		btnUsarItem.setEnabled(false);
		btnPasar.setEnabled(false);
	}

	public synchronized static void activarBotones() {
		btnLanzarDado.setEnabled(true);
		btnUsarItem.setEnabled(true);
		btnPasar.setEnabled(true);
	}

	public void setNombreJugador(String nombre) {
		lblNombrejugador.setText(nombre);
	}

	public void setGame(Game juego) {
		this.juego = juego;
	}

//	public void setControladorJuego(ControladorJuego juego) {
//		this.juego = juego;
//	}
}
