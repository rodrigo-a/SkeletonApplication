package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.User;

public class UserDao {

	private Session session;

	public UserDao(Session session) {
		this.session = session;
	}

	public void save(User user) {
		session.saveOrUpdate(user);
	}

	public List<User> findAll() {
		Query qry = session.getNamedQuery("User.findAll");
		return qry.list();
	}

	public void delete(User user) {
		session.delete(user);
	}

}
