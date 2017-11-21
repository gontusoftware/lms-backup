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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MCQBANK")
public class MCQBANK {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MCQBANK_id")
	private long id;
	private String name;
	private String description;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "MCQBANK_MCQ", joinColumns = @JoinColumn(name = "MCQBANK_ID", referencedColumnName = "mcqbank_id"), inverseJoinColumns = @JoinColumn(name = "MCQ_ID", referencedColumnName = "mcq_id"))
	private Set<MCQ> mcqs;

	@Override
	public String toString() {
		return "MCQBANK [id=" + id + ", name=" + name + ", description=" + description + ", mcqs=" + mcqs + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<MCQ> getMcqs() {
		return mcqs;
	}

	public void setMcqs(Set<MCQ> mcqs) {
		this.mcqs = mcqs;
	}

}
