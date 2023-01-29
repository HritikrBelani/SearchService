package com.project.youtube.SearchService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.youtube.SearchService.model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video,String>{
	
}
