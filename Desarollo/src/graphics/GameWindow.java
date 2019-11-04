package graphics;

import java.awt.Dimension;

import javax.swing.JFrame;

import juego.Constantes;
import juego.ventana.PanelConsola;
import juego.ventana.PanelJugador;
import juego.ventana.PanelPuntaje;

public class GameWindow {

	PanelJugador panelJugador;
	PanelConsola panelConsola;
	PanelPuntaje panelPuntaje;
	JFrame frame;

	public GameWindow(int w, int h, String title, Game game) {

		game.setPreferredSize(new Dimension(w, h + 75));
		game.setMaximumSize(new Dimension(w, h + 100));
		game.setMinimumSize(new Dimension(w, h));
		game.setVentana(this);
		// todo esto es la interface, podria estar en otra clase "Interface"
		panelJugador = new PanelJugador(10, Constantes.VENTANA_HEIGHT - 35);
		panelJugador.setBounds(0, 550, 260, 110);
		panelJugador.setGame(game);
		panelConsola = new PanelConsola(panelJugador.getWidth(), 550);
		panelPuntaje = new PanelPuntaje(panelJugador.getWidth() + panelConsola.getWidth(), 550);
		///////////////////////////////////////////////////////////////////
		frame = new JFrame(title);
		frame.add(panelJugador);
		frame.add(panelConsola);
		frame.add(panelPuntaje);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}

	public PanelConsola getPanelConsola() {
		return panelConsola;
	}

	public PanelJugador getPanelJugador() {
		return panelJugador;
	}

	public JFrame getFrame() {
		return this.frame;
	}

}
