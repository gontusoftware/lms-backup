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
@Table(name = "MODULE")
public class Module {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "module_id")
	private long id;
	private String name;
	private String description;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "MODULE_VIDEO", joinColumns = @JoinColumn(name = "MODULE_ID", referencedColumnName = "module_id"), inverseJoinColumns = @JoinColumn(name = "VIDEO_ID", referencedColumnName = "video_id"))
	private Set<Video> videos;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "MODULE_MCQBANK", joinColumns = @JoinColumn(name = "MODULE_ID", referencedColumnName = "module_id"), inverseJoinColumns = @JoinColumn(name = "MCQBANK_ID", referencedColumnName = "MCQBANK_id"))
	private Set<MCQBANK> mcqbanks;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "MODULE_ASSIGNMENT", joinColumns = @JoinColumn(name = "MODULE_ID", referencedColumnName = "module_id"), inverseJoinColumns = @JoinColumn(name = "ASSIGNMENT_ID", referencedColumnName = "assignment_id"))
	private Set<Assignment> assignments;

	@Override
	public String toString() {
		return "Module [id=" + id + ", name=" + name + ", description=" + description + ", videos=" + videos
				+ ", mcqbanks=" + mcqbanks + ", assignments=" + assignments + "]";
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

	public Set<Video> getVideos() {
		return videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}

	public Set<MCQBANK> getMcqbanks() {
		return mcqbanks;
	}

	public void setMcqbanks(Set<MCQBANK> mcqbanks) {
		this.mcqbanks = mcqbanks;
	}

	public Set<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(Set<Assignment> assignments) {
		this.assignments = assignments;
	}

}
