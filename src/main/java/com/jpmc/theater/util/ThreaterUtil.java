package com.jpmc.theater.util;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.jpmc.theater.model.Showing;

/**
 * 
 * @author Jyothi
 * This is utility class contains common utility/supporting methods that can be used across the project.
 */
public class ThreaterUtil {
			
	/**
	 * print the movie schedules in readable format
	 * @param list
	 */
	public static String printSchedule(List<Showing> list) {
		StringBuffer sb = new StringBuffer();
		
		sb.append(LocalDateProvider.singleton().currentDate() + "\n");
		
		sb.append("===================================================" + "\n");
		list.forEach(s ->
			sb.append(s.getSequenceOfTheDay() + ": " + s.getShowStartTime() + " " + s.getMovie().getTitle() + " " + s.getMovie().getRunningTime() + " $" +String.format("%,.2f", s.getMovie().getTicketPrice())  + "\n")
		);
		sb.append("===================================================" + "\n");
		return sb.toString();	
		
	}
	
	/**
	 * print the time duration in human readable format
	 * @param duration
	 * @return
	 */
	public static String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("%s hour%s %s minute%s", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }
	
	/**
	 * appends with 's' for the text hours and minutes
	 * @param value
	 * @return
	 */
	private static String handlePlural(long value) {        
        return value==1?"":"s";
    }
}
