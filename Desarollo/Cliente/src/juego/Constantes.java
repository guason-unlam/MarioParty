package juego;

public abstract class Constantes {
	public static final String ASSETS_PATH = "assets/";
	public static final String IMAGEN_PATH = "img/";
	public static final String SOUND_PATH = "sound/";
	public static final String MUSICA_LOGIN = ASSETS_PATH + SOUND_PATH + "login.wav";
	public static final String MUSICA_SELECT = ASSETS_PATH + SOUND_PATH + "sala.wav";

	// SERVIDOR
	public static final String IP = "192.168.1.10";
	public static final int MAXIMAS_CONEXIONES_SIMULTANEAS = 10;

	// PAR CLIENTE->SERVIDOR
	public static final int PUERTO_1 = 7777;
	public static final int PUERTO_2 = 7778;

	// PAR SERVIDOR->CLIENTE
	public static final int PUERTO_3 = 7779;
	public static final int PUERTO_4 = 7780;

	// ASSETS
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

	public static final int CANTIDAD_PERSONAJES = 3;
	public static final int NUMBER_OF_SPOTS = 4;
	public static final int NUMBER_OF_ROADS = 1;
	// Casilleros
	public static final int CASILLERO_WIDTH = 50;
	public static final int CASILLERO_HEIGHT = 50;

	// LOGIN
	public static final String LOGIN_REQUEST = "login";
	public static final String LOGOUT_REQUEST = "logout";
	public static final String INCORRECT_LOGOUT = "logoutIncorrecto";
	public static final String CORRECT_LOGOUT = "logoutCorrecto";
	public static final String INCORRECT_LOGIN = "loginIncorrecto";
	public static final String DUPLICATED_LOGIN = "loginDuplicado";
	public static final String CORRECT_LOGIN = "loginCorrecto";
	public static final String LOGIN_REQUEST_SERVER = "loginRequestServer";
	public static final String LOGIN_REQUEST_SERVER_CORRECT = "loginCorrectServer";
	public static final String LOGIN_REQUEST_SV_CLIENTE = "loginRequestSvCliente";

	// REGISTER
	public static final String REGISTER_REQUEST = "register";
	public static final String REGISTER_INCORRECT = "registerIncorrecto";
	public static final String REGISTER_CORRECT = "registerCorrecto";
	public static final String REGISTER_DUPLICATED = "registerDuplicado";
	public static final String REGISTER_REQUEST_INCORRECT = "registerRequestIncorrect";

	// JOIN ROOM
	public static final String INDEX_SALAS = "indexSalas";
	public static final String JOIN_ROOM = "joinRoom";

	// CREATE ROOM
	public static final String CREATE_ROOM_REQUEST = "createRoom";
	public static final String CREATE_ROOM_CORRECT = "createRoomOK";
	public static final String CREATE_ROOM_INCORRECT = "createRoomFail";
	public static final String EYE_ICON = ASSETS_PATH + IMAGEN_PATH + "eye.png";
	public static final String CREATE_ROOM = "createRoom";
	public static final String JOIN_ROOM_REQUEST = "joinRoom";
	public static final String JOIN_ROOM_CORRECT = "joinRoomOK";
	public static final String JOIN_ROOM_INCORRECT = "joinRoomFail";
	public static final String REQUEST_EXIT_ROOM = "requestExitRoom";
	public static final String LEAVE_ROOM_REQUEST = "leaveRoom";
	public static final String LEAVE_ROOM_CORRECT = "leaveRoomOK";
	public static final String LEAVE_ROOM_INCORRECT = "leaveRoomFail";
	public static final String REQUEST_REFRESH_ROOMS = "updateRooms";
	public static final String CREATE_ROOM_SV_REQUEST = "creeSala";
	public static final String JOIN_ROOM_SV_REQUEST = "unionSala";
	public static final String EXIT_ROOM_REQUEST = "leaveSala";
	public static final String INDEX_ROOM_REQUEST = "verSala";
	public static final String REFRESH_ALL_ROOMS = "refreshSalas";
	public static final String REFRESH_ROOM = "refreshRoom";
	public static final String REFRESH_PARAM_ROOM = "refreshParamRoom";
	public static final String JOIN_ROOM_PARAM = "parametersAdmin";
	public static final String LOGIN_REQUEST_SV_OK = "loginServerClienteOK";
	public static final String LOGIN_REQUEST_SV = "loginRequest";
	public static final String ARROW_LEFT = ASSETS_PATH + IMAGEN_PATH + "arrow-left.png";
	public static final String ARROW_RIGHT = ASSETS_PATH + IMAGEN_PATH + "arrow-right.png";
	public static final String FIN_SALA = "finSala";
	public static final String NOTICE_TODOS_EN_SALA = "ACKestanTodosEnSala!";
	public static final String TODOS_EN_SALA = "estanTodosEnSala";
	public static final String TOTAL_RONDAS = "totalRondas";
	public static final String NOTICE_ARRANCAR_JUEGO = "gogogo";
	public static final String MAPA = "mapa";
	public static final String CONDICION_VICTORIA = "condicionVictoria";
	public static final String CANTIDAD_BOTS = "cantidadBots";
	public static final String NOTICE_EMPEZA_JUEGO_CLIENTE = "arrancaLaPartidaaaaaa";
	public static final String JOIN_ROOM_REQUEST_PASSWORD = "JoinRoomPW";
	public static final String INCORRECT_PW = "pwIncorrecta";
	public static final String HISTORIAL = "historial";
	public static final String CORRECT_HISTORIAL = "historialOK";
	public static final String INCORRECT_HISTORIAL = "historialFail";
	public static final String TABLA_PARTIDAS = "TABLA_PARTIDAS_OK";
	public static final String SOLICITUD_TABLA_PARTIDAS = "solicitudTablaPartidas";
	public static final String INCORRECT_TABLA_PARTIDA = "tablaPartida";
	public static int NUMERO_DE_BOT = 1;
}
