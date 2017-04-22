package util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import bus.PermissionBus;
import model.Permission;

@FacesConverter(forClass = Permission.class, value = "permissionConverter")
public class PermissionConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				return PermissionBus.loadById(Integer.parseInt(value));
			} catch (NumberFormatException e) {
			}
		}
		return null;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			String str = Integer.toString(((Permission) object).getId());
			return str;
		} else {
			return null;
		}
	}
}
