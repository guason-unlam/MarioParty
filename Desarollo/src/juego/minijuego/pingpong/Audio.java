package juego.minijuego;

import java.applet.AudioClip;

public class Audio {

	public AudioClip getAudio(String direccion) {
		return java.applet.Applet.newAudioClip(getClass().getResource(direccion));
	}

}
