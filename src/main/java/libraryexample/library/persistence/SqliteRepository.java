package dtu.library.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dtu.library.app.MediumRepository;
import dtu.library.app.UserRepository;
import dtu.library.domain.Medium;
import dtu.library.domain.User;

public class SqliteRepository implements MediumRepository, UserRepository {

	EntityManagerFactory emf;
	EntityManager em;
	boolean isProduction = false;

	public SqliteRepository() {
		this(false);
	}

	public SqliteRepository(boolean isProduction) {
		this.isProduction = isProduction;
		Map<String, String> properties = new HashMap<String, String>();
		if (isProduction) {
			properties.put("javax.persistence.jdbc.url", "jdbc:sqlite:lib/db/production.db");
		} else {
			properties.put("javax.persistence.jdbc.url", "jdbc:sqlite:lib/db/test.db");
		}

		emf = Persistence.createEntityManagerFactory("library", properties);
		em = emf.createEntityManager();
	}

	@Override
	public boolean contains(User user) {
			TypedQuery<User> mediaQuery = em
					.createQuery("SELECT u FROM User u WHERE u.cpr=:cpr", User.class)
					.setParameter("cpr", user.getCpr());
			List<User> users = mediaQuery.getResultList();
			return !users.isEmpty();
	}

	@Override
	public void addUser(User user) {
		withinTransaction(() -> em.persist(user));
	}

	@Override
	public Stream<User> getAllUsersStream() {
		return em.createQuery("SELECT u FROM User u", User.class).getResultStream();
	}

	@Override
	public void removeUser(User user) {
		withinTransaction(() -> 
			em.createQuery("DELETE FROM User u WHERE u.cpr=:cpr", User.class)
			  .setParameter("cpr", user.getCpr())
			  .executeUpdate()
		);
	}

	@Override
	public void updateUser(User user) {
		withinTransaction(() -> {
			em.merge(user);
		});
	}

	@Override
	public void addMedium(Medium medium) {
		withinTransaction(() -> em.persist(medium));
	}

	@Override
	public Stream<Medium> getAllMediaStream() {
		return em.createQuery("SELECT m FROM Medium m", Medium.class).getResultStream();
	}

	@Override
	public void updateMedium(Medium m) {
		em.merge(m);
	}

	private void withinTransaction(UnitFunction f) {
		em.getTransaction().begin();
		f.unitFunction();
		em.getTransaction().commit();
	}

	interface UnitFunction {
		public void unitFunction();
	}
	
	@Override
	public void clearMediumDatabase() {
		if (isProduction) {
			throw new Error("clearDatabase should not be called with a production database");
		}
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM User_Medium").executeUpdate();
		em.createNativeQuery("DELETE FROM Medium").executeUpdate();
		em.getTransaction().commit();
	}

	@Override
	public void clearUserDatabase() {
		if (isProduction) {
			throw new Error("clearDatabase should not be called with a production database");
		}
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM User_Medium").executeUpdate();
		em.createQuery("DELETE FROM User").executeUpdate();
		em.getTransaction().commit();
	}
}
