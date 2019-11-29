package juego.lobby;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import juego.personas.Jugador;
import juego.tablero.MiniJuego;
import juego.tablero.Tablero;
import servidor.Servidor;

/*
 * Esta clase igual se podria reemplazar por el loop en la clase partida y 
 * lanzar despues del loop el minijuego, pero bueno, ya estaba 
 * implementada por algo supongo.
 * */
public class Ronda implements Serializable {

	private static final long serialVersionUID = -3765719165739391662L;
	private Tablero tablero;
	private int segundosTranscurridos = 0;
	private long currentTimeMillis;
	private int numeroRonda = 0;

//	Falta la referencia al minijuego, pero como aun no esta creado no lo pongo, pero deber�a ser un
//	ArrayList de minijuegos, y elejis uno al azar que no haya salido	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic
	private int id;
	@Column(name = "minigame")
	private MiniJuego minijuego;
	private List<Jugador> jugadoresEnPartida;
	private int jugadoresRestantes;
	private boolean jugando = false;

	public Ronda(ArrayList<Jugador> jugadoresEnPartida, Tablero tablero) {

		this.jugadoresEnPartida = jugadoresEnPartida;
		this.jugadoresRestantes = jugadoresEnPartida.size();
		this.tablero = tablero;
//		minijuego = new MejorDeDiez(this.jugadoresEnPartida);
	}

	/*
	 * Devuelve un int para devolver distintos tipos de error se puede cambiar a
	 * void y lanzar excepciones
	 */
	public void iniciar(boolean primeraRonda) {
//		jugadoresRestantes = jugadoresEnPartida.size();
//		if(primeraRonda) {//al ser la primera ronda, los jugadores tiran el dado para decidir el orden de los turnos
//			for (Jugador jugador : jugadoresEnPartida) {
//				JPanel panel = new JPanel();
//				JLabel lblNombre = new JLabel(jugador.getNombre());
//				panel.setBounds(0, 400, 300, 100);
//				panel.setBackground(Color.BLACK);
//				lblNombre.setBounds(10, 10, 100, 50);
//				lblNombre.setText("JAMON");
//				lblNombre.setVisible(true);
//				panel.setVisible(true);
//				panel.add(lblNombre);
//				ventanaJuego.getPanelJuego().add(panel);
//			}
//		}
//		for (Jugador jug : jugadoresEnPartida) {
//			Turno t = new Turno(jug);
//			t.iniciarTurno();
//			System.out.println("");
//			// jug.tirarDado();
//		}
//		((MejorDeDiez) minijuego).iniciar();
//		Lanzo minijuego cuando lo tenga
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MiniJuego getMinijuego() {
		return minijuego;
	}

	public void setMinijuego(MiniJuego minijuego) {
		this.minijuego = minijuego;
	}

	public void start() {
		boolean puedeActualizar = true;
		this.jugando = true;
		try {

			Servidor.actualizarJuego(this);
			Thread.sleep(1000);

			long tiempoInicial = System.currentTimeMillis();
			while (puedeActualizar && this.jugando) {
				this.currentTimeMillis = System.currentTimeMillis();
				this.tablero.actualizar();

				puedeActualizar = Servidor.actualizarJuego(this);

				Thread.sleep(100);
				this.segundosTranscurridos = (int) (System.currentTimeMillis() - tiempoInicial) / 1000;
			}
			this.jugando = false;
			Servidor.actualizarJuego(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public List<Jugador> getJugadoresEnPartida() {
		return jugadoresEnPartida;
	}

	public void setJugadoresEnPartida(List<Jugador> jugadoresEnPartida) {
		this.jugadoresEnPartida = jugadoresEnPartida;
	}

	public int getJugadoresRestantes() {
		return jugadoresRestantes;
	}

	public void setJugadoresRestantes(int jugadoresRestantes) {
		this.jugadoresRestantes = jugadoresRestantes;
	}

	public boolean isJugando() {
		return jugando;
	}

	public void setJugando(boolean jugando) {
		this.jugando = jugando;
	}

	public JsonObject toJson() {
		return Json.createObjectBuilder().add("terminado", this.terminado())
				.add("tiempoTranscurrido", this.segundosTranscurridos).add("currentTimeMillis", this.currentTimeMillis)
				.add("numeroRonda", this.numeroRonda).build();
	}

	public void setRonda(int rondaEnCurso) {
		this.numeroRonda = rondaEnCurso;
	}

	public boolean terminado() {
		return this.jugando == false;
	}

	public void parar() {
		this.jugando = false;
	}
//	public class decidirOrdenTurnos{
//		SortedList<resultadoLanzamiento> orden;
//		
//		public void agregarResultado(resultadoLanzamiento resultado) {
////			this.orden.add(resultado);
////			resultadosDados.values().
//		}
//	}

//	public class resultadoLanzamiento implements Comparable<resultadoLanzamiento>{
//		Jugador jugador;
//		int resultado;
//		
//		public int getResultado() { return this.resultado;}
//
//		@Override
//		public int compareTo(resultadoLanzamiento resultado) {
//			return this.resultado - resultado.getResultado();
//		}
//		
//		
//	}
//	public void decidirOrdenTurnos() {
//		ventanaJuego.pedirAccion(jugadoresEnPartida.get(jugadoresRestantes-1));
//	}

}
