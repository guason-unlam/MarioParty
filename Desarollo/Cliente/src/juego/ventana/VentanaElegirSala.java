package juego.ventana;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import juego.Constantes;

public class VentanaElegirSala extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8985049683108599509L;
	private JFrame lobby;
	private JPanel panel;
	private JButton btnJoin;
	private JButton btnVolver;
	private JTable tableSalas;
	private String sala;
	private ModelTableRooms modelTableRooms = new ModelTableRooms();

	public VentanaElegirSala(JFrame ventanaLobby) {
		// Me guardo la referencia para hacerlo visible, etc
		this.lobby = ventanaLobby;

		setTitle("Salas disponibles");
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
		setLocationRelativeTo(this.lobby);

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
		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (!(sala == null)) {
					entrarEnSala(sala);
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una sala", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		tableSalas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				// El doble click
				if (event.getClickCount() == 2) {
					if (!(sala == null)) {
						entrarEnSala(sala);
					} else {
						JOptionPane.showMessageDialog(null, "Seleccione una sala", "Sala no seleccionada",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					int fila = tableSalas.rowAtPoint(event.getPoint());
					sala = (String) tableSalas.getValueAt(fila, 0);

				}
			}

		});

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

	private void entrarEnSala(String sala) {
		if (Cliente.getConexionInterna().unirseASala(sala)) {
			VentanaAdministracionSala ventanaSala = new VentanaAdministracionSala(lobby, sala, false);
			Coordinador.setVentanaAdministracionSala(ventanaSala);

			JsonObject joinRoomRequest = Json.createObjectBuilder().add("type", Constantes.JOIN_ROOM_SV_REQUEST)
					.add("sala", sala).build();

			Cliente.getConexionServidor().enviarAlServidor(joinRoomRequest);

			this.setVisible(false);
			ventanaSala.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Sala llena o con partida en curso, por favor seleccione otra.",
					"Error al ingresar", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void indexSalas(JsonArray datosDeSalasDisponibles) {
		String data[][] = new String[datosDeSalasDisponibles.size()][4];

		for (int i = 0; i < datosDeSalasDisponibles.size(); i++) {

			data[i][0] = datosDeSalasDisponibles.getJsonObject(i).getString("nombre");
			data[i][1] = (datosDeSalasDisponibles.getJsonObject(i).getString("capacidadActual") + "/"
					+ datosDeSalasDisponibles.getJsonObject(i).getString("capacidadMaxima"));
			data[i][2] = datosDeSalasDisponibles.getJsonObject(i).getString("admin");
			data[i][3] = datosDeSalasDisponibles.getJsonObject(i).getString("salaPrivada");
		}

		if (datosDeSalasDisponibles.isEmpty()) {
			this.modelTableRooms.setTableEmpty();
			this.modelTableRooms.fireTableStructureChanged();
		} else {
			this.modelTableRooms.setData(data);
		}

		this.modelTableRooms.fireTableDataChanged();
		this.tableSalas.setModel(this.modelTableRooms);
	}

}
