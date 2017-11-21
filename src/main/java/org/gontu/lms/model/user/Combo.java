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
@Table(name = "COMBO")
public class Combo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "combo_id")
	private long id;
	private String name;
	private String description;

	private double prize;

	private double discount = 0;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "COMBO_COURSE", joinColumns = @JoinColumn(name = "COMBO_ID", referencedColumnName = "combo_id"), inverseJoinColumns = @JoinColumn(name = "COURSE_ID", referencedColumnName = "course_id"))
	private Set<Course> courses;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "COMBO_MODULE", joinColumns = @JoinColumn(name = "COMBO_ID", referencedColumnName = "combo_id"), inverseJoinColumns = @JoinColumn(name = "MODULE_ID", referencedColumnName = "module_id"))
	private Set<Module> modules;

	@Override
	public String toString() {
		return "Combo [id=" + id + ", name=" + name + ", description=" + description + ", prize=" + prize
				+ ", discount=" + discount + ", courses=" + courses + ", modules=" + modules + "]";
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

	public double getPrize() {
		return prize;
	}

	public void setPrize(double prize) {
		this.prize = prize;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Set<Module> getModules() {
		return modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

}
