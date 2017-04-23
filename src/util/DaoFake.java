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
package util;

import java.util.ArrayList;
import java.util.List;

import analysis.IrtCalculator;

import com.itemanalysis.psychometrics.irt.estimation.IrtExaminee;
import com.itemanalysis.psychometrics.irt.model.ItemResponseModel;

import data.ExamineeModel;
import data.OptionModel;
import data.ProfileModel;
import data.QuestionModel;
import data.UserModel;

/**
 * Class to be used while the database access isn't configured.
 * 
 * @author victorjatoba
 * 
 */
public class DaoFake {

	private static List<QuestionModel> questions;
	private static UserModel user;

	public static void init() {
		questions = getEnem2012Questions();
		user = fillFakeLogin();
	}

	public static List<QuestionModel> getEnem2012Questions() {
		List<ItemResponseModel> questions = jointEstimation();
		List<QuestionModel> questionsFormated = new ArrayList<QuestionModel>();

		int i = 0;
		for (ItemResponseModel itemResponseModel : questions) {
			QuestionModel q = new QuestionModel();
			q.setId((long) i);
			q.setDescription("Description from question" + " " + (i++));
			q.setDifficulty(itemResponseModel.getDifficulty());

			List<OptionModel> listOptionModel = makeListOptionModelFakeToQuestion(q);

			q.setListOptionModel(listOptionModel);

			questionsFormated.add(q);
		}

		return questionsFormated;
	}

	public static List<QuestionModel> getUnknownListQuestion() {
		List<QuestionModel> listQuestionModel = new ArrayList<QuestionModel>();

		for (int i = 0; i < 10; i++) {
			QuestionModel q = new QuestionModel();
			q.setDescription("Description from question" + " " + (i + 1));
			q.setId((long) i);

			List<OptionModel> listOptionModel = makeListOptionModelFakeToQuestion(q);

			q.setListOptionModel(listOptionModel);

			listQuestionModel.add(q);
		}

		return listQuestionModel;
	}

	private static List<OptionModel> makeListOptionModelFakeToQuestion(QuestionModel questionModel) {
		List<OptionModel> listOptionModel = new ArrayList<OptionModel>();

		for (int i = 0; i < 5; i++) {
			OptionModel op = new OptionModel();
			op.setId((long) i);
			op.setDescription("Alternative " + (i + 1));
			op.setLetterShow(Util.letterOption(i));
			op.setQuestionModel(questionModel);

			if (i == 0) {
				op.setFlagRight(Boolean.TRUE);
			}

			listOptionModel.add(op);
		}
		return listOptionModel;
	}

	public static List<ItemResponseModel> jointEstimation() {
		IrtCalculator irtCalculator = new IrtCalculator();

		irtCalculator.runJointMleRasch("enem2012.txt");
		ArrayList<ExamineeModel> iVecsModel = irtCalculator.getiVecsModel();
		List<ItemResponseModel> irms = irtCalculator.getIrms();

		return irms;
	}

	public static UserModel fillFakeLogin() {
		user = new UserModel();

		user.setEmail("intelectin@intelectin.com.br");
		user.setName("Victor");
		user.setLastName("Jatobá");
		user.setPassword("123");
		user.setId(1L);
		user.setProfileModel(new ProfileModel(Constants.PROFILE_STUDENT, "User"));

		return user;
	}

	/**
	 * 
	 * @return
	 */
	private String thetaEstimationKnownItem() {
		IrtCalculator irtCalculator = new IrtCalculator();

		// Init dataset
		byte[][] lsat7 = FileUploadUtil.readTestData("lsat7.txt", 32, 5);
		irtCalculator.mountExamineeList(lsat7);
		List<ItemResponseModel> irms = irtCalculator.getIrms();

		irtCalculator.runMaximumLikelihood2PL();
		List<IrtExaminee> iVecs = irtCalculator.getiVecs();

		return "";
	}

	public static List<QuestionModel> getQuestions() {
		return questions;
	}

	public static UserModel getUser() {
		return user;
	}

}
