package org.gontu.lms.model.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MCQ")
public class MCQ {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MCQ_id")
	private long id;

	private String question;

	private String level; // difficulty associated with this problem easy,
							// intermediate, advance

	private String description;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "mcq_id")
	private Set<MCQOption> mcqoptions;

	@Override
	public String toString() {
		return "MCQ [id=" + id + ", question=" + question + ", level=" + level + ", description=" + description
				+ ", mcqoptions=" + mcqoptions + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<MCQOption> getMcqoptions() {
		return mcqoptions;
	}

	public void setMcqoptions(Set<MCQOption> mcqoptions) {
		this.mcqoptions = mcqoptions;
	}

}
