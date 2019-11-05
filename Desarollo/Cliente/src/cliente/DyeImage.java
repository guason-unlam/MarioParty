package cliente;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import juego.Constantes;

public class DyeImage {
	private static BufferedImage image;

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

		JPanel panel = new JPanel(new GridLayout(5, 3));
		for (int i = 3; i < 4; i++) {
			image = grabImage(i, 1, 44, 44);
			panel.add(new JLabel(new ImageIcon(image)));
			panel.add(new JLabel(new ImageIcon(dye(image, new Color(0, 0, 153, 120)))));
			panel.add(new JLabel(new ImageIcon(dye(image, new Color(0, 153, 0, 120)))));
			panel.add(new JLabel(new ImageIcon(dye(image, new Color(255, 102, 0, 120)))));
			panel.add(new JLabel(new ImageIcon(dye(image, new Color(255, 255, 0, 120)))));
			panel.add(new JLabel(new ImageIcon(dye(image, new Color(51, 0, 0, 150)))));
			panel.add(new JLabel(new ImageIcon(dye(image, new Color(102, 0, 153, 120)))));
			// Personaje que no tiene el turno
			panel.add(new JLabel(new ImageIcon(dye(image, new Color(51, 51, 51, 120)))));
		}

		f.getContentPane().add(panel);

		f.pack();
		f.setVisible(true);
	}

	public static BufferedImage grabImage(int col, int row, int width, int height) {
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