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

import java.util.List;

import data.QuestionModel;
import data.UserQuestionModel;

public class UserQuestionDAO {

	private List<UserQuestionModel> userQuestionModels;

	public UserQuestionDAO(List<UserQuestionModel> userQuestionModels) {
		super();
		this.userQuestionModels = userQuestionModels;
	}

	public void insert(UserQuestionModel userQuestionModel) {
		this.userQuestionModels.add(userQuestionModel);
	}

	public void update(UserQuestionModel userQuestionModel) {
	}

	public void delete(UserQuestionModel userQuestionModel) {
	}

	public QuestionModel find(UserQuestionModel userQuestionModel) {
		return null;
	}

	public List<UserQuestionModel> getUserQuestionModels() {
		return userQuestionModels;
	}

	public void setUserQuestionModels(List<UserQuestionModel> userQuestionModels) {
		this.userQuestionModels = userQuestionModels;
	}

}
