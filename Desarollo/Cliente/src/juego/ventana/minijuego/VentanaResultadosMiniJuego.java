package juego.ventana.minijuego;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.json.JsonArray;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import cliente.Musica;
import juego.Constantes;

public class VentanaResultadosMiniJuego extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8985049683108599509L;
	private JFrame instrucciones;
	private JPanel panel;
	private JButton btnJoin;
	private JButton btnVolver;
	private JTable tableSalas;
	private ModelTableResultados modelTableResultados = new ModelTableResultados();
	private Musica musica;

	public VentanaResultadosMiniJuego(JFrame ventanaInstrucciones, Musica musica) {
		if (musica != null) {
			// Freno la musica anterior
			musica.stop();
		}

		this.musica = new Musica(Constantes.MUSICA_SUSPENSO);
		this.musica.loop();
		// Me guardo la referencia para hacerlo visible, etc
		this.instrucciones = ventanaInstrucciones;

		setTitle("Minijuego");
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

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
		setLocationRelativeTo(this.instrucciones);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnVolver.setBounds(10, 314, 111, 46);
		panel.add(btnVolver);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 430, 290);
		panel.add(scrollPane);

		tableSalas = new JTable();
		scrollPane.setViewportView(tableSalas);

		this.addListener();
	}

	private void addListener() {

		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				instrucciones.setVisible(true);
				// Lo oculto, puede ser de utilidad luego
				setVisible(false);
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

	/*
	 * private void entrarEnSala(String sala) { if
	 * (Cliente.getConexionInterna().unirseASala(sala)) { VentanaAdministracionSala
	 * ventanaSala = new VentanaAdministracionSala(instrucciones, sala, false);
	 * Coordinador.setVentanaAdministracionSala(ventanaSala);
	 * 
	 * JsonObject joinRoomRequest = Json.createObjectBuilder().add("type",
	 * Constantes.JOIN_ROOM_SV_REQUEST) .add("sala", sala).build();
	 * 
	 * Cliente.getConexionServidor().enviarAlServidor(joinRoomRequest);
	 * 
	 * this.setVisible(false); ventanaSala.setVisible(true); } else {
	 * JOptionPane.showMessageDialog(null,
	 * "Sala llena o con partida en curso, por favor seleccione otra.",
	 * "Error al ingresar", JOptionPane.WARNING_MESSAGE); } }
	 */
	public void refrescarPuntaje(JsonArray datosDeJugadores) {
		String data[][] = new String[datosDeJugadores.size()][2];
		System.out.println("|||" + datosDeJugadores.toString());
		for (int i = 0; i < datosDeJugadores.size(); i++) {

			data[i][0] = datosDeJugadores.getJsonObject(i).getString("nombre");
			data[i][1] = String.valueOf(datosDeJugadores.getJsonObject(i).getInt("puntos"));
		}

		if (datosDeJugadores.isEmpty()) {
			this.modelTableResultados.setTableEmpty();
			this.modelTableResultados.fireTableStructureChanged();
		} else {
			this.modelTableResultados.setData(data);
		}

		this.modelTableResultados.fireTableDataChanged();
		this.tableSalas.setModel(this.modelTableResultados);
	}

	public void mostrarPantallaFinal(JsonArray datosDeFinMinijuego) {
		VentanaResultadosFinal ventanaResultados = new VentanaResultadosFinal(datosDeFinMinijuego, this.instrucciones,
				this.musica);

		// Muestro la otra
		ventanaResultados.setVisible(true);

		// Cierro la ventana
		this.dispose();
	}

}
