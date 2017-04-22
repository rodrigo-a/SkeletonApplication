package bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.exception.ConstraintViolationException;

import bus.DepartmentBus;
import bus.PermissionBus;
import bus.UserBus;
import model.Department;
import model.Permission;
import model.User;
import util.Utils;

@ViewScoped
@ManagedBean
public class IndexBean {

	// Department
	private List<Department> departmentList;
	private Department currentDepartment;
	private boolean readOnlyDepartment;
	//
	// User
	private List<User> userList;
	private User currentUser;
	private Department userDepartment;
	private Integer userDepartmentId;
	private boolean readOnlyUser;
	private Permission userPermission;
	//
	// Permission
	private List<Permission> permissionList;
	private Permission currentPermission;
	private boolean readOnlyPermission;
	//

	public IndexBean() {
	}

	@PostConstruct
	public void init() {
		departmentList = DepartmentBus.findAll();
		userList = UserBus.findAll();
		permissionList = PermissionBus.findAll();
	}

	// Department
	public void saveDepartment() {
		try {
			boolean newDept = false;
			if (this.currentDepartment.getId() == null)
				newDept = true;
			DepartmentBus.save(currentDepartment);

			if (newDept) {
				if (this.departmentList == null)
					this.departmentList = new ArrayList<Department>();
				this.departmentList.add(this.currentDepartment);
			}

			Utils.ShowGrowl(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Department saved!"));
		} catch (Exception e) {
			Utils.ShowGrowl(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fail!", "Could not save department!"));
		}

	}

	public void showDepartmentDialog(Department dpt, boolean readOnly) {
		this.currentDepartment = dpt == null ? new Department() : dpt;
		this.readOnlyDepartment = readOnly;
		Utils.executeJS("PF('departmentDialog').show();");
	}

	public void deleteDepartment(Department department) {
		try {
			DepartmentBus.delete(department);
			this.departmentList.remove(department);
			Utils.ShowGrowl(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Department deleted!"));
		} catch (ConstraintViolationException e) {
			Utils.ShowGrowl(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fail!",
					"Could not delete department! Department " + department.getName() + " is associated with a User."));
		} catch (Exception e) {
			Utils.ShowGrowl(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fail!", "Could not delete department!"));
		}
	}

	//
	// User
	public void saveUser() {
		try {
			boolean newUser = false;
			if (this.currentUser.getId() == null)
				newUser = true;
			currentUser.setDepartment(userDepartment);
			UserBus.save(currentUser);

			if (newUser) {
				if (this.userList == null)
					this.userList = new ArrayList<User>();
				this.userList.add(this.currentUser);
			}

			Utils.ShowGrowl(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "User saved!"));
		} catch (Exception e) {
			Utils.ShowGrowl(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fail!", "Could not save user!"));
		}

	}

	public void showUserDialog(User user, boolean readOnly) {
		userPermission = null;
		currentUser = user == null ? new User() : user;
		userDepartment = currentUser.getDepartment();

		this.readOnlyUser = readOnly;
		Utils.executeJS("PF('userDialog').show();");
	}

	public void deleteUser(User user) {
		try {
			UserBus.delete(user);
			this.userList.remove(user);
			Utils.ShowGrowl(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "User deleted!"));
		} catch (Exception e) {
			Utils.ShowGrowl(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fail!", "Could not delete user!"));
		}
	}

	public void assignPermission(Permission perm) {
		try {
			if (perm == null)
				return;
			if (currentUser == null) {
				Utils.ShowGrowl(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fail!",
						"It is a new user! Save it before assigning permissions!"));
				return;
			}
			UserBus.assignPermission(currentUser, perm);
			Utils.ShowGrowl(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Permission assigned!"));
		} catch (Exception e) {
			Utils.ShowGrowl(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fail!", "Could not assign permission!"));
		}
	}

	public void removeUserPermission(Permission perm) {
		try {
			UserBus.removePermission(currentUser, perm);
			Utils.ShowGrowl(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Permission removed!"));
		} catch (Exception e) {
			Utils.ShowGrowl(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fail!", "Could not remove permission!"));
		}
	}

	//
	// Permission
	public void savePermission() {
		try {
			boolean newPermission = false;
			if (this.currentPermission.getId() == null)
				newPermission = true;
			PermissionBus.save(currentPermission);

			if (newPermission) {
				if (this.permissionList == null)
					this.permissionList = new ArrayList<Permission>();
				this.permissionList.add(this.currentPermission);
			}

			Utils.ShowGrowl(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Permission saved!"));
		} catch (Exception e) {
			Utils.ShowGrowl(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fail!", "Could not save permission!"));
		}

	}

	public void showPermissionDialog(Permission permission, boolean readOnly) {
		this.currentPermission = permission == null ? new Permission() : permission;
		this.readOnlyPermission = readOnly;
		Utils.executeJS("PF('permissionDialog').show();");
	}

	public void deletePermission(Permission permission) {
		try {
			PermissionBus.delete(permission);
			this.permissionList.remove(permission);
			this.userList = UserBus.findAll();
			Utils.ShowGrowl(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Permission deleted!"));
		} catch (ConstraintViolationException e) {
			Utils.ShowGrowl(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fail!",
					"Could not delete permission! Permission " + permission.getName() + " is associated with a User."));
		} catch (Exception e) {
			Utils.ShowGrowl(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fail!", "Could not delete permission!"));
		}
	}

	//

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public Department getCurrentDepartment() {
		return currentDepartment;
	}

	public void setCurrentDepartment(Department currentDepartment) {
		this.currentDepartment = currentDepartment;
	}

	public boolean isReadOnlyDepartment() {
		return readOnlyDepartment;
	}

	public void setReadOnlyDepartment(boolean readOnlyDepartment) {
		this.readOnlyDepartment = readOnlyDepartment;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public boolean isReadOnlyUser() {
		return readOnlyUser;
	}

	public void setReadOnlyUser(boolean readOnlyUser) {
		this.readOnlyUser = readOnlyUser;
	}

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	public Permission getCurrentPermission() {
		return currentPermission;
	}

	public void setCurrentPermission(Permission currentPermission) {
		this.currentPermission = currentPermission;
	}

	public boolean isReadOnlyPermission() {
		return readOnlyPermission;
	}

	public void setReadOnlyPermission(boolean readOnlyPermission) {
		this.readOnlyPermission = readOnlyPermission;
	}

	public Department getUserDepartment() {
		return userDepartment;
	}

	public void setUserDepartment(Department userDepartment) {
		this.userDepartment = userDepartment;
	}

	public Integer getUserDepartmentId() {
		return userDepartmentId;
	}

	public void setUserDepartmentId(Integer userDepartmentId) {
		this.userDepartmentId = userDepartmentId;
	}

	public Permission getUserPermission() {
		return userPermission;
	}

	public void setUserPermission(Permission userPermission) {
		this.userPermission = userPermission;
	}

}
