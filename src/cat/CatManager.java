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

	/**
	 * Method responsible to initialize the CAT process.
	 * 
	 * @param user
	 *            the user logged.
	 * 
	 * @return the first question.
	 * @throws NotAuthenticatedException
	 */
	public QuestionModel start(UserModel user) throws NotAuthenticatedException {

		QuestionModel questionStarted = null;
		if (this.userExist(user)) {
			if (!haveEnoughInformation(user)) {
				user.setTheta(Constants.AVERAGE_THETA_LEVEL);
			}
			// else {
			// questionStarted = this.questionDAO.searchTheAverageQuestion();
			// }
			questionStarted = this.selectFittestItem();

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
	 * @throws NotAuthenticatedException
	 */
	public QuestionModel nextQuestion(UserModel user, QuestionModel question, boolean answer) throws NotAuthenticatedException {
		QuestionModel nextQuestion = null;

		if (this.userExist(user)) {
			markItemAsAnswered();
			if (!finalizationCriteria()) {
				this.updateProficiency(user, answer);
				if (isCorrect(answer)) {
					nextQuestion = this.questionDAO.searchNextMoreHard(question.getDifficulty());

				} else {
					nextQuestion = this.questionDAO.searchNextMoreEasy(question.getDifficulty());
				}

			} else {
				this.finalizeTest();
				this.showFeedback();
			}

		} else {
			throw new NotAuthenticatedException("USER NOT FOUND");
		}

		return nextQuestion;
	}

	private boolean userExist(UserModel user) {
		return Boolean.TRUE;
	}

	private void showFeedback() {
		// TODO Auto-generated method stub

	}

	private void finalizeTest() {
		// TODO Auto-generated method stub

	}

	private void updateProficiency(UserModel user, boolean answer) {
		// TODO Auto-generated method stub

	}

	private void markItemAsAnswered() {
		// TODO Auto-generated method stub

	}

	private boolean isCorrect(boolean answer) {
		return answer == Boolean.TRUE;
	}

	private boolean finalizationCriteria() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Select the most appropriate question based on user ability.
	 * 
	 * @return the most appropriate question
	 */
	private QuestionModel selectFittestItem() {
		return this.questionDAO.searchTheAverageQuestion();
	}

	private boolean haveEnoughInformation(UserModel user) {
		// TODO Auto-generated method stub
		return false;
	}

}
