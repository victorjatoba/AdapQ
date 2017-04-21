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

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import util.MessageUtil;
import util.Util;
import data.OptionModel;
import data.QuestionModel;

@ManagedBean(name = "exerciseMB")
@SessionScoped
public class ExerciseMB {

	private List<QuestionModel> listQuestionModel;
	private OptionModel responseOptionModel;
	private QuestionModel responseQuestionModel;
	private String numberOfOption;

	public ExerciseMB() {
		this.clearFields();
	}

	private void clearFields() {
		this.responseQuestionModel = new QuestionModel();
		this.fillListQuestionFake();
	}

	private void fillListQuestionFake() {
		this.listQuestionModel = new ArrayList<QuestionModel>();

		for (int i = 0; i < 10; i++) {
			QuestionModel q = new QuestionModel();
			q.setDescription("Description from question" + " " + (i + 1));
			q.setId((long) i);

			List<OptionModel> listOptionModel = makeListOptionModelFakeToQuestion(q);

			q.setListOptionModel(listOptionModel);

			this.listQuestionModel.add(q);
		}
	}

	private List<OptionModel> makeListOptionModelFakeToQuestion(QuestionModel questionModel) {
		List<OptionModel> listOptionModel = new ArrayList<OptionModel>();

		for (int i = 0; i < 5; i++) {
			OptionModel op = new OptionModel();
			op.setId((long) i);
			op.setDescription("Alternative " + (i + 1));
			op.setLetterShow(Util.letterOption(i));
			op.setQuestionModel(questionModel);

			listOptionModel.add(op);
		}
		return listOptionModel;
	}

	public String respondQuestion() {

		boolean checked = false;

		for (OptionModel optionModel : this.responseQuestionModel.getListOptionModel()) {

			if (optionModel.isResponse()) {
				checked = true;
				if (optionModel.isFlagRight()) {
					MessageUtil.addInfoMessage("Parabéns! Você acertou!");
					// this.exerciseHistoricalModel.setHightAnswear(this.exerciseHistoricalModel.getHightAnswear() + 1);
				} else {
					MessageUtil.addErrorMessage("Resposta errada, tente novamente!");
					// this.exerciseHistoricalModel.setWrongAnswear(this.exerciseHistoricalModel.getWrongAnswear() + 1);
				}
			}
		}

		if (!checked) {
			MessageUtil.addErrorMessage("É necessário escolher uma opção.");
		} else {

			// if (!ValidationUtil.isNullOrEmpty(this.exerciseHistoricalModel.getId())) {
			// this.exerciseHistoricalDAO.update(this.exerciseHistoricalModel);
			// } else {
			// this.exerciseHistoricalDAO.insert(this.exerciseHistoricalModel);
			// }
		}

		return null;
	}

	public OptionModel getResponseOptionModel() {
		return responseOptionModel;
	}

	public void setResponseOptionModel(OptionModel responseOptionModel) {
		this.responseOptionModel = responseOptionModel;
	}

	public QuestionModel getResponseQuestionModel() {
		return responseQuestionModel;
	}

	public void setResponseQuestionModel(QuestionModel responseQuestionModel) {
		this.responseQuestionModel = responseQuestionModel;
	}

	public String getNumberOfOption() {
		return numberOfOption;
	}

	public void setNumberOfOption(String numberOfOption) {
		this.numberOfOption = numberOfOption;
	}

	public List<QuestionModel> getListQuestionModel() {
		return listQuestionModel;
	}

	public void setListQuestionModel(List<QuestionModel> listQuestionModel) {
		this.listQuestionModel = listQuestionModel;
	}

}
