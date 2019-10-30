package juego.minijuego.preguntas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Tablero extends JPanel {
	Pregunta pregunta = new Pregunta(50, 50);
	Opcion opA = new Opcion(50, 200);
	Opcion opB = new Opcion(50, 270);
	Opcion opC = new Opcion(450, 200);
	Opcion opD = new Opcion(450, 270);

	public Tablero() {
		setBackground(Color.BLUE);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon imaFondo = new ImageIcon(getClass().getResource("../../recursos/fondo.jpg"));
		g.drawImage(imaFondo.getImage(), 0, 0, 800, 500, null);
		setOpaque(false);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.GRAY);
		dibujar(g2);
//		dibujarPuntaje(g2);
//		actualizar();
	}

	public void dibujar(Graphics2D g) {
		Line2D.Double linea = new Line2D.Double(0, 350, 800, 350);
		g.draw(linea);
		g.fill(pregunta.getPregunta());
		g.fill(opA.getOpcion());
		g.fill(opB.getOpcion());
		g.fill(opC.getOpcion());
		g.fill(opD.getOpcion());

//		g.fill(raqueta1.getRaqueta());
//		g.fill(raqueta2.getRaqueta());
	}

}
