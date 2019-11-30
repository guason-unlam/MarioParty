package juego.ventana;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.json.JsonArray;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VentanaHistorial extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 295124723881461720L;
	private JFrame lobby;
	private JPanel panel;
	private JButton btnVolver;
	private JTable tableJugadas;
	private JsonArray datosTablaFormateados;
	private ModelTableRooms modelTableRooms = new ModelTableRooms();

	public VentanaHistorial(Map<String, Object> estadisticas, JsonArray datosTablaFormateados, JFrame lobby) {
		// Me guardo la referencia para hacerlo visible, etc
		this.lobby = lobby;
		this.datosTablaFormateados = datosTablaFormateados;
		setTitle("Historial de partidas");
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		setBounds(0, 0, 700, 450);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnVolver.setBounds(10, 364, 111, 46);
		panel.add(btnVolver);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 446, 308);
		panel.add(scrollPane);

		tableJugadas = new JTable();
		scrollPane.setViewportView(tableJugadas);

		JLabel lblEstadisticasGenerales = new JLabel("Estadisticas Generales");
		lblEstadisticasGenerales.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadisticasGenerales.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEstadisticasGenerales.setBounds(466, 22, 216, 59);
		panel.add(lblEstadisticasGenerales);

		JLabel lblPartidasJugadas = new JLabel("Partidas jugadas");
		lblPartidasJugadas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPartidasJugadas.setBounds(466, 69, 105, 25);
		panel.add(lblPartidasJugadas);

		JLabel partidasJugadasLabel = new JLabel(((String) estadisticas.get("cantidadPartidas")).equals("null") ? "0"
				: (String) estadisticas.get("cantidadPartidas"));
		partidasJugadasLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		partidasJugadasLabel.setBounds(604, 74, 30, 14);
		panel.add(partidasJugadasLabel);

		JLabel lblRondasJugadas = new JLabel("Rondas jugadas");
		lblRondasJugadas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRondasJugadas.setBounds(466, 105, 105, 25);
		panel.add(lblRondasJugadas);

		JLabel rondasJugadasLabel = new JLabel(((String) estadisticas.get("rondasJugadas")).equals("null") ? "0"
				: (String) estadisticas.get("rondasJugadas"));
		rondasJugadasLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rondasJugadasLabel.setBounds(604, 110, 30, 14);
		panel.add(rondasJugadasLabel);

		JLabel lblPuntajeMximo = new JLabel("Puntaje m\u00E1ximo");
		lblPuntajeMximo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPuntajeMximo.setBounds(466, 135, 105, 25);
		panel.add(lblPuntajeMximo);

		JLabel puntajeMaximoLabel = new JLabel(((String) estadisticas.get("puntajeMaximo")).equals("null") ? "0"
				: (String) estadisticas.get("puntajeMaximo"));
		puntajeMaximoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		puntajeMaximoLabel.setBounds(604, 140, 30, 14);
		panel.add(puntajeMaximoLabel);

		JLabel lblModoMsJugado = new JLabel("Modo m\u00E1s jugado");
		lblModoMsJugado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblModoMsJugado.setBounds(466, 165, 128, 25);
		panel.add(lblModoMsJugado);

		JLabel modoMasJugadoLabel = new JLabel(((String) estadisticas.get("modoMasJugado")).equals("null") ? "-"
				: (String) estadisticas.get("modoMasJugado"));
		modoMasJugadoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		modoMasJugadoLabel.setBounds(604, 170, 78, 14);
		panel.add(modoMasJugadoLabel);

		JLabel lblPartidasGanadas = new JLabel("Partidas ganadas");
		lblPartidasGanadas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPartidasGanadas.setBounds(466, 195, 105, 25);
		panel.add(lblPartidasGanadas);

		JLabel partidasGanadasLabel = new JLabel(((String) estadisticas.get("partidasGanadas")).equals("null") ? "0"
				: (String) estadisticas.get("partidasGanadas"));
		partidasGanadasLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		partidasGanadasLabel.setBounds(604, 200, 30, 14);
		panel.add(partidasGanadasLabel);

		JLabel lblDolaresAcumulados = new JLabel("Minijuegos ganados");
		lblDolaresAcumulados.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDolaresAcumulados.setBounds(466, 231, 128, 25);
		panel.add(lblDolaresAcumulados);

		JLabel minijuegosGanadosLabel = new JLabel(((String) estadisticas.get("minijuegosGanados")).equals("null") ? "0"
				: (String) estadisticas.get("minijuegosGanados"));
		minijuegosGanadosLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		minijuegosGanadosLabel.setBounds(604, 236, 30, 14);
		panel.add(minijuegosGanadosLabel);

		JLabel lblMinijuegoMsJugado = new JLabel("Minijuego m\u00E1s jugado");
		lblMinijuegoMsJugado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMinijuegoMsJugado.setBounds(466, 261, 140, 25);
		panel.add(lblMinijuegoMsJugado);

		JLabel minijuegoMasJugadoLabel = new JLabel(
				((String) estadisticas.get("minijuegoMasJugado")).equals("null") ? "-"
						: (String) estadisticas.get("minijuegoMasJugado"));
		minijuegoMasJugadoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		minijuegoMasJugadoLabel.setBounds(604, 266, 78, 14);
		panel.add(minijuegoMasJugadoLabel);

		JLabel lblPartidas = new JLabel("Partidas jugadas");
		lblPartidas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPartidas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPartidas.setBounds(124, 0, 216, 46);
		panel.add(lblPartidas);

		JLabel lblPuntosAcumulados = new JLabel("Puntos acumulados");
		lblPuntosAcumulados.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPuntosAcumulados.setBounds(466, 291, 128, 25);
		panel.add(lblPuntosAcumulados);

		JLabel puntosAcumuladosLabel = new JLabel(((String) estadisticas.get("puntosAcumulados")).equals("null") ? "0"
				: (String) estadisticas.get("puntosAcumulados"));
		puntosAcumuladosLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		puntosAcumuladosLabel.setBounds(604, 296, 30, 14);
		panel.add(puntosAcumuladosLabel);

		JLabel lblMonedasAcumuladas = new JLabel("Monedas acumuladas");
		lblMonedasAcumuladas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMonedasAcumuladas.setBounds(466, 328, 140, 25);
		panel.add(lblMonedasAcumuladas);

		JLabel monedasAcumuladasLabel = new JLabel(((String) estadisticas.get("cantidadMonedas")).equals("null") ? "0"
				: (String) estadisticas.get("cantidadMonedas"));
		monedasAcumuladasLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		monedasAcumuladasLabel.setBounds(604, 333, 30, 14);
		panel.add(monedasAcumuladasLabel);

		tablaPartidasJugadas(datosTablaFormateados);
		this.addListener();
	}

	public void tablaPartidasJugadas(JsonArray datosHistoricos) {
		String data[][] = new String[datosHistoricos.size()][4];

		for (int i = 0; i < datosHistoricos.size(); i++) {

			data[i][0] = datosHistoricos.getJsonObject(i).getString("numeroPartida");

			switch (Integer.valueOf(datosHistoricos.getJsonObject(i).getString("map"))) {
			case 1:
				data[i][1] = "Pequeño";
				break;
			case 2:
				data[i][1] = "Mediano";
				break;
			case 3:
				data[i][1] = "Grande";
				break;
			default:
				data[i][1] = "Sin definir";
				break;
			}

			data[i][2] = datosHistoricos.getJsonObject(i).getString("ganador");
			data[i][3] = datosHistoricos.getJsonObject(i).getString("highscore");
		}

		if (datosHistoricos.isEmpty()) {
			this.modelTableRooms.setTableEmpty();
			this.modelTableRooms.fireTableStructureChanged();
		} else {
			this.modelTableRooms.setData(data);
		}

		this.modelTableRooms.fireTableDataChanged();
		this.tableJugadas.setModel(this.modelTableRooms);
	}

	private void addListener() {

		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lobby.setVisible(true);
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
}
