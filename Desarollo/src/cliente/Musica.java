package cliente;

import java.io.File;

import audio.Audio;
import audio.AudioFactory;

public class Musica {
	private Audio audio;

	public Musica(final String path) {
		File file = new File(path);
		if (file != null) {
			this.audio = AudioFactory.getInstance().getAudio(file);
		}
	}

	public void loop() {
		this.audio.loop();
	}

	public void stop() {
		this.audio.stop();
	}

	public void play() {
		this.audio.play();
	}

	public void pause() {
		this.audio.pause();
	}
}
