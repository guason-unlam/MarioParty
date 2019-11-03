package graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import juego.Constantes;
import juego.item.Recompensa;
import juego.lobby.Partida;
import juego.lobby.Usuario;
import juego.misc.ExcepcionArchivos;
import juego.personas.Jugador;
import juego.tablero.Tablero;
import juego.tablero.casillero.Casillero;
import juego.ventana.PanelConsola;

public class Game extends Canvas implements Runnable {

	public static int WIDTH, HEIGHT;

	private boolean running = false;

	private Thread thread;

	public Handler handler;

	private BufferedImage map;

	static Texture tex;

	private Partida partida;

	private GameWindow ventana;

	private Tablero tab;

	/* Cosas para el intento de mover a mario */
	public Casillero cas1, cas2;
	public Jugador jugadorActual; // el personaje q esta jugando actualmente
	private int numeroJugadorActual = 0;
	boolean varPrueba;
	private static int[][] matrizMapa = new int[25][18];
	/**/
	private static final long serialVersionUID = 7245467516827418593L;

	public void init() {

		WIDTH = getWidth();
		HEIGHT = getHeight();

		tex = new Texture();

		BufferedImageLoader loader = new BufferedImageLoader();
		/* Add true path */
//		map = loader.loadImage("/mapa_01.png");
		// New Handler
		handler = new Handler();

//		loadImageMap(map);
//		Tablero tab;
		List<Usuario> usuarios = new ArrayList<Usuario>(); // creo los usuarios aca, temporalmente
		usuarios.add(new Usuario("nombre1", "contrasenia"));
		usuarios.add(new Usuario("nombre2", "contrasenia"));
		partida = new Partida(usuarios, 10);
		for (int i = 0; i < 25; i++)
			for (int j = 0; j < 18; j++)
				matrizMapa[i][j] = 0;
		tab = partida.getTablero();
		leerTablero(tab);
		for (Jugador jugador : partida.getJugadoresEnPartida()) {
			handler.addObject(jugador);
		}
		jugadorActual = partida.getJugadoresEnPartida().get(0);
	}

	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	private void tick() {
		handler.tick();
		if (!varPrueba) {
//			Game.moverDeCasilleroACasillero(cas1, cas2, new Player(96,192,0,ObjectId.Player));
			varPrueba = true;
		}
	}

	private void render() {

		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		/////////////////////////////////////////
//		Draw Here

		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);

		/////////////////////////////////////////

		g.dispose();
		bs.show();

	}
	/* Metodo creado para leer tablero desde una imagen png */
