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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import util.DaoFake;
import util.Util;
import util.ValidationUtil;
import data.OptionModel;
import data.QuestionModel;

public class QuestionDAO {

	public void insert(QuestionModel questionModel) {

		// super.insert(questionModel);

	}

	public void update(QuestionModel questionModel) {

		// super.update(questionModel);

	}

	public void delete(QuestionModel questionModel) {

		// super.setPropertySQL("query", questionModel.getId());
		//
		// super.setParameter("id");
		//
		// super.execute();
	}

	public QuestionModel find(QuestionModel questionModel) {

		// super.setPropertySQL("findPeriodById", questionModel.getId());
		//
		// return (PeriodModel) super.getObjectBean("id");
		return initFakeQuestion();

	}

	public QuestionModel searchNextMoreHard(double difficult) {

		List<QuestionModel> hardests = new ArrayList<QuestionModel>();
		for (QuestionModel question : DaoFake.getQuestions()) {
			if (question.getDifficulty() > difficult) {
				hardests.add(question);
			}
		}

		// sort by difficulty
		Collections.sort(hardests, new Comparator<QuestionModel>() {
			@Override
			public int compare(QuestionModel q1, QuestionModel q2) {
				if (q1.getDifficulty() > q2.getDifficulty())
					return 1;
				if (q1.getDifficulty() < q2.getDifficulty())
					return -1;
				return 0;
			}
		});

		if (ValidationUtil.isNullOrEmpty(hardests)) {
			hardests.set(0, null);
		}

		return hardests.get(0);

	}

	public QuestionModel searchNextMoreEasy(double difficult) {

		List<QuestionModel> easiest = new ArrayList<QuestionModel>();
		for (QuestionModel question : DaoFake.getQuestions()) {
			if (question.getDifficulty() < difficult) {
				easiest.add(question);
			}
		}

		// sort by difficulty
		Collections.sort(easiest, new Comparator<QuestionModel>() {
			@Override
			public int compare(QuestionModel q1, QuestionModel q2) {
				if (q1.getDifficulty() < q2.getDifficulty())
					return 1;
				if (q1.getDifficulty() > q2.getDifficulty())
					return -1;
				return 0;
			}
		});

		if (ValidationUtil.isNullOrEmpty(easiest)) {
			easiest.set(0, null);
		}

		return easiest.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<QuestionModel> researchQuestionBySubject(QuestionModel questionModel) {

		// super.setPropertySQL("researchQuestionBySubject", questionModel.getSubjectModel().getId());
		//
		// return super.getCollectionBean("idSubject");
		return new ArrayList<QuestionModel>();

	}

	private QuestionModel initFakeQuestion() {
		QuestionModel actualQuestion = new QuestionModel();
		actualQuestion.setId(1L);
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
}
