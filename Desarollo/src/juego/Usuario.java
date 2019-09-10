package juego;

public class Usuario {
	private String username;
	private String password;
	private int puntaje;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public Usuario(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
