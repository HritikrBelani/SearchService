package com.project.youtube.SearchService.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.youtube.SearchService.model.Video;
import com.project.youtube.SearchService.repository.VideoRepository;

@RestController
public class VideoController {

	@Autowired
	VideoRepository videoRepository;

	@GetMapping(path = "/videos")
	public ResponseEntity<Map<String, Object>> getAllVideos(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		try {
			List<Video> videos = new ArrayList<Video>();
			Pageable paging = PageRequest.of(page, size,Sort.by("publishedTime").descending());

			Page<Video> pageTuts;

			pageTuts = videoRepository.findAll(paging);

			videos = pageTuts.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("videos", videos);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
