package org;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {
	
	int x = 0,
		xa = 0;
	
	private static final int Y = 330;
	private static final int WIDTH = 60;
	private static final int HEIGHT = 10;
	
	private Game game;
	
	public Racquet(Game game) {
		this.game = game;
	}

	public void move() {
		
		if(x + xa > 0 && x + xa < game.getWidth() - WIDTH) {
			x = x + xa;
		}
	
	}
	
	public void paint(Graphics2D graph) {		
		graph.fillRect(x, Y, WIDTH, HEIGHT);
		graph.setColor(Color.RED);
	}
	
	public void keyReleased(KeyEvent e) {		
		xa = 0;		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, Y, WIDTH, HEIGHT);
	}
	
	public int getTopY() {
		return Y - HEIGHT;
	}
	
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			xa = -game.speed;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			xa = game.speed;
		}
	}

}