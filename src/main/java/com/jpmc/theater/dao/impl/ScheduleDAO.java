package com.jpmc.theater.dao.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpmc.theater.config.MovieConfig;
import com.jpmc.theater.dao.IScheduleDAO;
import com.jpmc.theater.model.DailyShows;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.util.LocalDateProvider;
import com.jpmc.theater.util.MovieSequenceComparator;

import lombok.extern.slf4j.Slf4j;

/**
 * This DAO provides movie showings for each day
 * @author Jyothi
 *
 */
@Component
@Slf4j
public class ScheduleDAO implements IScheduleDAO {
	
	@Autowired
	private MovieConfig movieConfig;
	
		
	private List<Showing> showSchedules; 
	private LocalDateProvider provider;
	
	 
	/**
	 * This methods gets data from configuration file in the form of DailyShows object and loads to showing schedules list
	 * @return List of Schedules
	 */
	public List<Showing> getSchedules() {
		
		log.info("movie config : screens: " + movieConfig.getScreens());
		showSchedules = new ArrayList<>();			
		provider = LocalDateProvider.singleton();
		
		for(DailyShows dailyShows:movieConfig.getScreens()) {
			Showing showingObj = null;		
			log.info("movieObj name: " + dailyShows.toString());
			
			for(int i=0; i < dailyShows.getMovieSequence().size(); i++) {
				
				Integer hour = Integer.valueOf(dailyShows.getRunningTime())/60;
				Integer remainingMinutes = Integer.valueOf(dailyShows.getRunningTime())%60;
				
				StringBuilder sbShowLength = new StringBuilder()
									.append(hour)
									.append(hour==1?" hour " : " hours ")
									.append(remainingMinutes)
									.append(remainingMinutes==1?" minute" : " minutes");
				
				//creation of showing object
				showingObj = Showing.builder()
							.movie(Movie.builder()
									.title(dailyShows.getTitle())
									.description(dailyShows.getDescription())
									.specialCode(dailyShows.getSpecialCode())
									.runningTime(sbShowLength.toString())
									.ticketPrice(Double.valueOf(dailyShows.getTicketPrice()))
									.build())
							.sequenceOfTheDay(Integer.valueOf(dailyShows.getMovieSequence().get(i)))
							.showStartTime(LocalDateTime.of(provider.currentDate(), LocalTime.of(Integer.valueOf(dailyShows.getMovieTimings().get(i).substring(0,2)), Integer.valueOf(dailyShows.getMovieTimings().get(i).substring(2)))))
							.build();
				showSchedules.add(showingObj);
			}
									
		}
		
		//log.info("showSchedules size b4 sort: " + showSchedules.size());
		
		Collections.sort(showSchedules,new MovieSequenceComparator());
		
		log.info("showSchedules size after sort: " + showSchedules.size());
		
		return showSchedules;
				
	}
	

}

