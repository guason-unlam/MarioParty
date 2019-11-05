package juego.misc;

public class ExcepcionArchivos extends Exception {
	/**
	* 
	*/
	private static final long serialVersionUID = 1833001021912935112L;
	int id;

	public ExcepcionArchivos(String message) {
		super(message);
	}
}