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

import java.util.List;

/**
 * Entity Question
 * 
 * @author anchieta
 * 
 */
// @Entity
// @Table(name = "TB_QUESTION")
// @NamedQueries({ @NamedQuery(name = "researchQuestionBySubject", query = "SELECT q FROM QuestionModel q WHERE q.subjectModel.id = :idSubject") })
// @SequenceGenerator(name = "TB_QUESTION_ID_SEQ", sequenceName = "TB_QUESTION_ID_SEQ", allocationSize = 1)
public class QuestionModel {

	// @Id
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_QUESTION_ID_SEQ")
	// @Column(name = "QUES_ID")
	private Long id;

	// @Column(name = "QUES_DESCRIPTION")
	private String description;

	// @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	// @JoinColumn(name = "QUES_SUBJECT_ID")
	// private SubjectModel subjectModel;

	// @OneToMany(mappedBy = "questionModel", cascade = CascadeType.ALL)
	private List<OptionModel> listOptionModel;

	// @OneToMany(mappedBy = "questionImageModel", cascade = CascadeType.ALL)
	// private List<ImageQuestionModel> listImageQuestionModel;

	// @Column(name = "QUES_FLAG_ACTIVE")
	private boolean flagActive;

	// @Transient
	private OptionModel responseOptionModel;

	public QuestionModel() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	//
	// public SubjectModel getSubjectModel() {
	// return subjectModel;
	// }
	//
	// public void setSubjectModel(SubjectModel subjectModel) {
	// this.subjectModel = subjectModel;
	// }

	public List<OptionModel> getListOptionModel() {
		return listOptionModel;
	}

	public void setListOptionModel(List<OptionModel> listOptionModel) {
		this.listOptionModel = listOptionModel;
	}

	public boolean isFlagActive() {
		return flagActive;
	}

	public void setFlagActive(boolean flagActive) {
		this.flagActive = flagActive;
	}

	public OptionModel getResponseOptionModel() {
		return responseOptionModel;
	}

	public void setResponseOptionModel(OptionModel responseOptionModel) {
		this.responseOptionModel = responseOptionModel;
	}

	// public List<ImageQuestionModel> getListImageQuestionModel() {
	// return listImageQuestionModel;
	// }
	//
	// public void setListImageQuestionModel(List<ImageQuestionModel> listImageQuestionModel) {
	// this.listImageQuestionModel = listImageQuestionModel;
	// }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionModel other = (QuestionModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
