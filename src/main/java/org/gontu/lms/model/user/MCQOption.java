package org.gontu.lms.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MCQ_OPTION")
public class MCQOption {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String optn;

	private boolean answer;

	@Override
	public String toString() {
		return "MCQOption [id=" + id + ", option=" + optn + ", answer=" + answer + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOptn() {
		return optn;
	}

	public void setOptn(String optn) {
		this.optn = optn;
	}

	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}

}
