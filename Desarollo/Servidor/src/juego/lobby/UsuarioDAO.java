package juego.lobby;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import juego.tablero.casillero.Casillero;
import servidor.Servidor;

public class UsuarioDAO {

	@SuppressWarnings("unused")
	public static Usuario loguear(String username, String password) {
		Transaction tx = null;

		try {
			String query = "SELECT u FROM Usuario u WHERE u.username = '" + username + "' AND u.password = '" + password
					+ "'";
			Query queryLogueo = Servidor.getSessionHibernate().createQuery(query);
			try {
				Usuario user = (Usuario) queryLogueo.getSingleResult();
				return new Usuario(user.getId(), username, password);
			} catch (NoResultException e) {
				return null;
			}

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public static int registrar(String username, String hashPassword) {
		Transaction txReg = null;

		try {
			txReg = Servidor.getSessionHibernate().beginTransaction();

			Query chequearDuplicado = Servidor.getSessionHibernate()
					.createQuery("SELECT u FROM Usuario u WHERE u.username = '" + username + "'");

			List<Usuario> resultList = chequearDuplicado.getResultList();

			if (!resultList.isEmpty()) {
				return 1;

			} else {

				Query queryMaxID = Servidor.getSessionHibernate().createQuery("SELECT max(u.id) FROM Usuario u");
				List<Integer> id = queryMaxID.getResultList();
				Usuario registrar = null;

				if (id.size() == 1 && id.get(0) == null) {
					registrar = new Usuario(1, username, hashPassword);
				} else {
					registrar = new Usuario(id.get(0).intValue() + 1, username, hashPassword);
				}
				Servidor.getSessionHibernate().save(registrar);
				return 0;
			}

		} catch (HibernateException e) {
			if (txReg != null)
				txReg.rollback();
			e.printStackTrace();
		} finally {
			txReg.commit();
		}
		return -1;
	}

	public static boolean updateEstadisticas(Usuario usuario) {
		Transaction txReg = null;

		try {
			txReg = Servidor.getSessionHibernate().beginTransaction();
			Servidor.getSessionHibernate().merge(usuario);
			return true;
		} catch (HibernateException e) {
			if (txReg != null)
				txReg.rollback();
			e.printStackTrace();
		} finally {
			txReg.commit();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> obtenerEstadisticas(String username) {
		Map<String, Object> estadisticas = new HashMap<>();
		Transaction tx = null;
		try {
			Query chequearDuplicado = Servidor.getSessionHibernate()
					.createQuery("SELECT u FROM Usuario u WHERE u.username = '" + username + "'");

			Usuario usuario = (Usuario) chequearDuplicado.getSingleResult();

			String query = "SELECT " + "COUNT(DISTINCT ronda.id) as rondasJugadas, "
					+ "COUNT(DISTINCT partida.id) as cantidadPartidas, " + "SUM(cantidad_monedas) as cantidadMonedas, "
					+ "SUM(minijuegos_ganados) as minijuegosGanados, " + "SUM(puntos_totales) as puntosAcumulados "
					+ "FROM EstadisticasPartida " + "INNER JOIN partida ON partida.id = EstadisticasPartida.partida "
					+ "INNER JOIN ronda ON ronda.partida = partida.id " + "WHERE EstadisticasPartida.usuario = "
					+ String.valueOf(usuario.getId());

			Query queryStats1 = Servidor.getSessionHibernate().createSQLQuery(query);
			List<Object[]> rows = queryStats1.getResultList();
			for (Object[] row : rows) {
				estadisticas.put("rondasJugadas", String.valueOf(row[0]));
				estadisticas.put("cantidadPartidas", String.valueOf(row[1]));
				estadisticas.put("cantidadMonedas", String.valueOf(row[2]));
				estadisticas.put("minijuegosGanados", String.valueOf(row[3]));
				estadisticas.put("puntosAcumulados", String.valueOf(row[4]));
			}

			String query2 = "SELECT nombre, MAX(cantidad)\r\n" + "FROM(\r\n" + "SELECT \r\n"
					+ "	MiniJuego.nombre as nombre, COUNT(*) as cantidad\r\n" + "FROM EstadisticasPartida\r\n"
					+ "INNER JOIN partida ON partida.id = EstadisticasPartida.partida\r\n"
					+ "INNER JOIN ronda ON ronda.partida = partida.id\r\n"
					+ "INNER JOIN MiniJuego ON MiniJuego.id = ronda.minigame\r\n"
					+ "WHERE EstadisticasPartida.usuario = " + String.valueOf(usuario.getId()) + "\r\n"
					+ "GROUP BY minigame) a";

			Query queryMinijuegoMasJugado = Servidor.getSessionHibernate().createSQLQuery(query2);
			List<Object[]> rowsQueryMinijuegoMasJugado = queryMinijuegoMasJugado.getResultList();
			for (Object[] row : rowsQueryMinijuegoMasJugado) {
				estadisticas.put("minijuegoMasJugado", String.valueOf(row[0]));
			}

			String query3 = "SELECT win_condition, MAX(cantidad)\r\n" + "FROM(\r\n" + "SELECT \r\n"
					+ "	win_condition, COUNT(*) as cantidad\r\n" + "FROM EstadisticasPartida\r\n"
					+ "INNER JOIN partida ON partida.id = EstadisticasPartida.partida\r\n"
					+ "INNER JOIN ronda ON ronda.partida = partida.id\r\n" + "WHERE EstadisticasPartida.usuario = "
					+ String.valueOf(usuario.getId()) + "\r\n" + "GROUP BY win_condition\r\n" + ") a";

			Query queryModoMasJugado = Servidor.getSessionHibernate().createSQLQuery(query3);
			List<Object[]> rowsQueryModoMasJugado = queryModoMasJugado.getResultList();
			for (Object[] row : rowsQueryModoMasJugado) {
				estadisticas.put("modoMasJugado", String.valueOf(row[0]));
			}

			String queryPartidasGanadas = "SELECT \r\n" + "	 COUNT(DISTINCT partida.id) as partidasGanadas\r\n"
					+ "FROM EstadisticasPartida\r\n"
					+ "INNER JOIN partida ON partida.id = EstadisticasPartida.partida\r\n"
					+ "INNER JOIN ronda ON ronda.partida = partida.id\r\n" + "WHERE EstadisticasPartida.usuario = "
					+ String.valueOf(usuario.getId()) + " and Partida.winner = " + String.valueOf(usuario.getId());

			Query qPartidasGanadas = Servidor.getSessionHibernate().createSQLQuery(queryPartidasGanadas);
			int rowsPartidasGanadas = qPartidasGanadas.getSingleResult() == null ? 0
					: (int) qPartidasGanadas.getSingleResult();

			estadisticas.put("partidasGanadas", String.valueOf(rowsPartidasGanadas));

			String maximoPuntaje = "SELECT \r\n" + "			MAX(puntos_totales) as maximoPuntaje\r\n"
					+ "			 FROM EstadisticasPartida\r\n" + "\r\n"
					+ "			WHERE EstadisticasPartida.usuario = " + String.valueOf(usuario.getId());

			Query qMaxPuntaje = Servidor.getSessionHibernate().createSQLQuery(maximoPuntaje);
			int rowsMaxPuntaje = qMaxPuntaje.getSingleResult() == null ? 0 : (int) qMaxPuntaje.getSingleResult();

			estadisticas.put("puntajeMaximo", String.valueOf(rowsMaxPuntaje));

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return estadisticas;
	}

	@SuppressWarnings("unchecked")
	public static JsonArrayBuilder obtenerEstadisticasHistorial(String username) {

		try {
			Query chequearDuplicado = Servidor.getSessionHibernate()
					.createQuery("SELECT u FROM Usuario u WHERE u.username = '" + username + "'");

			Usuario usuario = (Usuario) chequearDuplicado.getSingleResult();

			String query = "SELECT partida.id as numeroPartida, map, usuario.user as ganador, highscore FROM partida INNER JOIN (select partida from EstadisticasPartida where usuario = "
					+ String.valueOf(usuario.getId())
					+ ") t ON t.partida = partida.id INNER JOIN usuario ON usuario.id = partida.winner";

			Query queryStats1 = Servidor.getSessionHibernate().createSQLQuery(query);
			List<Object[]> rows = queryStats1.getResultList();
			JsonArrayBuilder datosHistoricos = Json.createArrayBuilder();
			for (Object[] row : rows) {
				datosHistoricos.add(toJson(row));
			}

			return datosHistoricos;
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static JsonObject toJson(Object[] row) {
//		JsonObjectBuilder builder = Json.createObjectBuilder();
//		builder.add("id", (int) row[0]);
		return Json.createObjectBuilder().add("numeroPartida", String.valueOf(row[0]))
				.add("map", String.valueOf(row[1])).add("ganador", String.valueOf(row[2]))
				.add("highscore", String.valueOf(row[3])).build();
	}

}
