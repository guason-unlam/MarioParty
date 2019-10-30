package juego.lobby;

public class ExcepcionJugadoresInsuficientes extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7495595027250279723L;
	private int id;
	
	public ExcepcionJugadoresInsuficientes(int id) {
		super();
		this.id = id;
	}
	
	@Override
	public String getMessage() {
		String message = "";
		switch(id) {
		case 0:
			message = "No se puede crear partida.\n\t>Jugadores Insuficientes.";
			break;
		}
		return message;
	}
	
	public int getExceptionId() {
		return id;
	}
	
}
