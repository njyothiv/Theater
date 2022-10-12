package com.jpmc.theater.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
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

import com.jpmc.theater.dao.impl.ScheduleDAO;
import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.util.TheaterUtil;
import com.jpmc.theater.util.TicketFeeCalculator;

@ExtendWith(MockitoExtension.class)
public class MovieServiceImplTest {
	
	@InjectMocks
	private MovieServiceImpl mockSvc;
	
	@Mock private ScheduleDAO mockDao;
	@Mock private TicketFeeCalculator ticketFeeCalculator;
	@Mock private TheaterUtil threaterUtil;
	
	private List<Showing> showSchedules;
	private Showing showing;
	Customer cust;
	
	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
		
		showSchedules = new ArrayList<>();	
		Movie spiderMan = Movie.builder()
				.title("Toys2")
				.description("Toys 2")
				.runningTime("90")
				.ticketPrice(10.00)
				.specialCode("0")
				.build();
		
		LocalDateTime lt = LocalDateTime.of(2022, 10, 8, 10, 00);
		showing = Showing.builder()
						  .movie(spiderMan)
						  .sequenceOfTheDay(1)
						  .showStartTime(lt)
						  .build();
		showSchedules.add(showing);
		Mockito.when(mockDao.getSchedules()).thenReturn(showSchedules);	
		
		cust = new Customer("123","Jyothi"); 
	}
	
		
	@Test
	public void testGetMovieSchedules() {	
		List<Showing> schedules = mockSvc.getMovieSchedules();
		assertEquals(1, schedules.size());
	}
	
	
	@Test
	public void testGetFormattedMovieSchedules() {
		Mockito.when(threaterUtil.printSchedule(showSchedules)).thenReturn("formattedmovieschedules");
		String formattedStr = mockSvc.getFormattedMovieSchedules();
		assertEquals("formattedmovieschedules", formattedStr);
	}
	
	
	@Test
	public void testReserve() {	
		Mockito.when(ticketFeeCalculator.calculateAdjustedTicketFee(showing)).thenReturn((double) 9);			
		Reservation ticketReservation = mockSvc.reserve(new Customer("123","Jyothi"), 1, 4);
				
		assertAll(() -> assertEquals("Jyothi", ticketReservation.getCustomer().getName() ),
				  () -> assertEquals(40, ticketReservation.getTotalTicketPrice()),
				  () -> assertEquals(4, ticketReservation.getDiscountApplied())
				  );
			
	}
	
	/*
	 * sequence passed as 0 to validate the exception
	 */
	@Test
	public void whenInvalidSeqExpThrownZeroSeq_thenAssertionSucceeds() {
	    
		Mockito.when(ticketFeeCalculator.calculateAdjustedTicketFee(showing)).thenReturn((double) 9);				
		
		
		Exception exception = assertThrows(RuntimeException.class, () -> {
			mockSvc.reserve(cust, 0, 4);
	    });

	    String expectedMessage = "This show is not available for the day";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	
	/*
	 * sequence passed as 3 to validate the exception
	 */
	@Test
	public void whenInvalidSeqExpThrownBiggerSeq_thenAssertionSucceeds() {
	    
		Mockito.when(ticketFeeCalculator.calculateAdjustedTicketFee(showing)).thenReturn((double) 9);				
		
		Exception exception = assertThrows(RuntimeException.class, () -> mockSvc.reserve(cust, 3, 4)
	    );

	    String expectedMessage = "This show is not available for the day";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	
	/*
	 * Count of tickets passed as 0 to validate the exception
	 */
	@Test
	public void whenInvalidTktCountExpThrownZeroCount_thenAssertionSucceeds() {    
		Mockito.when(ticketFeeCalculator.calculateAdjustedTicketFee(showing)).thenReturn((double) 9);				
		Exception exception = assertThrows(RuntimeException.class, () -> {
			mockSvc.reserve(cust, 1, 0);
	    });
	    String expectedMessage = "Please enter valid ticket count";
	    String actualMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(expectedMessage));
	}
}
