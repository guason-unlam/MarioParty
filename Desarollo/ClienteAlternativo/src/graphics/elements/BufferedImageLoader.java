package graphics.elements;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {

	private BufferedImage image;

	public BufferedImage loadImage(String path) {
		try {
			image = ImageIO.read(new FileInputStream((path)));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return image;
	}

}
