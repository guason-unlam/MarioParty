package cliente;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Musica {

	private Clip melodia;

	public Musica(final String path) {
		System.out.println(path);
		try {
			InputStream is = ClassLoader.class.getResourceAsStream(path);
			AudioInputStream ais = AudioSystem.getAudioInputStream(is);
			DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
			this.melodia = (Clip) AudioSystem.getLine(info);
			this.melodia.open(ais);
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void detener() {
		this.melodia.stop();
		this.melodia.flush();
	}

	public void reproducir() {
		this.melodia.stop();
		this.melodia.flush();
		this.melodia.setMicrosecondPosition(0);
		this.melodia.start();
	}

	public void repetir() {
		this.melodia.stop();
		this.melodia.flush();
		this.melodia.setMicrosecondPosition(0);

		this.melodia.loop(Clip.LOOP_CONTINUOUSLY);
	}

}
