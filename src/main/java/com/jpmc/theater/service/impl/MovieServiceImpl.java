package com.jpmc.theater.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpmc.theater.constants.ApplicationConstants;
import com.jpmc.theater.dao.impl.ScheduleDAO;
import com.jpmc.theater.exception.InvalidSequenceException;
import com.jpmc.theater.exception.InvalidTicketCountException;
import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.IMovieService;
import com.jpmc.theater.util.ThreaterUtil;
import com.jpmc.theater.util.TicketFeeCalculator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MovieServiceImpl implements IMovieService {

	@Autowired
	ScheduleDAO schedulDao;
	
	
	@Autowired
	TicketFeeCalculator ticketFeeCalculator;

	/**
	 * This method returns simple formatted movie schedules 
	 */
	public String getFormattedMovieSchedules() {

		String formattedSchedules = ThreaterUtil.printSchedule(schedulDao.getSchedules());
		return formattedSchedules;

	}

	/**
	 * This method returns list of movies along with their schedules
	 */
	public List<Showing> getMovieSchedules() {

		return schedulDao.getSchedules();

	}

	/**
	 * To make the reservation
	 * @param customer
	 * @param sequence
	 * @param howManyTickets
	 * @return Reservation
	 */
	public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
		
		List<Showing> schedules = schedulDao.getSchedules();
					
		if(sequence > schedules.size()) { throw new InvalidSequenceException(ApplicationConstants.INVALID_SHOW_SEQUENCE,"This show is not available for the day");} 
		
		if(howManyTickets==0) { throw new InvalidTicketCountException(ApplicationConstants.INVALID_TICKET_COUNT,"Please enter valid ticket count");} 
		
		Showing showing = schedules.get(sequence - 1);
		
		Double adjTicketFee = ticketFeeCalculator.calculateAdjustedTicketFee(showing);
		
		Double ticketPrice = showing.getMovie().getTicketPrice();
					
		log.info("MovieServiceImpl: showing : " + showing.toString());
		
		log.info("MovieServiceImpl: adjTicketFee : " + adjTicketFee);
			
		return new Reservation(customer, showing, howManyTickets,ticketPrice*howManyTickets, (ticketPrice-adjTicketFee)*howManyTickets , adjTicketFee*howManyTickets);
	}

}
