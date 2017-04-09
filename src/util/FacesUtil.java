package util;

import javax.faces.context.FacesContext;

public class FacesUtil {

	/**
	 * Pegar da sessão o objeto passado como parametro.
	 * 
	 * @param beanName
	 * @param managedBean
	 */
	public static Object getObjectInSession(String beanName) {
		return getManagedBeanInSession(beanName);
	}

	/**
	 * Retrieve the managed bean inside the session scope.
	 * 
	 * @param beanName
	 *            the name of the managed bean to be stored
	 * @param managedBean
	 *            the managed bean to be stored
	 */
	public static Object getManagedBeanInSession(String beanName) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(beanName);
	}

	/**
	 * Adiciona o managed bean na session scope.
	 * 
	 * @param beanName
	 *            the name of the managed bean to be stored
	 * @param managedBean
	 *            the managed bean to be stored
	 */
	public static void addObjectInSession(String beanName, Object managedBean) {
		setManagedBeanInSession(beanName, managedBean);
	}

	public static void removeObjectInSession(String beanName) {
		removeManagedBeanInSession(beanName);
	}

	/**
	 * Remove the managed bean inside the session scope.
	 * 
	 * @param beanName
	 *            the name of the managed bean to be stored
	 * @param managedBean
	 *            the managed bean to be stored
	 */
	public static Object removeManagedBeanInSession(String beanName) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(beanName);
	}

	public static void setManagedBeanInSession(String beanName, Object managedBean) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(beanName, managedBean);
	}

}
