package org;

import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class KeyBoard extends JPanel{

	public KeyBoard() {
//		
//		KeyListener listener = new KeyListener() {
//			@Override
//			public void keyTyped(KeyEvent e) {
//				
//			}
//			
//			@Override
//			public void keyPressed(KeyEvent e) {
//				System.out.println("KeyPressed=" + KeyEvent.getKeyText(e.getKeyCode()));
//			}
//			
//			@Override
//			public void keyReleased(KeyEvent e) {
//				System.out.println("KeyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
//			}
//		};
//		addKeyListener(listener);
//		setFocusable(true);
		
	}

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Mini Tennis");
		KeyBoard keyboardExample = new KeyBoard();
		frame.add(keyboardExample);
		frame.setSize(200, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}


}