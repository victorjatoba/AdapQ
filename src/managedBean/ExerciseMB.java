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
	private QuestionModel actualQuestion;
	private OptionModel responseOptionModel;
	private String numberOfOption;

	public ExerciseMB() {
		this.clearFields();
	}

	private void clearFields() {
		// this.fillListQuestionFake();
		this.initFakeQuestion();
	}

	private void initFakeQuestion() {
		this.actualQuestion = new QuestionModel();
		this.actualQuestion.setId(1L);
		this.actualQuestion
				.setDescription("Jogar baralho é uma atividade que estimula o raciocínio. Um jogo tradicional é a Paciência, que utiliza 52 cartas. Inicialmente são formadas sete colunas com as cartas. A primeira coluna tem uma carta, a segunda tem duas cartas, a terceira tem três cartas, a quarta tem quatro cartas, e assim sucessivamente até a sétima coluna, a qual tem sete cartas, e o que sobra forma o monte, que são as cartas não utilizadas nas colunas. \nA quantidade de cartas que forma o monte é");

		List<OptionModel> listOptionModel = new ArrayList<OptionModel>();

		OptionModel op = new OptionModel();
		op.setId(0L);
		op.setDescription("21");
		op.setLetterShow(Util.letterOption(0));
		op.setQuestionModel(this.actualQuestion);
		listOptionModel.add(op);

		op = new OptionModel();
		op.setId(1L);
		op.setDescription("24");
		op.setLetterShow(Util.letterOption(1));
		op.setQuestionModel(this.actualQuestion);
		op.setFlagRight(Boolean.TRUE);
		listOptionModel.add(op);

		op = new OptionModel();
		op.setId(2L);
		op.setDescription("26");
		op.setLetterShow(Util.letterOption(2));
		op.setQuestionModel(this.actualQuestion);
		listOptionModel.add(op);

		op = new OptionModel();
		op.setId(3L);
		op.setDescription("28");
		op.setLetterShow(Util.letterOption(3));
		op.setQuestionModel(this.actualQuestion);
		listOptionModel.add(op);

		op = new OptionModel();
		op.setId(4L);
		op.setDescription("31");
		op.setLetterShow(Util.letterOption(4));
		op.setQuestionModel(this.actualQuestion);
		listOptionModel.add(op);

		actualQuestion.setListOptionModel(listOptionModel);
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

			if (i == 0) {
				op.setFlagRight(Boolean.TRUE);
			}

			listOptionModel.add(op);
		}
		return listOptionModel;
	}

	public String responseQuestion() {

		boolean checked = false;

		for (OptionModel optionModel : this.actualQuestion.getListOptionModel()) {

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

	public QuestionModel getActualQuestion() {
		return actualQuestion;
	}

	public void setActualQuestion(QuestionModel actualQuestion) {
		this.actualQuestion = actualQuestion;
	}

}
