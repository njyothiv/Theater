package com.jpmc.theater.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jpmc.theater.constants.ApplicationConstants;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TicketFeeCalculator {
	
	
	/**
	 * To calculate total fee for the customer for the selected movie and show
	 * @param showing
	 * @return double
	 */
	public double calculateAdjustedTicketFee(Showing showing) {
		Movie selectedMovie = showing.getMovie();
		return Double.valueOf(selectedMovie.getTicketPrice()) - calculateDiscount(showing.getSequenceOfTheDay(),
															Integer.valueOf(selectedMovie.getSpecialCode()),
															Double.valueOf(selectedMovie.getTicketPrice()),
															showing.getShowStartTime());
	}
	
	/**
	 * To calculate the discount for the movie based on special code or time of the day or show sequence or day of the month. Apply whichever gives better discount to the customer 
	 * @param showSequence
	 * @param specialCode
	 * @param ticketPrice
	 * @return
	 */
	private double calculateDiscount(int showSequence, int specialCode, double ticketPrice, LocalDateTime lt) {
		double specialDiscount = 0;
        if (ApplicationConstants.MOVIE_CODE_SPECIAL == specialCode) {
            specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
        }
        
        double dayDiscount = 0;
        if (lt.getDayOfMonth() == 7) {
        	dayDiscount = 1; // Any movies showing on 7th, you'll get 1$ discount
        }

        double sequenceDiscount = 0;
        if (showSequence == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (showSequence == 2) {
            sequenceDiscount = 2; // $2 discount for 2nd show
        } 
       
        //time based discount
        int hourOfTheShow = lt.getHour();
        double timeDiscount = 0;
        if((hourOfTheShow >= 11 && hourOfTheShow <= 16)) {
        	
        	timeDiscount = ticketPrice * 0.25;
        	
        	if(hourOfTheShow == 16 && lt.getMinute() > 0)
        		timeDiscount = 0;       	
        	
        	log.info("timeDiscount: " + timeDiscount);
        } 
        
        // biggest discount wins
        List<Double> discountList = Arrays.asList(specialDiscount, sequenceDiscount,timeDiscount, dayDiscount);
        log.info("All discounts : "  + specialDiscount + " - " + sequenceDiscount + " - "  + timeDiscount + " - "  + dayDiscount);
         
        Optional<Double> maxDiscount = discountList.stream().max(Double::compare);
        
        return (!maxDiscount.isEmpty()) ? maxDiscount.get():0;
       		
	}
	

}
