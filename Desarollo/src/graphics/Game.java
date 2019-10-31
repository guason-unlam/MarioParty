package graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Map.Entry;


import juego.Constantes;
import juego.misc.ExcepcionArchivos;
import juego.tablero.Tablero;
import juego.tablero.casillero.Casillero;




public class Game extends Canvas implements Runnable {

	public static int WIDTH, HEIGHT;
	
	private boolean running = false;
	
	private Thread thread;
	
	public Handler handler;
	
	private BufferedImage map;
	
	static Texture tex;
	
	/* Cosas para el intento de mover a mario*/
	Casillero cas1, cas2;
	boolean varPrueba;
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
		Tablero tab;
		try {
			tab = new Tablero("../Mapas/tablero03.txt");
			leerTablero(tab);
			Player pj = new Player(96, 192, 0, ObjectId.Player);
			pj.setVelX(1);
			handler.addObject(pj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*Intento mover a mario*/
		varPrueba = false;
		cas1 = new Casillero(0);
		cas1.setPosicionX(96);
		cas1.setPosicionY(192);
		cas2 = new Casillero(1);
		cas2.setPosicionX(640);
		cas2.setPosicionY(64);
		/**/
	}
	
	
	public synchronized void start() {
		if(running) {
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
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
				
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}


	private void tick() {
		handler.tick();
		if(!varPrueba) {
			Game.moverDeCasilleroACasillero(cas1, cas2, new Player(96,192,0,ObjectId.Player));
			varPrueba = true;
		}
	}

	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
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
	private void loadImageMap(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for(int xx = 0; xx < h; xx++) {
			for(int yy = 0; yy < w; yy++) {
				int pixel = image.getRGB(yy, xx);
				int red = ( pixel >> 16 ) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 0 && green == 255 & blue == 0) handler.addObject(new Road(yy * 32, xx * 32, 0, ObjectId.Road));
				if(red == 255 && green == 0 & blue == 0) handler.addObject(new Spot(yy * 32, xx * 32, 0, ObjectId.Spot));
				
			}
		}
	}
	
	/*	Intento de mover a mario/cualquierPj	*/
	public static void moverDeCasilleroACasillero(Casillero casAct, Casillero casSig, Player p){
		int x = casAct.getPosicionX();
		int y = casAct.getPosicionY();
		p.xObjetivo = casSig.getPosicionX();
		p.yObjetivo = casSig.getPosicionY();
		if(x < casSig.getPosicionX()) {
			p.setVelX(0.5f);
		}else if(x > casSig.getPosicionX()){
			p.setVelX(-0.5f);
		}
		p.enMovimientoX = true;
		while(p.enMovimientoX) {
		}
		if(y < casSig.getPosicionY()) {
			p.setVelY(0.5f);
		}else if(y > casSig.getPosicionY()){
			p.setVelY(-0.5f);
		}
		
	}
	
	public void leerTablero(Tablero tab) {
		 for (Entry<Integer, Casillero> elemento : tab.getCasilleros().entrySet()) {

				Casillero casilleroActual = elemento.getValue();
				handler.addObject(
						new Spot(casilleroActual.getPosicionX(),
								 casilleroActual.getPosicionY(), 
								 0, ObjectId.Spot));
				
				/* Caminos */
				for (Casillero sig : casilleroActual.getSiguiente()) {
					int x = casilleroActual.getPosicionX();
					int y = casilleroActual.getPosicionY();
					
					while(x != sig.getPosicionX()) {
						if(x > sig.getPosicionX()) {
							x -= 32;
						}else if(x < sig.getPosicionX()){
							x += 32;
						}
						handler.addObject(
							new Road(x, y, 0,ObjectId.Road));
						
					}
					while(y != sig.getPosicionY()) {
						if(y > sig.getPosicionY()) {
							y -= 32;
						}else if(y < sig.getPosicionY()){
							y += 32;
						}
						handler.addObject(
								new Road(x, y, 0,ObjectId.Road));
						
					}
				}
			}
	}
	
	
	public static Texture getInstance() {
		return tex;
	}
	
	public static void main(String[] args) {

		new UnJugador(800, 600, "Mario Party Prototype", new Game());
		
	}
	
}
