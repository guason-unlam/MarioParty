package servidor;

import juego.Constantes;
import juego.lobby.Usuario;

public class Bot extends Usuario {
	public Bot() {
		//Uso el constructor de nombre y pw
		super("Bot" + Constantes.NUMERO_DE_BOT++, "");
	}
}
