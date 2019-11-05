package audio;

public interface Audio {
	public void play();

	public void pause();

	public void stop();

	public void close();

	public void loop();

	public void setCurrentSecs(long currentSecs);

	public long getCurrentSecs();

	public long getTotalSecs();
}
