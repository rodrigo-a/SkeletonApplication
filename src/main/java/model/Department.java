package model;

import javax.persistence.*;

/**
 * The persistent class for the department database table.
 * 
 */
@Entity
@NamedQueries(value = { @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
		@NamedQuery(name = "Department.findById", query = "SELECT d FROM Department d WHERE d.id = :id"), })
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String description;

	private String name;

	@Override
	public boolean equals(Object object) {
		return (object instanceof Department) && (id != null) ? id.equals(((Department) object).id) : (object == this);
	}

	public int hashCode() {
		return id.hashCode();
	}

	public Department() {
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