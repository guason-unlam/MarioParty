package graphics.elements;

import java.awt.image.BufferedImage;

import juego.Constantes;

public class Texture {

	SpriteSheet cs; // characterSheet;
	SpriteSheet ss;
	SpriteSheet rs;

	private BufferedImage characterSheet;
	private BufferedImage spotSheet;
	private BufferedImage roadSheet;

	public BufferedImage[] characters = new BufferedImage[Constantes.CANTIDAD_PERSONAJES];
	public BufferedImage[] spots = new BufferedImage[Constantes.NUMBER_OF_SPOTS];
	public BufferedImage[] roads = new BufferedImage[Constantes.NUMBER_OF_ROADS];

	/*
	 * Clase que levanta las imagenes para poder usarlas despues en su clase
	 * correspondiente
	 */
	public Texture() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			characterSheet = loader.loadImage(Constantes.ASSETS_PATH + Constantes.IMAGEN_PATH + "characters_sheet.png");

			spotSheet = loader.loadImage(Constantes.ASSETS_PATH + Constantes.IMAGEN_PATH + "spots_sheet.png");
			roadSheet = loader.loadImage(Constantes.ASSETS_PATH + Constantes.IMAGEN_PATH + "roads_sheet.png");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		cs = new SpriteSheet(characterSheet);
		ss = new SpriteSheet(spotSheet);
		rs = new SpriteSheet(roadSheet);
		getTextures();
	}

	private void getTextures() {
		/* Characters */
		characters[0] = cs.grabImage(1, 1, 44, 44);
		characters[1] = cs.grabImage(2, 1, 44, 44);
		characters[2] = cs.grabImage(3, 1, 44, 44);
		/* Roads */
//		/* TODO: change values on demand */
		roads[0] = rs.grabImage(1, 1, 32, 32);
//		/* Spots */
		spots[0] = ss.grabImage(1, 1, 32, 32);
		spots[1] = ss.grabImage(2, 1, 32, 32);
		spots[2] = ss.grabImage(3, 1, 32, 32);
		spots[3] = ss.grabImage(4, 1, 32, 32);
		/* Se deben cargar todos los personajes que se quiera tener en el juego */
		/*
		 * Hay que ir expandiendo la imagen que cree con los cuadritos de cada uno,
		 * ahora tiene 3 nomas pero se le pueden agregar las que quieran.
		 */
	}

}
