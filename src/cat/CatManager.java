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

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import util.Constants;
import util.DaoFake;
import util.ValidationUtil;

import com.itemanalysis.psychometrics.irt.estimation.IrtExaminee;
import com.itemanalysis.psychometrics.irt.model.ItemResponseModel;

import dao.QuestionDAO;
import dao.UserQuestionDAO;
import data.QuestionModel;
import data.UserModel;
import data.UserQuestionModel;

/**
 * 
 * @author victorjatoba
 *
 */
public class CatManager {

	QuestionDAO questionDAO;
	UserQuestionDAO userQuestionDAO;
	UserModel user;

	public CatManager(UserModel userModel) {
		super();
		this.user = userModel;
		this.clearFields();
	}

	private void clearFields() {
		questionDAO = new QuestionDAO();
		userQuestionDAO = new UserQuestionDAO(new ArrayList<UserQuestionModel>());
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
	public QuestionModel start() throws NotAuthenticatedException {
		QuestionModel questionStarted = null;

		// if (userExist(userModel)) {
		if (!haveEnoughInformation(this.user)) {
			this.user.getIrtExaminee().setStartValue(Constants.AVERAGE_THETA_LEVEL);
		}

		questionStarted = selectQuestionMostInformative(DaoFake.getIrms());

		// } else {
		// throw new NotAuthenticatedException("USER NOT FOUND");
		// }

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
	public QuestionModel nextQuestion(QuestionModel question, boolean answer) throws NotAuthenticatedException {
		QuestionModel nextQuestion = null;

		// if (this.userExist(this.userModel)) {
		this.markItemAsAnswered(question, answer);
		if (!finalizationCriteria()) {
			this.updateProficiency(answer);
			List<ItemResponseModel> questions = null;

			if (isCorrect(answer)) {
				// nextQuestion = this.questionDAO.searchNextMoreHard(question.getDifficulty());
				questions = this.questionDAO.searchQuestionsMoreHard(question.getDifficulty());

			} else {
				// nextQuestion = this.questionDAO.searchNextMoreEasy(question.getDifficulty());
				questions = this.questionDAO.searchQuestionsMoreEasy(question.getDifficulty());

			}

			if (questions.size() != 0) {
				nextQuestion = this.selectQuestionMostInformative(questions);
			}

		} else {
			this.finalizeTest();
			this.showFeedback();
		}

		// } else {
		// throw new NotAuthenticatedException("USER NOT FOUND");
		// }

		return nextQuestion;
	}

	private boolean userExist(UserModel userModel2) {
		return Boolean.TRUE;
	}

	private void showFeedback() {
		// TODO Auto-generated method stub

	}

	private void finalizeTest() {
		// TODO Auto-generated method stub

	}

	private void updateProficiency(boolean answer) {
		// TODO fill examinee with questions answered
		IrtExaminee examinee = this.user.getIrtExaminee();
		List<UserQuestionModel> userQuestionModels = this.userQuestionDAO.getUserQuestionModels();
		byte[] responses = new byte[userQuestionModels.size()];

		int i = 0;
		for (UserQuestionModel userQuestionModel : userQuestionModels) {
			responses[i++] = (userQuestionModel.getAnswer() == Boolean.TRUE) ? (byte) 1 : (byte) 0;
		}

		examinee.setResponseVector(responses);
		examinee.maximumLikelihoodEstimate(Constants.thetaMin, Constants.thetaMax);
	}

	private void markItemAsAnswered(QuestionModel question, boolean answer) {

		UserQuestionModel userQuestionModel = new UserQuestionModel(this.user, question, answer, new Time(0));
		this.userQuestionDAO.insert(userQuestionModel);
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
	 * @param irtExaminee
	 * 
	 * @return the most appropriate question
	 */
	private QuestionModel selectQuestionMostInformative(List<ItemResponseModel> irms) {
		QuestionModel questionModelMoreInformative = null;

		if (!ValidationUtil.isNullOrEmpty(irms)) {
			ItemResponseModel irmMostInformative = irms.get(0); // get first
			IrtExaminee irtExaminee = this.user.getIrtExaminee();
			double iifBigger = irmMostInformative.itemInformationAt(irtExaminee.getTheta());

			for (int i = 1; i < irms.size(); i++) {
				double iifLocal = irms.get(i).itemInformationAt(irtExaminee.getTheta());
				if (iifLocal > iifBigger) {
					iifBigger = iifLocal;
					irmMostInformative = irms.get(i);
				}
			}

			questionModelMoreInformative = this.questionDAO.findByDifficult(irmMostInformative.getDifficulty());
		}

		return questionModelMoreInformative;
	}

	private boolean haveEnoughInformation(UserModel userModel) {
		return userModel.getIrtExaminee().getTheta() != 0.0;
	}
	//
	// private void iif(UserModel user, QuestionModel question) {
	// double iif = P(user.getTheta(), question.getDifficulty()) * Q(user.getTheta(), question.getDifficulty());
	// }
	//
	// private double Q(double theta, double difficulty) {
	// return 1 - P(theta, difficulty);
	// }
	//
	// private double P(double theta, double difficulty) {
	// double P_i = (1 / (1 + Math.exp(L(theta, difficulty))));
	// return P_i;
	// }
	//
	// /**
	// * @param theta
	// * @param difficulty
	// * @return
	// */
	// private double L(double theta, double difficulty) {
	// return theta - difficulty;
	// }
}
