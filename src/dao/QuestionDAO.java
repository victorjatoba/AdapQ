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

import com.itemanalysis.psychometrics.irt.model.ItemResponseModel;

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

	/**
	 * Search the prox hardest question in relation to difficult passed by param.
	 * 
	 * @return the prox hardest question
	 */
	public QuestionModel searchNextMoreHard(double difficult) {

		List<QuestionModel> hardest = new ArrayList<QuestionModel>();
		for (QuestionModel question : DaoFake.getQuestions()) {
			if (question.getDifficulty() > difficult) {
				hardest.add(question);
			}
		}

		// sort questions
		hardest = Util.sortQuestionListFromEasierToHardest(hardest);

		QuestionModel nextMoreHard = null;
		// get first from sorted questions
		if (!ValidationUtil.isNullOrEmpty(hardest)) {
			nextMoreHard = hardest.get(0);
		}

		return nextMoreHard;

	}

	/**
	 * Search the prox hardest question in relation to difficult passed by param.
	 * 
	 * @return the prox hardest question
	 */
	public List<ItemResponseModel> searchQuestionsMoreHard(double difficult) {

		List<ItemResponseModel> hardest = new ArrayList<ItemResponseModel>();
		for (ItemResponseModel question : DaoFake.getIrms()) {
			if (question.getDifficulty() > difficult) {
				hardest.add(question);
			}
		}

		return hardest;

	}

	/**
	 * Search the prox hardest question in relation to difficult passed by param.
	 * 
	 * @return the prox hardest question
	 */
	public List<ItemResponseModel> searchQuestionsMoreEasy(double difficult) {

		List<ItemResponseModel> easiests = new ArrayList<ItemResponseModel>();
		for (ItemResponseModel question : DaoFake.getIrms()) {
			if (question.getDifficulty() < difficult) {
				easiests.add(question);
			}
		}

		return easiests;

	}

	/**
	 * Search the prox easier question in relation to difficult passed by param.
	 * 
	 * @return the prox easier question
	 */
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

	public QuestionModel findByDifficult(double difficulty) {
		List<QuestionModel> questions = DaoFake.getQuestions();
		QuestionModel questionModelFound = null;

		for (QuestionModel questionModel : questions) {
			if (questionModel.getDifficulty() == difficulty) {
				questionModelFound = questionModel;
				break;
			}
		}

		return questionModelFound;
	}

	public QuestionModel findByDifficultyAndName(double difficultyToBeSearch, String nameToBeSearch) {
		List<QuestionModel> questions = DaoFake.getQuestions();
		QuestionModel questionModelFound = null;

		for (QuestionModel questionModel : questions) {
			String name = "v" + (questionModel.getId() + 1);
			if (questionModel.getDifficulty() == difficultyToBeSearch && nameToBeSearch.equals(name)) {
				questionModelFound = questionModel;
				break;
			}
		}

		return questionModelFound;
	}
}
