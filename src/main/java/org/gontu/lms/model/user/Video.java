package org.gontu.lms.model.user;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "VIDEO")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "video_id")
	private long id;
	private String name;
	private String description;
	private String YOUTUBEID;

	@Override
	public String toString() {
		return "Video [id=" + id + ", name=" + name + ", description=" + description + ", YOUTUBEID=" + YOUTUBEID + "]";
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

	public String getYOUTUBEID() {
		return YOUTUBEID;
	}

	public void setYOUTUBEID(String yOUTUBEID) {
		YOUTUBEID = yOUTUBEID;
	}
}
