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
import util.FacesUtil;
import util.MessageUtil;
import util.Util;
import util.ValidationUtil;
import dao.UserDAO;
import data.ProfileModel;
import data.UserModel;

@ManagedBean
@SessionScoped
public class UserLoginMB {

	private UserModel userModel;
	private UserModel userCadastre;
	private UserDAO userDAO;

	private String passwordConfirm;
	private boolean editUser;
	private boolean editImage;
	private boolean editPassword;
	private boolean passwordValid;

	public UserLoginMB() {
		this.clearFields();

		fillFakeLogin();
	}

	@PostConstruct
	public void init() {
		this.fillFakeLogin();
	}

	private void fillFakeLogin() {
		this.userModel.setEmail("intelectin@intelectin.com.br");
		this.userModel.setName("Victor");
		this.userModel.setLastName("Jatobá");
		this.userModel.setPassword("123");
		this.userModel.setId(1L);
		this.userModel.setProfileModel(new ProfileModel(Constants.PROFILE_STUDENT, "User"));
	}

	/**
	 * Method to clear fields when the class be instantiated.
	 * 
	 * @author anchieta
	 */
	private void clearFields() {
		this.userModel = new UserModel();
		this.userCadastre = new UserModel();
		this.userDAO = new UserDAO();

		this.passwordConfirm = "";
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

	/**
	 * Method to check if the password one is equals password confirm.
	 * 
	 * @return String
	 * @author anchieta
	 */
	public String checkPassword() {

		this.passwordValid = false;

		if (!ValidationUtil.isNullOrEmpty(this.userCadastre.getPassword()) && !ValidationUtil.isNullOrEmpty(this.passwordConfirm)) {

			if (this.userCadastre.getPassword().length() >= 6) {
				if (this.userCadastre.getPassword().equals(this.passwordConfirm)) {
					this.passwordValid = true;
				} else {
					MessageUtil.addErrorMessage("form:cadastrePassword_2", "As senhas não conferem.");
				}
			} else {
				MessageUtil.addErrorMessage("form:cadastrePassword_2", "A senha é muito curta!");
			}
		}

		return null;

	}

	public String showFieldsEditUser() {
		this.userCadastre = this.userModel;
		this.editUser = true;
		this.editImage = false;
		this.editPassword = false;
		return null;
	}

	public String closedFieldsEditUser() {
		this.userCadastre = new UserModel();
		this.editUser = false;
		this.editPassword = false;
		return null;
	}

	public String showFieldsEditPassword() {
		this.userCadastre = this.userModel;
		this.editUser = false;
		this.editImage = false;
		this.editPassword = true;
		return null;
	}

	public String closedFieldsEditPassword() {
		this.userCadastre = new UserModel();
		this.editImage = false;
		this.editUser = false;
		this.editPassword = false;
		return null;
	}

	public String showFieldsEditImageUser() {
		this.editImage = true;
		this.editUser = false;
		this.editPassword = false;
		return null;
	}

	public String closedFieldsEditImageUser() {
		this.editImage = false;
		this.editUser = false;
		this.editPassword = false;
		return null;
	}

	private boolean validateFields() {

		boolean valid = true;

		if (ValidationUtil.isNullOrEmpty(this.userCadastre.getName())) {
			MessageUtil.addErrorMessage("form:userCadastreName", "Um valor é necessário.");
			valid = false;
		} else {
			int index = this.userCadastre.getName().indexOf(" ");
			if (index != -1) {
				MessageUtil.addErrorMessage("form:userCadastreName", "Informe só o primeiro nome.");
				valid = false;
			}
		}

		if (ValidationUtil.isNullOrEmpty(this.userCadastre.getLastName())) {
			MessageUtil.addErrorMessage("form:userCadastreLastName", "Um valor é necessário.");
			valid = false;
		}

		if (ValidationUtil.isNullOrEmpty(this.userCadastre.getBirthDate())) {
			MessageUtil.addErrorMessage("form:userBirthDate", "Um valor é necessário.");
			valid = false;
		}

		if (ValidationUtil.isNullOrEmpty(this.userCadastre.getEmail())) {
			MessageUtil.addErrorMessage("form:userCadastreEmail", "Um valor é necessário.");
			valid = false;
		}

		if (!this.editUser) {

			// UserModel userTem = this.userDAO.findByEmail(this.userCadastre);
			UserModel userTem = this.userModel;

			if (!ValidationUtil.isNullOrEmpty(userTem)) {
				MessageUtil.addErrorMessage("form:userCadastreEmail", "O email já está cadastrado.");
				valid = false;
			}

			if (ValidationUtil.isNullOrEmpty(this.userCadastre.getPassword())) {
				MessageUtil.addErrorMessage("form:cadastrePassword_2", "Um valor é necessário.");
				valid = false;
			}

			if (ValidationUtil.isNullOrEmpty(this.passwordConfirm)) {
				MessageUtil.addErrorMessage("form:cadastrePassword_2", "Um valor é necessário.");
				valid = false;
			}

			this.checkPassword();

			if (!this.passwordValid) {
				valid = false;
			}
		}

		return valid;
	}

	private boolean validateFieldsChangePassword() {
		boolean valid = true;

		if (ValidationUtil.isNullOrEmpty(this.userCadastre.getOldPassword())) {
			MessageUtil.addErrorMessage("form:cadastreOldPassword", "Um valor é necessário");
			valid = false;
		}

		if (ValidationUtil.isNullOrEmpty(this.userCadastre.getPassword())) {
			MessageUtil.addErrorMessage("form:cadastrePassword_2", "Um valor é necessário.");
			valid = false;
		}

		if (ValidationUtil.isNullOrEmpty(this.passwordConfirm)) {
			MessageUtil.addErrorMessage("form:cadastrePassword_2", "Um valor é necessário.");
			valid = false;
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

	public UserModel getUserCadastre() {
		return userCadastre;
	}

	public void setUserCadastre(UserModel userCadastre) {
		this.userCadastre = userCadastre;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public boolean isPasswordValid() {
		return passwordValid;
	}

	public void setPasswordValid(boolean passwordValid) {
		this.passwordValid = passwordValid;
	}

	public boolean isEditUser() {
		return editUser;
	}

	public void setEditUser(boolean editUser) {
		this.editUser = editUser;
	}

	public boolean isEditImage() {
		return editImage;
	}

	public void setEditImage(boolean editImage) {
		this.editImage = editImage;
	}

	public boolean isEditPassword() {
		return editPassword;
	}

	public void setEditPassword(boolean editPassword) {
		this.editPassword = editPassword;
	}

}
