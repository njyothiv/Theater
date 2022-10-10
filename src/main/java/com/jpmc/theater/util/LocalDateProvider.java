package com.jpmc.theater.util;

import java.time.LocalDate;

/*
 * This is to return singleton for Local Date
 */
public class LocalDateProvider {
	
	/**
	 * @return Local Date as singleton instance
	 */
	private static LocalDateProvider instance;
	
	public static LocalDateProvider singleton() {
		if(instance == null) {
			instance = new LocalDateProvider();
		} 
		return instance;
	}
	
	public LocalDate currentDate() {
		return LocalDate.now();
	}

}
