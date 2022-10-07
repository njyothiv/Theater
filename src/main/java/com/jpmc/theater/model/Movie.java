package com.jpmc.theater.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Movie {
	private String title;
    private String description;
    private String runningTime;
    private Double ticketPrice;
    private String specialCode;
}
