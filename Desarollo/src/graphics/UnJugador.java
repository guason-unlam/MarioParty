package graphics;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UnJugador implements ActionListener {

	Game game;
	JFrame frame;

	private static final long serialVersionUID = -2857003455782693239L;

	public UnJugador(int w, int h, String title, Game game) {
		this.game = game;
		frame = new JFrame(title);
		frame.setBounds(0, 0, w, h);
		frame.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		JButton jugarButton = new JButton();
		jugarButton.setText("Jugar");
		jugarButton.setForeground(Color.GREEN);
		jugarButton.addActionListener(this);
		panel.add(jugarButton);
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e) {
		this.frame.setVisible(false);
		new GameWindow(800, 600, "Mario Party Prototype", game);
	}

}
