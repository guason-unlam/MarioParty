package juego.administracion;

import java.util.ArrayList;

public class Sala {
	private String nombre;
	private String password;
	private boolean salaLlena = false;
	private int capacidadMaxima = 0;
	private int capacidadActual;
	private ArrayList<Usuario> usuariosActivos = new ArrayList<Usuario>();
	private Usuario usuarioCreador;
	private Partida partidaActual;

	public Sala(String nombreSala, String passwordSala, int capacidadMaxima, Usuario usuarioCreador) {
		this.nombre = nombreSala;
		this.password = passwordSala;
		this.capacidadMaxima = capacidadMaxima;
		this.usuarioCreador = usuarioCreador;
		this.partidaActual = null;
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
		return usuariosActivos;
	}

	public void setJugadoresActivos(ArrayList<Usuario> jugadoresActivos) {
		this.usuariosActivos = jugadoresActivos;
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
			usuario.setSala(this);
			this.usuariosActivos.add(usuario);
			this.capacidadActual++;
			return true;
		}
		this.salaLlena = true;
		return false;
	}

	public boolean sacarUsuarioDeSala(Usuario usuario) {
		if (this.usuariosActivos.remove(usuario)) {
			this.capacidadActual--;
			usuario.setSala(null);
			return true;
		}
		if (this.salaLlena) {
			this.salaLlena = false;
		}

		return false;

	}

	public ArrayList<Usuario> getUsuariosActivos() {
		return usuariosActivos;
	}

	public void setUsuariosActivos(ArrayList<Usuario> usuariosActivos) {
		this.usuariosActivos = usuariosActivos;
	}

	/*
	 * Para llevar un control de que estoy jugando
	 */
	public Partida getPartidaActual() {
		return partidaActual;
	}

	public void setPartidaActual(Partida partidaActual) {
		this.partidaActual = partidaActual;
	}

}
