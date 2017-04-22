package model;

import java.util.List;

import javax.persistence.*;

/**
 * The persistent class for the department database table.
 * 
 */
@Entity
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String description;

	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "iddepartment")
	private Department department;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "rel_userpermission", joinColumns = @JoinColumn(name = "iduser"), inverseJoinColumns = {
			@JoinColumn(name = "idpermission") })
	private List<Permission> permissions;

	@Override
	public boolean equals(Object object) {
		return (object instanceof User) && (id != null) ? id.equals(((User) object).id) : (object == this);
	}

	public int hashCode() {
		return id.hashCode();
	}

	public User() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}