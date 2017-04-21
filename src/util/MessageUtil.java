package util;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public final class MessageUtil {

	private static ResourceBundle bundle = null;

	public static void addErrorMessageKey(String key) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, getText(key)));
	}

	public static void addErrorMessage(String msg) {
		addErrorMessage(null, msg);
	}

	public static void addInfoMessageKey(String key) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, getText(key)));
	}

	public static void addInfoMessage(String clientId, String msg) {
		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, null, msg));
	}

	public static void addInfoMessage(String msg) {
		addInfoMessage(null, msg);
	}

	/**
	 * Add error message to a sepcific client.
	 * 
	 * @param clientId
	 *            the client id
	 * @param msg
	 *            the error message
	 */
	public static void addErrorMessage(String clientId, String msg) {
		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, msg));
	}

	private static String getText(String key) {
		String text = null;

		try {
			text = bundle.getString(key);
		} catch (Exception e) {
			text = "???" + key + "???";
		}
		return text;

	}
}
