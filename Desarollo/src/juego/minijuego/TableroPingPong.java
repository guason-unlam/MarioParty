package juego.minijuego;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TableroPingPong extends JPanel {
	Rectangle limites;
	PelotaPingPong pelota = new PelotaPingPong(0, 0);
	Raqueta raqueta1 = new Raqueta(10, 200);
	Raqueta raqueta2 = new Raqueta(794 - 10 - Raqueta.ANCHO, 200);

	public TableroPingPong() {
		setBackground(Color.BLACK);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		dibujar(g2);
		actualizar();
	}

	public void dibujar(Graphics2D g) {
		g.fill(pelota.getPelota());
		g.fill(raqueta1.getRaqueta());
		g.fill(raqueta2.getRaqueta());
	}

	public void actualizar() {
		pelota.mover(getBounds(), colision(raqueta1.getRaqueta()), colision(raqueta2.getRaqueta()));
		raqueta1.moverR1(getBounds());
		raqueta2.moverR2(getBounds());

	}

	private boolean colision(Rectangle2D r) {
		return pelota.getPelota().intersects(r);

	}

}
