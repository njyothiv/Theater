package com.jpmc.theater.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jpmc.theater.dao.impl.ScheduleDAO;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;

@ExtendWith(MockitoExtension.class)
public class TicketFeeCalculatorTest {
	
	@InjectMocks
	private TicketFeeCalculator ticketFeeCalc;
	
	@Mock private ScheduleDAO mockDao;
	@Mock private TicketFeeCalculator ticketFeeCalculator;
	@Mock private TheaterUtil threaterUtil;
	
	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);	
	}
		
	/**
	 * To test special code discount scenario
	 */
	@Test
	public void testCalculateAdjustedTicketFeeSplCode() {
		
		Movie spiderMan = Movie.builder()
				.title("Toys2")
				.description("Toys 2")
				.runningTime("90")
				.ticketPrice(11.50)
				.specialCode("1")
				.build();
		LocalDateTime lt = LocalDateTime.of(2022, 10, 8, 10, 00);		
		Showing showing = Showing.builder()
						  .movie(spiderMan)
						  .sequenceOfTheDay(2)
						  .showStartTime(lt)
						  .build();
		Double adjTickeFee =  ticketFeeCalc.calculateAdjustedTicketFee(showing);
		assertEquals(9.2, adjTickeFee,0.001);
	}
	
	/**
	 * To test 1st show discount scenario pass sequenceOfTheDay as 1
	 */
	@Test
	public void testCalculateAdjustedTicketFeeFirstShow() {
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
				  .sequenceOfTheDay(1)
				  .showStartTime(lt)
				  .build();		
		Double adjTickeFee =  ticketFeeCalc.calculateAdjustedTicketFee(showing);
		assertEquals(7, adjTickeFee,0.001);
	}
	
	/**
	 * To test 2nd show discount scenario pass sequenceOfTheDay as 2
	 */
	@Test
	public void testCalculateAdjustedTicketFeeSecondShow() {
		
		Movie spiderMan = Movie.builder()
				.title("Toys2")
				.description("Toys 2")
				.runningTime("90")
				.ticketPrice(11.50)
				.specialCode("0")
				.build();
		LocalDateTime lt = LocalDateTime.of(2022, 10, 8, 10, 00);	
		Showing showing = Showing.builder()
			  .movie(spiderMan)
			  .sequenceOfTheDay(2)
			  .showStartTime(lt)
			  .build();		
		Double adjTickeFee =  ticketFeeCalc.calculateAdjustedTicketFee(showing);
		assertEquals(9.5, adjTickeFee,0.001);		
	}

	
	/**
	 * To test show discount based on time of the day modify local time to 11.30 AM
	 */
	@Test
	public void testCalculateAdjustedTicketFeeTimeShow() {		
		
		Movie spiderMan = Movie.builder()
				.title("Toys2")
				.description("Toys 2")
				.runningTime("90")
				.ticketPrice(10.00)
				.specialCode("0")
				.build();
		LocalDateTime lt = LocalDateTime.of(2022, 10, 8, 11, 30);	
		Showing showing = Showing.builder()
			  .movie(spiderMan)
			  .sequenceOfTheDay(4)
			  .showStartTime(lt)
			  .build();		
		Double adjTickeFee =  ticketFeeCalc.calculateAdjustedTicketFee(showing);
		assertEquals(7.5, adjTickeFee,0.001);		
	}
	
	/**
	 * To test show discount based on 7th day of the month scenario include the day as 7th in Local date field
	 */
	@Test
	public void testCalculateAdjustedTicketFeeSeventhDayShow() {
		Movie spiderMan = Movie.builder()
							.title("Toys2")
							.description("Toys 2")
							.runningTime("90")
							.ticketPrice(10.00)
							.specialCode("0")
							.build();			
		LocalDateTime lt = LocalDateTime.of(2022, 10, 7, 16, 30);	
		Showing showing = new Showing(spiderMan, 5, lt);
		Double adjTickeFee =  ticketFeeCalc.calculateAdjustedTicketFee(showing);
		assertEquals(9, adjTickeFee,0.001);
	}
	
}
