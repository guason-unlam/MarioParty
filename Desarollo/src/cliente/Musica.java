package cliente;

import java.io.File;

import audio.Audio;
import audio.AudioFactory;

public class Musica {
	private Audio audio;

	public Musica(final String path) {
		File file = new File(path);
		if (file != null) {
			audio = AudioFactory.getInstance().getAudio(file);
			audio.loop();
		}
	}
}
