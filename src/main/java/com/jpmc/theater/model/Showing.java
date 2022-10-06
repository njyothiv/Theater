package com.jpmc.theater.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Showing  {
	private Movie movie;
	private int sequenceOfTheDay;
	private LocalDateTime showStartTime;
}
