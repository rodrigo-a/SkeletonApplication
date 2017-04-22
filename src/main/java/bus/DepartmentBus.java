package bus;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import dao.DepartmentDao;
import model.Department;
import util.SessionFactoryMysql;

public class DepartmentBus {

	public DepartmentBus() {
	}

	public static void save(Department department) {
		Session sessao = SessionFactoryMysql.getSession();
		try {
			sessao.beginTransaction();
			new DepartmentDao(sessao).save(department);
			sessao.getTransaction().commit();
		} catch (Exception e) {
			sessao.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	public static List<Department> findAll() {
		List<Department> rvalue = null;
		Session sessao = SessionFactoryMysql.getSession();
		try {
			sessao.beginTransaction();
			rvalue = new DepartmentDao(sessao).findAll();
			sessao.getTransaction().commit();
		} catch (Exception e) {
			sessao.getTransaction().rollback();
			e.printStackTrace();
		}

		return rvalue;
	}

	public static Department loadById(Integer id) {
		Department rvalue = null;
		Session sessao = SessionFactoryMysql.getSession();
		try {
			sessao.beginTransaction();
			rvalue = new DepartmentDao(sessao).findById(id);
			sessao.getTransaction().commit();
		} catch (Exception e) {
			sessao.getTransaction().rollback();
			e.printStackTrace();
		}

		return rvalue;
	}

	public static void delete(Department department) {
		Session sessao = SessionFactoryMysql.getSession();
		try {
			sessao.beginTransaction();
			new DepartmentDao(sessao).delete(department);
			sessao.getTransaction().commit();
		}catch(ConstraintViolationException e){
			sessao.getTransaction().rollback();
			throw e;
		} catch (Exception e) {
			sessao.getTransaction().rollback();
			e.printStackTrace();
		}
	}
}
