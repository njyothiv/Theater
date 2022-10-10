package com.jpmc.theater.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
public class TicketFeeCalculatorTest {
	@InjectMocks
	TicketFeeCalculator ticketFeeCalc = new TicketFeeCalculator();
	
	public TicketFeeCalculatorTest() {
	}
	
	/**
	 * To test special code discount scenario
	 */
	@Test
	public void testCalculateAdjustedTicketFeeSplCode() {
		
		//ticketFeeCalc = new TicketFeeCalculator();
		
		Movie spiderMan = new Movie("Toys2", "Toys2", "90", 11.5, "1");

		LocalDateTime lt = LocalDateTime.of(2022, 10, 8, 10, 00);	

		Showing showing = new Showing(spiderMan, 2, lt);
		
		System.out.println("ticketFeeCalc: " + ticketFeeCalc);

		Double adjTickeFee =  ticketFeeCalc.calculateAdjustedTicketFee(showing);
		
		assertEquals(9.2, adjTickeFee,0.001);
		
	}
	/**
	 * To test 2nd show discount scenario
	 */
	@Test
	public void testCalculateAdjustedTicketFeeFirstShow() {
		
		//ticketFeeCalc = new TicketFeeCalculator();
		
		Movie spiderMan = Movie.builder()
							.title("Toys2")
							.description("Toys 2")
							.runningTime("90")
							.ticketPrice(10.00)
							.specialCode("0")
							.build();
				

		LocalDateTime lt = LocalDateTime.of(2022, 10, 8, 10, 00);	

		Showing showing = new Showing(spiderMan, 1, lt);

		System.out.println("ticketFeeCalc: " + ticketFeeCalc);
		
		Double adjTickeFee =  ticketFeeCalc.calculateAdjustedTicketFee(showing);
		
		assertEquals(7, adjTickeFee,0.001);
		
	}
	
	/**
	 * To test 2nd show discount scenario
	 */
	@Test
	public void testCalculateAdjustedTicketFeeSecondShow() {
		
		//ticketFeeCalc = new TicketFeeCalculator();
		
		Movie spiderMan = new Movie("Toys2", "Toys2", "90", 11.5, "0");

		LocalDateTime lt = LocalDateTime.of(2022, 10, 8, 10, 00);	

		Showing showing = new Showing(spiderMan, 2, lt);

		System.out.println("ticketFeeCalc: " + ticketFeeCalc);
		
		Double adjTickeFee =  ticketFeeCalc.calculateAdjustedTicketFee(showing);
		
		assertEquals(9.5, adjTickeFee,0.001);		
	}

	
	/**
	 * To test 2nd show discount scenario
	 */
	@Test
	public void testCalculateAdjustedTicketFeeTimeShow() {
		
		//ticketFeeCalc = new TicketFeeCalculator();
		
		Movie spiderMan = new Movie("Toys2", "Toys2", "90", 10.0, "0");

		LocalDateTime lt = LocalDateTime.of(2022, 10, 8, 11, 30);	

		Showing showing = new Showing(spiderMan, 4, lt);

		System.out.println("ticketFeeCalc: " + ticketFeeCalc);
		
		Double adjTickeFee =  ticketFeeCalc.calculateAdjustedTicketFee(showing);
		
		assertEquals(7.5, adjTickeFee,0.001);		
	}
	
	/**
	 * To test 2nd show discount scenario
	 */
	@Test
	public void testCalculateAdjustedTicketFeeSeventhDayShow() {
		
		//ticketFeeCalc = new TicketFeeCalculator();
		
		Movie spiderMan = Movie.builder()
							.title("Toys2")
							.description("Toys 2")
							.runningTime("90")
							.ticketPrice(10.00)
							.specialCode("0")
							.build();
				

		LocalDateTime lt = LocalDateTime.of(2022, 10, 7, 16, 30);	

		Showing showing = new Showing(spiderMan, 5, lt);

		System.out.println("ticketFeeCalc: " + ticketFeeCalc);
		
		Double adjTickeFee =  ticketFeeCalc.calculateAdjustedTicketFee(showing);
		
		assertEquals(9, adjTickeFee,0.001);
		
	}
	
}
