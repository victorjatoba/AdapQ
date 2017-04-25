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
package cat;

import util.Constants;
import dao.QuestionDAO;
import data.QuestionModel;
import data.UserModel;

/**
 * 
 * @author victorjatoba
 *
 */
public class CatManager {

	QuestionDAO questionDAO;

	public CatManager() {
		super();
		this.clearFields();
	}

	private void clearFields() {
		questionDAO = new QuestionDAO();
	}

	public QuestionModel start(UserModel user) {

		QuestionModel questionStarted = null;
		if (this.userExist(user)) {
			if (haveEnoughInformation(user)) {
				questionStarted = this.selectItemByFit();

			} else {
				questionStarted = this.questionDAO.searchTheAverageQuestion();

			}

		} else {
			throw new NotAuthenticatedException("USER NOT FOUND");
		}

		return questionStarted;
	}

	/***
	 * Method responsible to select the next question based on previously student answer
	 * 
	 * @param user
	 *            the student
	 * @param question
	 *            the previously question
	 * @param answer
	 *            the student response/answer <code>true<code> if answer correctly
	 *            <code>false<code> Otherwise
	 * @return
	 */
	public QuestionModel nextQuestion(UserModel user, QuestionModel question, boolean answer) {
		markItemAsAnswered();
		updateProficiency();
		QuestionModel nextQuestion = null;

		if (!finalizationCriteria()) {
			if (answer == Constants.CORRECT_ANSWER) {
				nextQuestion = this.questionDAO.searchNextMoreHard(question.getDifficulty());
			} else {
				nextQuestion = this.questionDAO.searchNextMoreEasy(question.getDifficulty());
			}

		} else {
			this.finalizeTest();
			this.showFeedback();
		}

		return nextQuestion;
	}

	private boolean userExist(UserModel user) {
		// TODO Auto-generated method stub
		return Boolean.TRUE;
	}

	private void showFeedback() {
		// TODO Auto-generated method stub

	}

	private void finalizeTest() {
		// TODO Auto-generated method stub

	}

	private void updateProficiency() {
		// TODO Auto-generated method stub

	}

	private void markItemAsAnswered() {
		// TODO Auto-generated method stub

	}

	private boolean checkAnswer() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean finalizationCriteria() {
		// TODO Auto-generated method stub
		return false;
	}

	private void showItem() {
		// TODO Auto-generated method stub

	}

	private QuestionModel selectItemByFit() {
		// TODO Auto-generated method stub

		return null;
	}

	private boolean haveEnoughInformation(UserModel user) {
		// TODO Auto-generated method stub
		return false;
	}

}
