package juego.ventana;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import juego.Constantes;

public class VentanaAdministracionSala extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3071454957161090149L;
	private JFrame lobby;
	private JPanel panel;
	private JButton btnJoin;
	private JButton btnVolver;
	private JLabel lblNewLabel;
	private BufferedImage image;
	private JLabel labelLeft;
	private JPanel panel2;
	private JLabel imageLabel;
	private int actual = 1;
	private ArrayList<JLabel> labelsIconos;
	private ListIterator<JLabel> lis;
	private JLabel labelRight;
	private BufferedImage image2;
	private BufferedImage image3;
	private BufferedImage imageSheet;

	public static void main(String[] args) {
		new VentanaAdministracionSala(null, "Sala de pruebas", true).setVisible(true);
		;
	}

	public VentanaAdministracionSala(JFrame ventanaLobby, String nombreSala, boolean esAdmin) {

		// Me guardo la referencia para hacerlo visible, etc
		this.lobby = ventanaLobby;

		setTitle("Salas disponibles");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setBounds(0, 0, 456, 400);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		btnJoin = new JButton("Entrar!");
		btnJoin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnJoin.setBounds(329, 314, 111, 46);
		panel.add(btnJoin);
		setLocationRelativeTo(this.lobby);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnVolver.setBounds(10, 314, 111, 46);
		panel.add(btnVolver);

		lblNewLabel = new JLabel(nombreSala);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(126, 11, 285, 106);

		panel.add(lblNewLabel);
		panel2 = new JPanel();
		panel2.setLocation(187, 199);
		panel2.setSize(50, 50);

		labelLeft = new JLabel("");
		labelLeft.setIcon(new ImageIcon(
				new ImageIcon(Constantes.ARROW_LEFT).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
		labelLeft.setFont(new Font("Tahoma", Font.BOLD, 17));
		labelLeft.setBounds(153, 209, 32, 32);

		getContentPane().add(panel2);
		getContentPane().add(labelLeft);

		labelRight = new JLabel("");
		labelRight.setFont(new Font("Tahoma", Font.BOLD, 17));
		labelRight.setIcon(new ImageIcon(
				new ImageIcon(Constantes.ARROW_RIGHT).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
		labelRight.setFont(new Font("Tahoma", Font.BOLD, 17));
		labelRight.setBounds(239, 209, 32, 32);
		panel.add(labelRight);

		labelsIconos = new ArrayList<JLabel>();

		try {
			imageSheet = ImageIO
					.read(new File(Constantes.ASSETS_PATH + Constantes.IMAGEN_PATH + "/characters_sheet.png"));
			image = grabImage(imageSheet, 1, 1, 44, 44);
			image2 = grabImage(imageSheet, 2, 1, 44, 44);
			image3 = grabImage(imageSheet, 3, 1, 44, 44);
			imageLabel = new JLabel(new ImageIcon(image));
			labelsIconos.add(imageLabel);
			panel2.add(imageLabel);
			labelsIconos.add(new JLabel(new ImageIcon(image2)));
			labelsIconos.add(new JLabel(new ImageIcon(image3)));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.addListener();
	}

	private void addListener() {
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lobby.setVisible(true);
				// Lo oculto, puede ser de utilidad luego
				setVisible(false);
			}
		});
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				switch (actual) {
				case 1:
					System.out.println("Elegiste Mario!");
					break;
				case 2:
					System.out.println("Elegiste Luigi!");
					break;
				case 3:
					System.out.println("Elegiste Yoshi!");
					break;
				}
			}
		});
		// Listener que se encarga de mostrar u ocultar la contraseña
		labelLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (actual > 1) {
					actual--;

					if (actual >= 1) {
						lis = labelsIconos.listIterator(actual);
						JLabel icono = lis.hasPrevious() ? lis.previous() : null;
						if (icono != null) {
							Component[] components = panel2.getComponents();

							for (Component component : components) {
								panel2.remove(component);
							}

							panel2.revalidate();
							panel2.repaint();
							panel2.add(icono);

						}
					}
				}
			}
		});

		labelRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (actual < labelsIconos.size()) {
					lis = labelsIconos.listIterator(actual);

					JLabel icono = null;
					if (actual == 0) {
						if (lis.hasNext()) {
							lis.next();
							if (lis.hasNext()) {
								icono = lis.next();
								actual++;
							}
						}
					} else {
						if (lis.hasNext()) {
							icono = lis.next();
						}
					}
					if (icono != null) {
						Component[] components = panel2.getComponents();

						for (Component component : components) {
							panel2.remove(component);
						}

						panel2.revalidate();
						panel2.repaint();
						panel2.add(icono);

						actual++;
					}
				}
			}
		});
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(getContentPane(), "Desea cerrar la ventana?", "Atencion!",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
	}

	public static BufferedImage grabImage(BufferedImage image, int col, int row, int width, int height) {
		BufferedImage img = image.getSubimage((col * width) - width, (row * height) - height, width, height);
		return img;
	}
}
