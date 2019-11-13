package org;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	
	int x = 0,
		y = 0,
		xa = 1,
		ya = 1;
	
	private static final int DIAMETER = 30;
	
	private Game game;
	
	void move() {
		
		boolean changeDirection = true;
		
		if(x + xa < 0) {
			xa = game.speed;
		}	
		else if (x + xa > game.getWidth() - DIAMETER) {
			xa = -game.speed;
		}	
		else if(y + ya < 0) {
			ya = game.speed;
		}	
		else if( y + ya > game.getHeight() -DIAMETER) {
			game.gameOver();
		}
		else if(collision()) {
			ya = -game.speed;
			y = game.racquet.getTopY() - DIAMETER;
			game.speed++;
		}else
			changeDirection = false;
		
		if(changeDirection)
			Sound.BALL.play();

		x = x + xa;
		y = y + ya;
			
	}
	
	private boolean collision() {
		return game.racquet.getBounds().intersects(getBounds());
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
	public void paint(Graphics2D graph) {
		graph.fillOval(x,  y, 30, 30);
		graph.setColor(Color.RED);
	}

	public Ball(Game game) {
		
		this.game = game;
		
	}

}