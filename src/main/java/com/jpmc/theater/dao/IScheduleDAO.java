package com.jpmc.theater.dao;

import java.util.List;

import com.jpmc.theater.model.Showing;

public interface IScheduleDAO {
	
	public List<Showing> getSchedules();

}
