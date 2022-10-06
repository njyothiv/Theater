package com.jpmc.theater.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class DailyShows {
	
	private String title;
    private String description;
    private String runningTime;
    private String ticketPrice;
    private String specialCode;
    private List<String> movieSequence;
    private List<String> movieTimings;


}