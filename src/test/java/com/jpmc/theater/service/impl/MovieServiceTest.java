package com.jpmc.theater.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jpmc.theater.dao.impl.ScheduleDAO;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;

@ExtendWith(MockitoExtension.class)

@RunWith(SpringJUnit4ClassRunner.class)
public class MovieServiceTest {
	
	@InjectMocks
	private MovieServiceImpl mockSvc;
	
	@Mock private ScheduleDAO mockDao;
	
	private List<Showing> showSchedules;
	
	@Before 
	public void setUp()
    {
		showSchedules = new ArrayList<>();	
		Movie spiderMan = Movie.builder()
				.title("Toys2")
				.description("Toys 2")
				.runningTime("90")
				.ticketPrice(10.00)
				.specialCode("0")
				.build();
		
		LocalDateTime lt = LocalDateTime.of(2022, 10, 8, 10, 00);
		
		Showing showing = Showing.builder()
						  .movie(spiderMan)
						  .sequenceOfTheDay(2)
						  .showStartTime(lt)
						  .build();
			
		showSchedules.add(showing);
    }
	
	@Test
	public void testGetMovieSchedules() {
		Mockito.when(mockDao.getSchedules()).thenReturn(showSchedules);	
		List<Showing> schedules = mockSvc.getMovieSchedules();
		assertEquals(1, schedules.size());
	}

	
//	@Test(expected=InvalidSequenceException.class)
//	public void testReserve() {
//		//mockStatic(ThreaterUtil.class);
//		
//		//assertThat(ThreaterUtil.printSchedule(mockDao.getSchedules()).is
//		
//		//Mockito.when(ThreaterUtil.printSchedule(new ArrayList<>())).thenReturn("Text Formatted Daily Shows");
//		
//		Mockito.when(mockDao.getSchedules()).thenReturn(new ArrayList<>());
//		
//		//IMovieService movieSvc = new MovieServiceImpl();
//		
//		System.out.println("mockSvc service : " + mockSvc);
//		List<Showing> formattedStr = mockSvc.getMovieSchedules();
//		
//		
//		//log.info("formattedStr: " + formattedStr);
//		
//		
//	}

}
