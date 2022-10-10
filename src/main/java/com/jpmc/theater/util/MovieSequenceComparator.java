package com.jpmc.theater.util;

import java.util.Comparator;

import com.jpmc.theater.model.Showing;

/*
 * compares Showing objects based on the the movie sequence  
 */
public class MovieSequenceComparator implements Comparator<Showing> {
  
	    public int compare(Showing s1, Showing s2)
	    {
	        return ((s1.getSequenceOfTheDay() == s2.getSequenceOfTheDay()) ? 0: ((s1.getSequenceOfTheDay() > s2.getSequenceOfTheDay()) ? 1 :-1));
	    }

}
