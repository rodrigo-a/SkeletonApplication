package util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import bus.DepartmentBus;
import model.Department;

@FacesConverter(forClass = Department.class, value = "departmentConverter")
public class DepartmentConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				return DepartmentBus.loadById(Integer.parseInt(value));
			} catch (NumberFormatException e) {
			}
		}
		return null;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			String str = Integer.toString(((Department) object).getId());
			return str;
		} else {
			return null;
		}
	}
}
