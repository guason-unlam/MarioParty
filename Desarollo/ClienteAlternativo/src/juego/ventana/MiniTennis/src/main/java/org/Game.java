package org;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel{
	
	Ball ball = new Ball(this);
	Racquet racquet = new Racquet(this);
	
	int speed = 1;
	
	private static BufferedImage image;
//	public void bg(){
//		img = Toolkit.getDefaultToolkit().createImage("SpriteSheet.png");
//	}
	
	private int getScore() {
		return speed - 1;
	}
	
	private void move() {
		ball.move();
		
		racquet.move();
	}
	
	public Game() {

		addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				
			}

			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);				
			}

			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);			
			}
		});
		
		setFocusable(true);
		
		Sound.MARCHA.loop();
	}
	
	@Override
	public void paint(Graphics graph) {
		
		super.paint(graph);
		
		Graphics2D graph2D = (Graphics2D) graph;
		
		graph2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		try {
			image = ImageIO.read(new File("src\\main\\java\\org\\gh36.jpg"));
		
			graph2D.drawImage(image, 0,0,null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ball.paint(graph2D);

		racquet.paint(graph2D);
		
		graph2D.setColor(Color.RED);
		
		graph2D.setFont(new Font("Verdana", Font.BOLD, 30));
		
		graph2D.drawString(String.valueOf(getScore()), 10, 30);
		
//		ghost = Toolkit.getDefaultToolkit().createImage(urlGhost);
	}
	
	public void gameOver() {
		Sound.MARCHA.stop();
		Sound.GAMEOVER.play();
		JOptionPane.showMessageDialog(this, "Your Score is: " + getScore(), "Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		JFrame frame = new JFrame("Minni Tennis");
		
//		try {
//            image = ImageIO.read(new File("C:\\Users\\Luciano\\Downloads\\gh36.jpg"));
//            // Set your Image Here.
//            frame.setContentPane(new JLabel(new ImageIcon(image)));
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
		Game game = new Game();

		frame.add(game);
		
		frame.setSize(300,400);
		
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while(true) {
			
			game.move();
			
			game.repaint();
			
			Thread.sleep(10);
		}

	}

}