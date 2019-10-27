package cliente;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;

import juego.Constantes;

import javax.imageio.*;

public class DyeImage {
	private BufferedImage image;

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new DyeImage();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public DyeImage() throws Exception {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.image = ImageIO.read(new File(Constantes.ASSETS_PATH + Constantes.IMAGEN_PATH + "/characters_sheet.png"));
		image = grabImage(0, 0, 10, 10);
		JPanel panel = new JPanel(new GridLayout(1, 0));
		panel.add(new JLabel(new ImageIcon(image)));
		panel.add(new JLabel(new ImageIcon(dye(image, new Color(255, 0, 0, 128)))));
		panel.add(new JLabel(new ImageIcon(dye(image, new Color(255, 0, 0, 32)))));
		panel.add(new JLabel(new ImageIcon(dye(image, new Color(0, 128, 0, 32)))));
		panel.add(new JLabel(new ImageIcon(dye(image, new Color(0, 0, 255, 32)))));
		f.getContentPane().add(panel);
		f.pack();
		f.setVisible(true);
	}

	public BufferedImage grabImage(int col, int row, int width, int height) {
		BufferedImage img = image.getSubimage((col * width) - width, (row * height) - height, width, height);
		return img;
	}

	private static BufferedImage dye(BufferedImage image, Color color) {
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage dyed = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = dyed.createGraphics();
		g.drawImage(image, 0, 0, null);
		g.setComposite(AlphaComposite.SrcAtop);
		g.setColor(color);
		g.fillRect(0, 0, w, h);
		g.dispose();
		return dyed;
	}

}