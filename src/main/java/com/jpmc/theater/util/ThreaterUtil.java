package com.jpmc.theater.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.jpmc.theater.model.Showing;

/**
 * 
 * @author Jyothi
 * This is utility class contains common utility/supporting methods that can be used across the project.
 */
public class ThreaterUtil {
	
	private LocalDateProvider provider = LocalDateProvider.singleton();
	
	public ThreaterUtil() {
		
	}
	
	/**
	 * print the movie schedules in readable format
	 * @param schedule
	 */
	public void printSchedule(ArrayList<Showing> schedule) {
		System.out.println(provider.currentDate());
		System.out.println("===================================================");
		schedule.forEach(s ->
		     System.out.println(s.getSequenceOfTheDay() + ": " + s.getShowStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(Duration.parse(s.getMovie().getRunningTime())) + " $" + s.getMovie().getTicketPrice())
		);
		System.out.println("===================================================");		
	}
	
	/**
	 * print the time duration in human readable format
	 * @param duration
	 * @return
	 */
	public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }
	
	/**
	 * appends with 's' for the text hours and minutes
	 * @param value
	 * @return
	 */
	private String handlePlural(long value) {        
        return value==1?"":"s";
    }
}
