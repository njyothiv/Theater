package com.jpmc.theater.service;

import java.util.List;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;

public interface IMovieService {

	public Reservation reserve(Customer customer, int sequence, int howManyTickets);
	
	public List<Showing> getMovieSchedules();
	
	public String getFormattedMovieSchedules();
}
