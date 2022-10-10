package com.jpmc.theater.dao.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jpmc.theater.config.MovieConfig;
import com.jpmc.theater.dao.impl.ScheduleDAO;
import com.jpmc.theater.model.DailyShows;
import com.jpmc.theater.model.Showing;

@ExtendWith(MockitoExtension.class)
public class ScheduleDAOTest {
	
	@InjectMocks
	private ScheduleDAO mockScheduleDao;
	
	@Mock private MovieConfig mockMovieConfig;
	
	private List<DailyShows> showSchedules;
	
	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
		
		List<String> movieSeq = new ArrayList<>();
		movieSeq.add("3");
		movieSeq.add("1");
		movieSeq.add("2");
		
		
		List<String> movieTimings = new ArrayList<>();
		movieTimings.add("14:30");
		movieTimings.add("10:30");
		movieTimings.add("12:40");
				
		DailyShows spiderManShows = DailyShows.builder()
				.title("Toys2")
				.description("Toys 2")
				.runningTime("90")
				.ticketPrice("10.00")
				.specialCode("0")
				.movieSequence(movieSeq)
				.movieTimings(movieTimings)
				.build();
		
		showSchedules = new ArrayList<>();
		showSchedules.add(spiderManShows);
		
	}
	
		
	@Test
	public void testGetSchedules() {
		Mockito.when(mockMovieConfig.getScreens()).thenReturn(showSchedules);	
		List<Showing> schedules = mockScheduleDao.getSchedules();	
		assertEquals(3, schedules.size());
	}


}
