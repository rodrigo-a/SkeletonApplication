package model;

import javax.persistence.*;

/**
 * The persistent class for the department database table.
 * 
 */
@Entity
@NamedQueries(value = { @NamedQuery(name = "Permission.findAll", query = "SELECT p FROM Permission p"),
		@NamedQuery(name = "Permission.findById", query = "SELECT p FROM Permission p WHERE p.id = :id"), })
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String description;

	private String name;

	@Override
	public boolean equals(Object object) {
		return (object instanceof Permission) && (id != null) ? id.equals(((Permission) object).id) : (object == this);
	}

	public int hashCode() {
		return id.hashCode();
	}

	public Permission() {
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

}