package juego.minijuego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
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
		dibujarPuntaje(g2);
		actualizar();
	}

	public void dibujar(Graphics2D g) {
		Line2D.Double linea = new Line2D.Double(getBounds().getCenterX(), 0, getBounds().getCenterX(),
				getBounds().getMaxY());
		g.draw(linea);
		g.fill(pelota.getPelota());
		g.fill(raqueta1.getRaqueta());
		g.fill(raqueta2.getRaqueta());
	}

	public void actualizar() {
		pelota.moverPelota(getBounds(), colision(raqueta1.getRaqueta()), colision(raqueta2.getRaqueta()));
		raqueta1.moverR1(getBounds());
		raqueta2.moverR2(getBounds());

	}

	private boolean colision(Rectangle2D r) {
		return pelota.getPelota().intersects(r);

	}
	 private void dibujarPuntaje(Graphics2D g) {
	        Graphics2D g1 = g, g2 = g;
	        Font score = new Font("Arial", Font.BOLD, 30);
	        g.setFont(score);

	        g1.drawString(Integer.toString(pelota.getScore1()), (float) getBounds().getCenterX() - 50, 30);
	        g2.drawString(Integer.toString(pelota.getScore2()), (float) getBounds().getCenterX() + 25, 30);
	        if (pelota.getScore1() == 5) {
	            g.drawString("GANÓ El JUGADOR 1", (float) getBounds().getCenterX() - 180, (float) getBounds().getCenterY() - 100);
	            PelotaPingPong.finJuego = true;
	        }
	        if (pelota.getScore2() == 5) {
	            g.drawString("GANÓ EL JUGADOR 2", (float) getBounds().getCenterX() - 180, (float) getBounds().getCenterY() - 100);
	            PelotaPingPong.finJuego = true;
	        }
	    }


}
