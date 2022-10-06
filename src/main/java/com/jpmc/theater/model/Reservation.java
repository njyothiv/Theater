package com.jpmc.theater.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Reservation {
	private Customer customer;
	private Showing showing;
	private int audienceCount;
	private double totalTicketPrice;
	private double discountApplied;
	private double finalTicketTotalPrice;
}
