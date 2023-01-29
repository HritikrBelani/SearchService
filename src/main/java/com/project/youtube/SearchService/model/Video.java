package com.project.youtube.SearchService.model;



import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Video {
	
	@Id
	public String id;
	
	public String title;
	
	public Date publishedTime;

	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Date getPublishedTime() {
		return publishedTime;
	}


	public void setPublishedTime(Date publishedTime) {
		this.publishedTime = publishedTime;
	}


	@Override
	public String toString() {
		return "Video [id=" + id + ", title=" + title + ", publishedTime=" + publishedTime + "]";
	}
	
}
