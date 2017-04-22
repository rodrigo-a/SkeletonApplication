package bus;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import dao.PermissionDao;
import model.Permission;
import util.SessionFactoryMysql;

public class PermissionBus {

	public PermissionBus() {
		// TODO Auto-generated constructor stub
	}

	public static void save(Permission department) {
		Session sessao = SessionFactoryMysql.getSession();
		try {
			sessao.beginTransaction();
			new PermissionDao(sessao).save(department);
			sessao.getTransaction().commit();
		} catch (Exception e) {
			sessao.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	public static List<Permission> findAll() {
		List<Permission> rvalue = null;
		Session sessao = SessionFactoryMysql.getSession();
		try {
			sessao.beginTransaction();
			rvalue = new PermissionDao(sessao).findAll();
			sessao.getTransaction().commit();
		} catch (Exception e) {
			sessao.getTransaction().rollback();
			e.printStackTrace();
		}

		return rvalue;
	}

	public static void delete(Permission permission) {
		Session sessao = SessionFactoryMysql.getSession();
		try {
			sessao.beginTransaction();
			new PermissionDao(sessao).delete(permission);
			sessao.getTransaction().commit();
		}catch(ConstraintViolationException e){
			sessao.getTransaction().rollback();
			throw e;
		} catch (Exception e) {
			sessao.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	public static Permission loadById(Integer id) {
		Permission rvalue = null;
		Session sessao = SessionFactoryMysql.getSession();
		try {
			sessao.beginTransaction();
			rvalue = new PermissionDao(sessao).findById(id);
			sessao.getTransaction().commit();
		} catch (Exception e) {
			sessao.getTransaction().rollback();
			e.printStackTrace();
		}

		return rvalue;
	}
}
