package bus;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import dao.DepartmentDao;
import dao.UserDao;
import model.Permission;
import model.User;
import util.SessionFactoryMysql;

public class UserBus {

	public UserBus() {
		// TODO Auto-generated constructor stub
	}

	public static void save(User user) {
		Session sessao = SessionFactoryMysql.getSession();
		try {
			sessao.beginTransaction();
			new UserDao(sessao).save(user);
			sessao.getTransaction().commit();
		} catch (Exception e) {
			sessao.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	public static List<User> findAll() {
		List<User> rvalue = null;
		Session sessao = SessionFactoryMysql.getSession();
		try {
			sessao.beginTransaction();
			rvalue = new UserDao(sessao).findAll();
			sessao.getTransaction().commit();
		} catch (Exception e) {
			sessao.getTransaction().rollback();
			e.printStackTrace();
		}

		return rvalue;
	}

	public static void delete(User user) {
		Session sessao = SessionFactoryMysql.getSession();
		try {
			sessao.beginTransaction();
			new UserDao(sessao).delete(user);
			sessao.getTransaction().commit();
		}catch(ConstraintViolationException e){
			sessao.getTransaction().rollback();
			throw e;
		} catch (Exception e) {
			sessao.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	public static void assignPermission(User user, Permission perm) {
		user.setPermissions(user.getPermissions() == null ? new ArrayList<Permission>() : user.getPermissions());
		user.getPermissions().add(perm);
		save(user);
	}

	public static void removePermission(User user, Permission perm) {
		user.getPermissions().remove(perm);
		save(user);
	}

	public static User loadById(Integer id) {
		User rvalue = null;
		Session sessao = SessionFactoryMysql.getSession();
		try {
			sessao.beginTransaction();
			rvalue = new UserDao(sessao).loadById(id);
			sessao.getTransaction().commit();
		} catch (Exception e) {
			sessao.getTransaction().rollback();
			e.printStackTrace();
		}

		return rvalue;
	}
}
