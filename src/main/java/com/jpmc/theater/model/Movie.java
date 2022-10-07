package com.jpmc.theater.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie {
	private String title;
    private String description;
    private String runningTime;
    private Double ticketPrice;
    private String specialCode;
}
