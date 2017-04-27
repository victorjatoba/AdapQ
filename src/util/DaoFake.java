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
	private static List<ItemResponseModel> irms;
	private static UserModel user;

	public static void init() {
		questions = getEnem2012Questions();
		user = fillFakeLogin();
	}

	public static List<QuestionModel> getEnem2012Questions() {
		DaoFake.irms = jointEstimation();
		List<QuestionModel> questionsFormated = new ArrayList<QuestionModel>();

		int i = 0;
		for (ItemResponseModel itemResponseModel : irms) {
			QuestionModel q = new QuestionModel();
			q.setId((long) i);
			q.setDescription("Description from question" + " " + (i++));
			q.setDifficulty(itemResponseModel.getDifficulty());

			List<OptionModel> listOptionModel = makeListOptionModelFakeToQuestion(q);

			q.setListOptionModel(listOptionModel);

			questionsFormated.add(q);
		}

		questionsFormated.add(initFakeQuestion());

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
		ArrayList<ExamineeModel> examineeModels = irtCalculator.getiVecsModel();
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

	private static QuestionModel initFakeQuestion() {
		QuestionModel actualQuestion = new QuestionModel();
		actualQuestion.setId(1L);
		actualQuestion.setDifficulty(Constants.AVERAGE_DIFFICULTY_LEVEL_ENEM_2012);
		actualQuestion
				.setDescription("Jogar baralho é uma atividade que estimula o raciocínio. Um jogo tradicional é a Paciência, que utiliza 52 cartas. Inicialmente são formadas sete colunas com as cartas. A primeira coluna tem uma carta, a segunda tem duas cartas, a terceira tem três cartas, a quarta tem quatro cartas, e assim sucessivamente até a sétima coluna, a qual tem sete cartas, e o que sobra forma o monte, que são as cartas não utilizadas nas colunas. \nA quantidade de cartas que forma o monte é");

		List<OptionModel> listOptionModel = new ArrayList<OptionModel>();

		OptionModel op = new OptionModel();
		op.setId(0L);
		op.setDescription("21");
		op.setLetterShow(Util.letterOption(0));
		op.setQuestionModel(actualQuestion);
		listOptionModel.add(op);

		op = new OptionModel();
		op.setId(1L);
		op.setDescription("24");
		op.setLetterShow(Util.letterOption(1));
		op.setQuestionModel(actualQuestion);
		op.setFlagRight(Boolean.TRUE);
		listOptionModel.add(op);

		op = new OptionModel();
		op.setId(2L);
		op.setDescription("26");
		op.setLetterShow(Util.letterOption(2));
		op.setQuestionModel(actualQuestion);
		listOptionModel.add(op);

		op = new OptionModel();
		op.setId(3L);
		op.setDescription("28");
		op.setLetterShow(Util.letterOption(3));
		op.setQuestionModel(actualQuestion);
		listOptionModel.add(op);

		op = new OptionModel();
		op.setId(4L);
		op.setDescription("31");
		op.setLetterShow(Util.letterOption(4));
		op.setQuestionModel(actualQuestion);
		listOptionModel.add(op);

		actualQuestion.setListOptionModel(listOptionModel);

		return actualQuestion;
	}

	public static List<ItemResponseModel> getIrms() {
		return irms;
	}

	public static void setIrms(List<ItemResponseModel> irms) {
		DaoFake.irms = irms;
	}

}
