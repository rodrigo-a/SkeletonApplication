package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.Permission;

public class PermissionDao {

	private Session session;

	public PermissionDao(Session session) {
		this.session = session;
	}

	public void save(Permission permission) {
		session.saveOrUpdate(permission);
	}

	public List<Permission> findAll() {
		Query qry = session.getNamedQuery("Permission.findAll");
		return qry.list();
	}

	public void delete(Permission permission) {
		session.delete(permission);		
	}

	public Permission findById(Integer id) {
		Query qry = session.getNamedQuery("Permission.findById");
		qry.setParameter("id", id);
		return (Permission) qry.uniqueResult();
	}

}
