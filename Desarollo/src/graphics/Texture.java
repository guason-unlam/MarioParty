package graphics;

import java.awt.image.BufferedImage;

import juego.Constantes;

public class Texture {
	
	SpriteSheet cs; // characterSheet;
	
	private BufferedImage characterSheet;
	
	public BufferedImage[] characters = new BufferedImage[Constantes.CANTIDAD_PERSONAJES];
	
	public Texture() {
	
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			characterSheet = loader.loadImage("/characters_sheet.png");
		}catch(Exception e) {
			e.getStackTrace();
		}
		
		cs = new SpriteSheet(characterSheet);
		
		getTextures();
	}

	private void getTextures() {

		characters[0] = cs.grabImage(1, 1, 44, 44);
		characters[1] = cs.grabImage(2, 1, 44, 44);
		characters[2] = cs.grabImage(3, 1, 44, 44);
		/* Se deben cargar todos los personajes que se quiera tener en el juego */
		/* Hay que ir expandiendo la imagen que cree con los cuadritos de cada uno, ahora tiene 3 nomas
		 * pero se le pueden agregar las que quieran. */
	}
	
}
