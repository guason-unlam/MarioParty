package juego;

public abstract class Constantes {
	public static final String ASSETS_PATH = "assets/";
	public static final String IMAGEN_PATH = "img/";
	public static final String SOUND_PATH = "sound/";
	public static final String MUSICA_LOGIN = ASSETS_PATH + SOUND_PATH + "service-bell_daniel_simion.wav";

	public static final int VENTANA_WIDTH = 800;
	public static final int VENTANA_HEIGHT = 600;

	public static final int BOTON_WIDTH = 130;
	public static final int BOTON_HEIGHT = 40;

	public static final int MAPA_WIDTH = 640;
	public static final int MAPA_HEIGHT = 480;

	public static final int LOGIN_WIDTH = 454;
	public static final int LOGIN_HEIGHT = 290;

	public static final String LOGO_PATH = ASSETS_PATH + IMAGEN_PATH + "logo.png";
	public static final String TABLERO1_PATH = ASSETS_PATH + IMAGEN_PATH + "tablero01.jpg";

	// Casilleros
	public static final int CASILLERO_WIDTH = 50;
	public static final int CASILLERO_HEIGHT = 50;

	// LOGIN
	public static final String LOGIN_REQUEST = "login";
	public static final String INCORRECT_LOGIN = "loginIncorrecto";
	public static final String DUPLICATED_LOGIN = "loginDuplicado";
	public static final String CORRECT_LOGIN = "loginCorrecto";
	public static final String LOGIN_REQUEST_SERVER = "loginRequestServer";
	public static final String LOGIN_REQUEST_SERVER_CORRECT = "loginCorrectServer";

	// REGISTER
	public static final String REGISTER_REQUEST = "register";
	public static final String REGISTER_INCORRECT = "registerIncorrecto";
	public static final String REGISTER_CORRECT = "registerCorrecto";
	public static final String REGISTER_DUPLICATED = "registerDuplicado";

	// SERVIDOR
	public static final String IP = "0.0.0.0";
	public static final int PUERTO = 7777;
	public static final int MAXIMAS_CONEXIONES_SIMULTANEAS = 2;

	// PAR CLIENTE->SERVIDOR
	public static final int PUERTO_SALIDA_CLIENTE = 7775;
	public static final int PUERTO_ENTRADA_SERVIDOR = 7777;

	// PAR SERVIDOR->CLIENTE
	public static final int PUERTO_ENTRADA_CLIENTE = 7774;
	public static final int PUERTO_SALIDA_SERVIDOR = 7776;

	public static final String LOGIN_REQUEST_SV_CLIENTE = "loginRequestSvCliente";
	public static final String REGISTER_REQUEST_INCORRECT = "registerRequestIncorrect";

}
