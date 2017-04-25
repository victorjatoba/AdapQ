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
import java.util.List;

import util.DaoFake;
import util.Util;
import util.ValidationUtil;
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
		return null;

	}

	public QuestionModel searchNextMoreHard(double difficult) {

		List<QuestionModel> easiests = new ArrayList<QuestionModel>();
		for (QuestionModel question : DaoFake.getQuestions()) {
			if (question.getDifficulty() > difficult) {
				easiests.add(question);
			}
		}

		// sort questions
		easiests = Util.sortQuestionListFromEasierToHardest(easiests);

		QuestionModel nextMoreHard = null;
		// get first from sorted questions
		if (!ValidationUtil.isNullOrEmpty(easiests)) {
			nextMoreHard = easiests.get(0);
		}

		return nextMoreHard;

	}

	public QuestionModel searchNextMoreEasy(double difficult) {

		List<QuestionModel> easiers = new ArrayList<QuestionModel>();
		for (QuestionModel question : DaoFake.getQuestions()) {
			if (question.getDifficulty() < difficult) {
				easiers.add(question);
			}
		}

		// sort questions
		easiers = Util.sortQuestionListFromHardestToEasier(easiers);

		QuestionModel nextMoreEasy = null;
		// get first from sorted questions
		if (!ValidationUtil.isNullOrEmpty(easiers)) {
			nextMoreEasy = easiers.get(0);
		}

		return nextMoreEasy;
	}

	/***
	 * Search the question that have the average difficulty level.
	 * 
	 * @return the average question
	 */
	public QuestionModel searchTheAverageQuestion() {

		QuestionModel averageQuestion = null;
		List<QuestionModel> sortedQuestions = Util.sortQuestionListFromEasierToHardest(DaoFake.getQuestions());

		if (!ValidationUtil.isNullOrEmpty(sortedQuestions)) {
			int averagePosition = sortedQuestions.size() / 2;
			averageQuestion = sortedQuestions.get(averagePosition);
		}

		return averageQuestion;
	}

	@SuppressWarnings("unchecked")
	public List<QuestionModel> researchQuestionBySubject(QuestionModel questionModel) {

		// super.setPropertySQL("researchQuestionBySubject", questionModel.getSubjectModel().getId());
		//
		// return super.getCollectionBean("idSubject");
		return new ArrayList<QuestionModel>();

	}

}
