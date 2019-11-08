package juego.ventana.minijuego;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import cliente.Musica;
import juego.Constantes;
import juego.lobby.Usuario;
import juego.personas.Jugador;
import juego.ventana.Coordinador;
import juego.ventana.VentanaAdministracionSala;

public class VentanaMiniJuego extends JFrame {

	private JLabel icono;
	private Usuario usuario = new Usuario("juan", "perez");

	private static final long serialVersionUID = 9099821102113802071L;
	private Musica musica;
	private JButton tirarDadosButton;

	public VentanaMiniJuego(ArrayList<Jugador> arrJugadores, VentanaAdministracionSala ventanaAdministracionSala,
			Musica musica) {
		if (musica == null) {
			musica = new Musica(Constantes.MUSICA_LOGIN);
			musica.loop();
		} else {
			this.musica = musica;
		}

		// Obtengo el usuario!
		usuario = Cliente.getConexionInterna().getUsuario();
		this.setBounds(0, 0, 600, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		setTitle("Minijuego");

		Icon icon = new ImageIcon(Constantes.ASSETS_PATH + Constantes.IMAGEN_PATH + "tirarDados.gif");
		icono = new JLabel(icon);
		icono.setHorizontalAlignment(SwingConstants.LEFT);
		icono.setBounds(new Rectangle(10, 95, 336, 241));
		getContentPane().add(icono);

		tirarDadosButton = new JButton("Tirar dados!");
		tirarDadosButton.setBounds(new Rectangle(220, 133, 186, 62));
		tirarDadosButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tirarDadosButton.setForeground(Color.BLACK);
		tirarDadosButton.setBackground(Color.GREEN);
		tirarDadosButton.setBounds(371, 191, 213, 58);
		tirarDadosButton.setFocusPainted(false);

		getContentPane().add(tirarDadosButton);

		JTextArea instrucciones = new JTextArea();
		instrucciones.setLocation(70, 11);
		instrucciones.setFont(new Font("Dialog", Font.PLAIN, 22));
		instrucciones.setText(
				"El juego consta de varios jugadores,los cuales tirar\u00E1n 10 veces un dado y se realizar\u00E1 la suma de todos los tiros.");
		instrucciones.setEditable(false);
		instrucciones.setLineWrap(true);
		instrucciones.setOpaque(false);
		instrucciones.setWrapStyleWord(true);
		instrucciones.setBorder(BorderFactory.createEmptyBorder());
		instrucciones.setSize(400, 100);
		getContentPane().add(instrucciones, BorderLayout.CENTER);

		addListener();
	}

	private void addListener() {

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				int opcion = JOptionPane.showConfirmDialog(getContentPane(), "Desea cerrar la ventana?", "Atención!",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (opcion == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		tirarDadosButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				listoInstrucciones();

			}
		});

	}

	protected void listoInstrucciones() {
		// Voy a crear una ventana nueva, que va mostrando la tablita
		VentanaResultadosMiniJuego ventanaResultadosMiniJuego = new VentanaResultadosMiniJuego(this, this.musica);
		Coordinador.setVentanaResultadosMiniJuego(ventanaResultadosMiniJuego);
		// La referencia a la administracion de sala ya la tiene el coordinador
		JsonObject paqueteAceptarInstruccionesYRollear = Json.createObjectBuilder()
				.add("type", Constantes.TIRAR_DADO_REQUEST).add("usuario", usuario.getUsername()).build();

		// Le aviso al sv que estoy listo para jugar
		Cliente.getConexionServidor().enviarAlServidor(paqueteAceptarInstruccionesYRollear);

		ventanaResultadosMiniJuego.setVisible(true);
		this.setVisible(false);
	}

}