//	private void loadImageMap(BufferedImage image) {
//		int w = image.getWidth();
//		int h = image.getHeight();
//
//		for(int xx = 0; xx < h; xx++) {
//			for(int yy = 0; yy < w; yy++) {
//				int pixel = image.getRGB(yy, xx);
//				int red = ( pixel >> 16 ) & 0xff;
//				int green = (pixel >> 8) & 0xff;
//				int blue = (pixel) & 0xff;
//				
//				if(red == 0 && green == 255 & blue == 0) handler.addObject(new Road(yy * 32, xx * 32, 0, ObjectId.Road));
//				if(red == 255 && green == 0 & blue == 0) handler.addObject(new Spot(yy * 32, xx * 32, 0, ObjectId.Spot));
//				
//			}
//		}
//	}

	public void leerTablero(Tablero tab) {
		System.out.println(tab.getCasilleros().keySet());
		for (Entry<Integer, Casillero> elemento : tab.getCasilleros().entrySet()) {

			Casillero casilleroActual = elemento.getValue();
			handler.addObject(new Spot(casilleroActual.getPosicionX(), casilleroActual.getPosicionY(), 0, ObjectId.Spot,
					casilleroActual));
			System.out.println("x - " + casilleroActual.getPosicionX() + " - " + casilleroActual.getPosicionX() / 32);
			System.out.println("y - " + casilleroActual.getPosicionY() + " - " + casilleroActual.getPosicionY() / 32);
			matrizMapa[(int) casilleroActual.getPosicionX() / 32][(int) casilleroActual.getPosicionY() / 32] = 1;

			/* Caminos */
			for (Casillero sig : casilleroActual.getSiguiente()) {
				int x = casilleroActual.getPosicionX();
				int y = casilleroActual.getPosicionY();

				while (x != sig.getPosicionX()) {
					if (x > sig.getPosicionX()) {
						x -= 32;
						casilleroActual.setCaminoIzquierda(true);
					} else if (x < sig.getPosicionX()) {
						x += 32;
						casilleroActual.setCaminoDerecha(true);
					}
					handler.addObject(new Road(x, y, 0, ObjectId.Road));
					matrizMapa[(int) x / 32][(int) y / 32] = 1;

				}
				while (y != sig.getPosicionY()) {
					if (y > sig.getPosicionY()) {
						y -= 32;
					} else if (y < sig.getPosicionY()) {
						y += 32;
						casilleroActual.setCaminoArriba(true);
					}
					handler.addObject(new Road(x, y, 0, ObjectId.Road));
					matrizMapa[(int) x / 32][(int) y / 32] = 1;

				}
			}
		}

	}

	public static Texture getInstance() {
		return tex;
	}

	public void avanzarJugador(int cant) {
		ventana.getPanelConsola().agregarTexto(jugadorActual.getNombre() + " lanzo el dado y saco " + cant);
		System.out.println("avanzo " + cant);
		for (int i = cant; i > 0; i--) {

			if (jugadorActual.caminosDisponibles() == 1) {

				jugadorActual.avanzarUnCasillero();
			} else {
				String[] caminos = new String[jugadorActual.caminosDisponibles()];
				for (int j = 0; j < jugadorActual.caminosDisponibles(); j++) {
					caminos[j] = "Camino " + jugadorActual.getPosicion().getSiguiente().get(j).getId();
				}
				int camino;
				do {
					camino = JOptionPane.showOptionDialog(null, "Elegir camino", "Elija un camino",
							JOptionPane.WARNING_MESSAGE, 0, null, caminos, caminos[0]);
				} while (camino == JOptionPane.CLOSED_OPTION);
				jugadorActual.avanzarUnCasillero(camino);
				//
				// ESTE BREAK NO DEBERIA IR
				//
				break;
			}
			if (jugadorActual.getPosicion().isTieneArbolito()) { // pregunto si quiere comprar dolar
				int respuesta = JOptionPane.showConfirmDialog(ventana.getFrame().getContentPane(),
						"Desea comprar un dolar?", "Atención!", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				if (respuesta == JOptionPane.YES_OPTION) {
					if (jugadorActual.comprarDolar())
						ventana.getPanelConsola().agregarTexto(jugadorActual.getNombre() + " ha comprado un dolar!");
				}
			}
		}
		if (jugadorActual.getPosicion().isTieneRecompensa()) {
			Recompensa recompensa = jugadorActual.getPosicion().getRecompensa();
			recompensa.darRecompensa(jugadorActual);
			ventana.getPanelConsola()
					.agregarTexto(jugadorActual.getNombre() + " ha obtenido " + recompensa.getNombre());
			jugadorActual.getPosicion().setRecompensa(null);
			jugadorActual.getPosicion().setTieneRecompensa(false);
		}
	}

	public void continuar() {
		siguienteJugador();
	}

	private void siguienteJugador() {
		numeroJugadorActual++;
		if (numeroJugadorActual > partida.getJugadoresEnPartida().size()) {// aca termino la ronda, se lanzaria el
																			// minijuego
			numeroJugadorActual = 1;
		}
		jugadorActual = partida.getJugadoresEnPartida().get(numeroJugadorActual - 1);
		ventana.getPanelJugador().setNombreJugador("Turno de " + jugadorActual.getNombre());
		ventana.getPanelConsola().agregarTexto("Comienza turno de " + jugadorActual.getNombre());
	}

	public static int hayCamino(double d, double e) {
//		System.out.println(matrizMapa[(int) d / 32][(int) e / 32] == 1
//				? "Hay camino entre " + 1 + (int) d / 32 + " y " + 1 + (int) e / 32
//				: "NO Hay camino entre " + 1 + (int) d / 32 + " y " + 1 + (int) e / 32);
		return matrizMapa[(int) d / 32][(int) e / 32];
	}

	public void setVentana(GameWindow ventana) {
		this.ventana = ventana;
	}

	public static void main(String[] args) {

		new UnJugador(800, 600, "Mario Party Prototype", new Game());

	}

}
