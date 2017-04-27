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
package dao;

import util.DaoFake;
import util.ValidationUtil;
import data.UserModel;

public class UserDAO {

	private String emailDefault = "intelectin@intelectin.com.br";

	public UserModel login(UserModel userModel) {

		if (ValidationUtil.isNullOrEmpty(userModel.getEmail()) || !userModel.getEmail().equals(emailDefault)
				|| ValidationUtil.isNullOrEmpty(userModel.getPassword()) || !userModel.getPassword().equals("123")) {
			userModel = null;
		} else {
			userModel = DaoFake.getUser();
		}

		return userModel;

	}
	// private UserModel fillFakeLogin(UserModel userModel) {
	// userModel.setName("Victor");
	// userModel.setLastName("Jatobá");
	// userModel.setId(1L);
	// userModel.setProfileModel(new ProfileModel(Constants.PROFILE_STUDENT, "User"));
	//
	// return userModel;
	// }
}
