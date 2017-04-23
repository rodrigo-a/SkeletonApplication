package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

public class Utils {

	public Utils() {
	}

	public static void ShowGrowl(FacesMessage msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, msg);
	}

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}

	public static void executeJS(String js) {
		RequestContext.getCurrentInstance().execute(js);
	}

}
