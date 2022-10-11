package com.jpmc.theater.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.IMovieService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MovieRestController {
	
	@Autowired
	IMovieService movieSvc;
	
	/**
	 * This API will return list of movie schedules
	 * @return showing list
	 */
	@GetMapping(value="/movieschedules",
		 	 produces = {"application/json","text/plain"})
	public ResponseEntity<List<Showing>> getMovieSchedules() {
	
		return new ResponseEntity<>(movieSvc.getMovieSchedules(),HttpStatus.OK);			
		
	}
	
	/**
	 * This movie will return movie schedules in the simple readble format
	 * @return String
	 */
	@GetMapping(value="/formattedmovieschedules",
		 	 produces = {"application/json","text/plain"})
	public ResponseEntity<String> getFormattedMovieSchedules() {
	
		return new ResponseEntity<>(movieSvc.getFormattedMovieSchedules(),HttpStatus.OK);			
		
	}
	
	

	/**
	 * To make reservation for the movie. Requires customer name, show number, number of tickets
	 * @param customerName
	 * @param sequence
	 * @param howManyTickets
	 * @return Reservation
	 */
	@GetMapping(value="/reserve",
		 	 produces = {"application/json"})
	public ResponseEntity<Reservation> reserve(@RequestParam("customerName")String customerName, @RequestParam("showNum")Integer sequence, @RequestParam("ticketCount")Integer howManyTickets) {
		log.debug("log controller updated application prop debug: " + customerName + " - " + sequence + " - " + howManyTickets);
		Customer custObj = new Customer(UUID.randomUUID().toString(), customerName);
		return new ResponseEntity<>(movieSvc.reserve(custObj,sequence,howManyTickets),HttpStatus.OK);
	}

}
