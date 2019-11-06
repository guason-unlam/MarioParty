package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import juego.tablero.casillero.Casillero;

public class GameWindow {

	public GameWindow(int w, int h, String title, Game game) {
		
		game.setPreferredSize(new Dimension(w, h));
		game.setMaximumSize(new Dimension(w, h));
		game.setMinimumSize(new Dimension(w, h));

		JFrame frame = new JFrame(title);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.WEST);
		frame.pack();
		JButton mover = new JButton("Mover");
		mover.setPreferredSize(new Dimension(50, 50));
		mover.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Player temp = null;
				Casillero cas2;
				cas2 = new Casillero(1);
				cas2.setPosicionX(96);
				cas2.setPosicionY(32);
				for(GameObject go : game.handler.object) {
					if(go.id == ObjectId.Player){
						temp = (Player)go;
					}
				}
				Game.moverDeCasilleroACasillero( cas2, temp);
				
			}
		});
		frame.add(mover, BorderLayout.EAST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	
}
