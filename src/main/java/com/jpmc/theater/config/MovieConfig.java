package com.jpmc.theater.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.jpmc.theater.model.DailyShows;
import com.jpmc.theater.model.Movie;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties("movies")
@Data

public class MovieConfig {
		
	private List<DailyShows> screens;
	
}
