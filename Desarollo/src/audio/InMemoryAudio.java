package audio;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;

public class InMemoryAudio implements Audio {
    private static final int MICROSECONDS_PER_SECOND = 1_000_000;
    private Clip clip;

    public InMemoryAudio(AudioInputStream stream)
            throws IOException, LineUnavailableException {
        DataLine.Info info = new DataLine.Info(Clip.class, stream.getFormat());
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(stream);
    }

    @Override
    public void play() {
        clip.start();
    }

    @Override
    public void pause() {
        clip.stop();
    }

    @Override
    public void stop() {
        clip.stop();
    }
    @Override
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    public void close() {
        clip.close();
    }

    @Override
    public void setCurrentSecs(long currentSecs) {
        clip.setMicrosecondPosition(currentSecs * MICROSECONDS_PER_SECOND);
    }

    @Override
    public long getCurrentSecs() {
        return (int) clip.getMicrosecondPosition() / MICROSECONDS_PER_SECOND;
    }

    @Override
    public long getTotalSecs() {
        return (int) clip.getMicrosecondLength() / MICROSECONDS_PER_SECOND;
    }
}
