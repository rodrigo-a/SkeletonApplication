package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.Department;

public class DepartmentDao {

	private Session session;

	public DepartmentDao(Session session) {
		this.session = session;
	}

	public void save(Department department) {
		session.saveOrUpdate(department);
	}

	public List<Department> findAll() {
		Query qry = session.getNamedQuery("Department.findAll");
		return qry.list();
	}

	public Department findById(Integer id) {
		Query qry = session.getNamedQuery("Department.findById");
		qry.setParameter("id", id);
		return (Department) qry.uniqueResult();
	}
	
	public void delete(Department department)
	{
		session.delete(department);
	}
}
