package juego.minijuego;

public class Hilo extends Thread {
	TableroPingPong lamina;

	public Hilo(TableroPingPong lamina) {
		this.lamina = lamina;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lamina.repaint();
		}
	}
}
