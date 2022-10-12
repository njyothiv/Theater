package com.jpmc.theater.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jpmc.theater.model.Showing;

@Component
public interface IScheduleDAO {
	
	public List<Showing> getSchedules();

}
