package juego.lobby;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

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
}
