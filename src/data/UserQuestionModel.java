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
package data;

import java.util.Date;

/**
 * Entity responsible to rec User response to the Question
 * 
 * @author victorjatoba
 * 
 */
public class UserQuestionModel {

	private Long id;

	private UserModel userModel;

	private QuestionModel questionModel;

	private boolean answer;

	private Date wastedTimeToAnswer;

	private Date realizationDate;

	public UserQuestionModel(UserModel userModel, QuestionModel questionModel, boolean answer, Date wastedTimeToAnswer) {
		super();
		this.userModel = userModel;
		this.questionModel = questionModel;
		this.answer = answer;
		this.wastedTimeToAnswer = wastedTimeToAnswer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public QuestionModel getQuestionModel() {
		return questionModel;
	}

	public void setQuestionModel(QuestionModel questionModel) {
		this.questionModel = questionModel;
	}

	public Date getRealizationDate() {
		return realizationDate;
	}

	public void setRealizationDate(Date realizationDate) {
		this.realizationDate = realizationDate;
	}

	public boolean getAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}

	public Date getWastedTimeToAnswer() {
		return wastedTimeToAnswer;
	}

	public void setWastedTimeToAnswer(Date wastedTimeToAnswer) {
		this.wastedTimeToAnswer = wastedTimeToAnswer;
	}

}
