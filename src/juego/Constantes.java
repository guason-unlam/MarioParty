package juego;

public abstract class Constantes {
	public static final String ASSETS_PATH = "assets/img/";
	public static final int VENTANA_WIDTH = 800;
	public static final int VENTANA_HEIGHT = 600;

	public static final int BOTON_WIDTH = 130;
	public static final int BOTON_HEIGHT = 40;

	public static final int MAPA_WIDTH = 640;
	public static final int MAPA_HEIGHT = 480;

	public static final int LOGIN_WIDTH = 454;
	public static final int LOGIN_HEIGHT = 290;
	public static final String LOGO_PATH = ASSETS_PATH + "logo.png";
	public static final String TABLERO1_PATH = ASSETS_PATH + "tablero01.jpg";

	// Casilleros
	public static final int CASILLERO_WIDTH = 50;
	public static final int CASILLERO_HEIGHT = 50;

	// LOGIN
	public static final String LOGIN_REQUEST = "login";
	public static final String INCORRECT_LOGIN = "loginIncorrecto";
	public static final String DUPLICATED_LOGIN = "loginDuplicado";
	public static final String CORRECT_LOGIN = "loginCorrecto";

	// REGISTER
	public static final String REGISTER_REQUEST = "register";
	public static final String REGISTER_INCORRECT = "registerIncorrecto";
	public static final String REGISTER_CORRECT = "registerCorrecto";
	public static final String REGISTER_DUPLICATED = "registerDuplicado";

	// SERVIDOR
	public static final String IP = "0.0.0.0";
	public static final int PUERTO = 7777;
	public static final int MAXIMAS_CONEXIONES_SIMULTANEAS = 2;
	public static final int SALIDA_CLIENTE = 7575;
	public static final int ENTRADA_CLIENTE = 7777;
}
