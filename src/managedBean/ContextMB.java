/*
 * Copyright 2017 V. M. G. Jatobá
 *
 * Licensed under the MIT;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://opensource.org/licenses/MIT
 *
 * The software is provided "AS IS", without warranty of any kind, 
 * express or implied, including but not limited to the warranties
 * of merchantability, fitness for a particular purpose and
 * noninfringement. In no event shall the authors or copyright
 * holders be liable for any claim, damages or other liability,
 * whether in an action of contract, tort or otherwise, arising
 * from, out of or in connection with the software or the use
 * or other dealings in the software.
 */
package managedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import util.Constants;
import util.DaoFake;
import util.FacesUtil;
import util.MessageUtil;
import util.ValidationUtil;
import cat.CatManager;

import com.itemanalysis.psychometrics.irt.estimation.IrtExaminee;
import com.itemanalysis.psychometrics.irt.model.ItemResponseModel;

import data.ExamineeModel;
import data.QuestionModel;
import enums.Page;

@ManagedBean(name = "contextMB")
@SessionScoped
public class ContextMB {

	private String actualPage = Page.HOME.getName();
	private List<IrtExaminee> iVecs;
	private List<ExamineeModel> iVecsModel;
	private List<ItemResponseModel> irms;
	private int pageChose;

	@ManagedProperty(value = "#{userLoginMB}")
	private UserLoginMB userLoginMB;

	public ContextMB() {
		DaoFake.init();
		this.clearFields();
	}

	@PostConstruct
	public void init() {
		this.actualPage = Page.HOME.getName();
	}

	/**
	 * Method to clear fields when the class be instantiated.
	 * 
	 * @author anchieta
	 */
	private void clearFields() {

		// Set the managed bean in session to get anywhere else.
		FacesUtil.setManagedBeanInSession(Constants.CONTEXT_MB, this);

		this.pageChose = Page.UNKNOWN.getValue();
	}

	public String startTest() {

		FacesUtil.removeManagedBeanInSession(Constants.EXERCISE_MB);
		String page = Constants.PAGE_EXERCISE;

		CatManager catManager = new CatManager();
		QuestionModel startedQuestion = catManager.start(this.userLoginMB.getUserModel());

		if (!ValidationUtil.isNullOrEmpty(startedQuestion)) {
			ExerciseMB exerciseMB = new ExerciseMB();

			exerciseMB.setActualQuestion(startedQuestion);

			FacesUtil.setManagedBeanInSession(Constants.EXERCISE_MB, exerciseMB);
		} else {
			MessageUtil.addErrorMessage("You have no questions for today.");
			page = null;
		}

		return page;
	}

	/**
	 * Log out. Remove the object UserModel of session
	 * 
	 * @return go to home page.
	 * @author anchieta
	 */
	public String logOutgoToLoginPage() {

		FacesUtil.removeObjectInSession(Constants.USER_LOGGED);

		// this.userLoginMB.setUserModel(new UserModel()); //TODO descomentar depois

		this.actualPage = Page.LOGIN.getName();

		return goToHomePage();
	}

	public String goToDashboardPage() {
		this.actualPage = Page.LOGIN.getName();
		return "sucess";
	}

	public void reset() {
		this.clearFields();
	}

	public String goToHomePage() {
		return "home";
	}

	public String goToContactPage() {
		return "contact";
	}

	public String getActualPage() {
		return actualPage;
	}

	public void setActualPage(String actualPage) {
		this.actualPage = actualPage;
	}

	public List<IrtExaminee> getiVecs() {
		return iVecs;
	}

	public void setiVecs(List<IrtExaminee> iVecs) {
		this.iVecs = iVecs;
	}

	public List<ItemResponseModel> getIrms() {
		return irms;
	}

	public void setIrms(List<ItemResponseModel> irms) {
		this.irms = irms;
	}

	public int getPageChose() {
		return pageChose;
	}

	public void setPageChose(int pageChose) {
		this.pageChose = pageChose;
	}

	public List<ExamineeModel> getiVecsModel() {
		return iVecsModel;
	}

	public void setiVecsModel(List<ExamineeModel> iVecsModel) {
		this.iVecsModel = iVecsModel;
	}

	public UserLoginMB getUserLoginMB() {
		return userLoginMB;
	}

	public void setUserLoginMB(UserLoginMB userLoginMB) {
		this.userLoginMB = userLoginMB;
	}

}