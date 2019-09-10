package juego;

import java.util.ArrayList;

public class Sala {
	private String nombre;
	private String password;
	private boolean salaLlena = false;
	private int capacidadMaxima = 0;
	private int capacidadActual;
	private ArrayList<Usuario> jugadoresActivos = new ArrayList<Usuario>();
	private Usuario usuarioCreador;

	public Sala(String nombreSala, String passwordSala, int capacidadMaxima, Usuario usuarioCreador) {
		this.nombre = nombreSala;
		this.password = passwordSala;
		this.capacidadMaxima = capacidadMaxima;
		this.usuarioCreador = usuarioCreador;
	}

	public Sala(String nombreSala, int capacidadMaxima, Usuario usuarioCreador) {
		this.nombre = nombreSala;
		this.password = "";
		this.capacidadMaxima = capacidadMaxima;
		this.usuarioCreador = usuarioCreador;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Usuario getUsuarioCreador() {
		return usuarioCreador;
	}

	public void setUsuarioCreador(Usuario usuarioCreador) {
		this.usuarioCreador = usuarioCreador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isSalaLlena() {
		return salaLlena;
	}

	public void setSalaLlena(boolean salaLlena) {
		this.salaLlena = salaLlena;
	}

	public ArrayList<Usuario> getJugadoresActivos() {
		return jugadoresActivos;
	}

	public void setJugadoresActivos(ArrayList<Usuario> jugadoresActivos) {
		this.jugadoresActivos = jugadoresActivos;
	}

	public int getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public void setCapacidadMaxima(int capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}

	public int getCapacidadActual() {
		return capacidadActual;
	}

	public void setCapacidadActual(int capacidadActual) {
		this.capacidadActual = capacidadActual;
	}

	public Usuario getJugadorCreador() {
		return usuarioCreador;
	}

	public void setJugadorCreador(Usuario jugadorCreador) {
		this.usuarioCreador = jugadorCreador;
	}

	public boolean esAdmin(Usuario user) {
		return this.usuarioCreador.getUsername().equals(user.getUsername());
	}

	public boolean conectarseALaSala(Usuario usuario) {
		if (this.capacidadActual < this.capacidadMaxima) {
			this.jugadoresActivos.add(usuario);
			this.capacidadActual++;
			return true;
		}
		this.salaLlena = true;
		return false;
	}

	public boolean sacarUsuarioDeSala(Usuario usuario) {
		if (this.jugadoresActivos.remove(usuario)) {
			this.capacidadActual--;
			return true;
		}
		if (this.salaLlena) {
			this.salaLlena = false;
		}

		return false;

	}

}
