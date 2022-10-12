package com.jpmc.theater.dao.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpmc.theater.config.MovieConfig;
import com.jpmc.theater.dao.IScheduleDAO;
import com.jpmc.theater.model.DailyShows;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.util.LocalDateProvider;
import com.jpmc.theater.util.TheaterUtil;

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
	 
	/**
	 * This methods gets data from configuration file in the form of DailyShows object and loads to showing schedules list
	 * @return List of Schedules
	 */
	public List<Showing> getSchedules() {
			
		log.info("movie config : screens: " + movieConfig.getScreens());
		List<Showing> showSchedules = new ArrayList<>();			
		LocalDateProvider provider = LocalDateProvider.singleton();
		
		for(DailyShows dailyShows:movieConfig.getScreens()) {
			Showing showingObj = null;		
			log.info("movieObj name: " + dailyShows.toString());
			
			for(int i=0; i < dailyShows.getMovieSequence().size(); i++) {
				String runTime = TheaterUtil.humanReadableFormat(Duration.ofMinutes(Long.valueOf(dailyShows.getRunningTime())));//run time in readable format
				LocalTime localTime = LocalTime.parse(dailyShows.getMovieTimings().get(i)); //Gives Local time
								
				//creation of showing object
				showingObj = Showing.builder()
							.movie(Movie.builder()
									.title(dailyShows.getTitle())
									.description(dailyShows.getDescription())
									.specialCode(dailyShows.getSpecialCode())
									.runningTime(runTime)
									.ticketPrice(Double.valueOf(dailyShows.getTicketPrice()))
									.build())
							.sequenceOfTheDay(Integer.valueOf(dailyShows.getMovieSequence().get(i)))
							.showStartTime(LocalDateTime.of(provider.currentDate(), localTime))
							.build();
				showSchedules.add(showingObj);
			}
									
		}		
				
		showSchedules.sort(Comparator.comparing(Showing::getSequenceOfTheDay));
			
		return showSchedules;
				
	}
	

}

