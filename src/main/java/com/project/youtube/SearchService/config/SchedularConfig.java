package com.project.youtube.SearchService.config;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.GenericData;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTube.Search.List;
import com.google.api.services.youtube.model.SearchListResponse;
import com.project.youtube.SearchService.model.Video;
import com.project.youtube.SearchService.repository.VideoRepository;

@Configuration
@EnableScheduling
public class SchedularConfig {

	@Autowired
	YouTube youtubeService;

	@Autowired
	VideoRepository videoRepository;

	@SuppressWarnings("unchecked")
	@Scheduled(fixedRate = 1000000000)
	public void retrieveVideoListandDump() throws GeneralSecurityException, IOException, GoogleJsonResponseException {
		YouTube.Search.List request = youtubeService.search().list("snippet").set("key",
				"AIzaSyA36ZUq0AIup900QMS151rBMbjc4hxIGNM");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MILLISECOND, -1000000000);
		Date result = calendar.getTime();
		System.out.println("past: " + result.getTime());
		SearchListResponse response = request.setMaxResults(25L).setPublishedAfter(new DateTime(result))
				.execute();

		for (Map<String, Object> video : response.getItems()) {
			Video newVideo = new Video();
			Map<String, Object> snippetJson = (Map<String, Object>) video.get("snippet");
			Map<String, Object> idJson = (Map<String, Object>) video.get("id");
			DateTime date = (DateTime) snippetJson.get("publishedAt");
			newVideo.id = (String) idJson.get("videoId");
			newVideo.publishedTime = new Date(date.getValue());
			newVideo.title = (String) snippetJson.get("title");
			System.out.println(newVideo.toString());
			videoRepository.save(newVideo);
		}
	}
}
