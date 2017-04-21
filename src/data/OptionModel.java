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


/**
 * Entity Question
 * 
 * @author anchieta
 * 
 */
// @Entity
// @Table(name = "TB_OPTION")
// @NamedQueries({})
// @SequenceGenerator(name = "TB_OPTION_ID_SEQ", sequenceName = "TB_OPTION_ID_SEQ", allocationSize = 1)
public class OptionModel {

	// @Id
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_OPTION_ID_SEQ")
	// @Column(name = "OPTI_ID")
	private Long id;

	// @Column(name = "OPTI_DESCRIPTION")
	private String description;

	// @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	// @JoinColumn(name = "OPTI_QUESTION_ID")
	private QuestionModel questionModel;

	// @Column(name = "OPTI_FLAG_RIGHT")
	private boolean flagRight;

	// @Transient
	private boolean response;

	// @Transient
	private String letterShow;

	public OptionModel() {

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

	public QuestionModel getQuestionModel() {
		return questionModel;
	}

	public void setQuestionModel(QuestionModel questionModel) {
		this.questionModel = questionModel;
	}

	public boolean isFlagRight() {
		return flagRight;
	}

	public void setFlagRight(boolean flagRight) {
		this.flagRight = flagRight;
	}

	public boolean isResponse() {
		return response;
	}

	public void setResponse(boolean response) {
		this.response = response;
	}

	public String getLetterShow() {
		return letterShow;
	}

	public void setLetterShow(String letterShow) {
		this.letterShow = letterShow;
	}

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
		OptionModel other = (OptionModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
