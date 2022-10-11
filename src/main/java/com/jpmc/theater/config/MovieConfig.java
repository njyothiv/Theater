package com.jpmc.theater.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.jpmc.theater.model.DailyShows;

import lombok.Data;

@Configuration
@ConfigurationProperties("movies")
@Data

public class MovieConfig {
		
	private List<DailyShows> screens;
	
}
