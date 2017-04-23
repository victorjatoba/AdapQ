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

import java.security.NoSuchAlgorithmException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import util.Constants;
import util.DaoFake;
import util.FacesUtil;
import util.MessageUtil;
import util.Util;
import util.ValidationUtil;
import dao.UserDAO;
import data.UserModel;

@ManagedBean
@SessionScoped
public class UserLoginMB {

	private UserModel userModel;
	private UserDAO userDAO;

	private boolean passwordValid;

	public UserLoginMB() {
		this.clearFields();
	}

	@PostConstruct
	public void init() {
	}

	/**
	 * Method to clear fields when the class be instantiated.
	 * 
	 * @author anchieta
	 */
	private void clearFields() {
		this.userDAO = new UserDAO();
		this.userModel = DaoFake.fillFakeLogin(); // TODO

	}

	/**
	 * Method to login of the users
	 * 
	 * @return page dashboad
	 * @throws NoSuchAlgorithmException
	 * @author anchieta
	 */

	public String login() throws NoSuchAlgorithmException {

		if (ValidationUtil.isNullOrEmpty(this.userModel.getEmail())) {
			MessageUtil.addErrorMessage("form:username", "Email deve ser preenchido.");
			return null;
		} else if (ValidationUtil.isNullOrEmpty(this.userModel.getPassword())) {
			MessageUtil.addErrorMessage("form:password", "Senha deve ser preenchido.");
			return null;
		}

		// this.userModel.setPassword(Encrypt.encript(this.userModel.getPassword()));

		UserModel userModelTemp = this.userDAO.login(this.userModel);

		if (Util.isEmpty(userModelTemp)) {
			MessageUtil.addErrorMessage("form:username", "Email e/ou senha inválido.");
			return null;
		}

		this.userModel = userModelTemp;

		FacesUtil.addObjectInSession(Constants.USER_LOGGED, this.userModel);

		FacesUtil.setManagedBeanInSession(Constants.USER_LOGIN_MB, this);

		String page = null;
		if (this.userModel.getProfileModel().getId().equals(Constants.PROFILE_ADMINISTRATION)) {
			page = Constants.PAGE_ADMINISTRATION;
		} else {
			page = Constants.PAGE_SUCESS;
		}

		return page;

	}

	/**
	 * Method to get the user that is logged
	 * 
	 * @return boolean
	 * @author anchieta
	 */
	public boolean isUserLogged() {

		boolean logad = true;

		UserModel user = Util.getUserLogged();

		if (ValidationUtil.isNullOrEmpty(user)) {
			logad = false;
		}

		return logad;
	}

	/**
	 * Method to get the user that is logged
	 * 
	 * @return boolean
	 * @author anchieta
	 */
	public boolean isUserProfileAdministration() {

		boolean valid = true;

		UserModel user = Util.getUserLogged();

		if (ValidationUtil.isNullOrEmpty(user)) {
			valid = false;
		} else {
			if (user.getProfileModel().getId().equals(Constants.PROFILE_ADMINISTRATION)) {
				valid = true;
			} else {
				valid = false;
			}
		}

		return valid;
	}

	public String goForgotPassword() {
		// ForgotPasswordMB forgotPasswordMB = new ForgotPasswordMB();
		// ResetPasswordModel resetPasswordModel = new ResetPasswordModel();
		// resetPasswordModel.setUserModel(this.userModel);
		// forgotPasswordMB.setResetPasswordModel(resetPasswordModel);
		//
		// FacesUtil.setManagedBeanInSession(Constants.FORGOT_PASSWORD_MB, forgotPasswordMB);

		return "forgotPassword";
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public boolean isPasswordValid() {
		return passwordValid;
	}

	public void setPasswordValid(boolean passwordValid) {
		this.passwordValid = passwordValid;
	}

}
