package com.jpmc.theater.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.IMovieService;

/**
 * This is for unit testing MovieController
 * 
 * @author Jyothi
 *
 */
@WebMvcTest(value = MovieRestController.class)
class MovieRestControllerTest {

	@MockBean
	private IMovieService movieSvc;

	@Autowired
	private MockMvc mockMvc;

	Movie spiderMan = new Movie("Toys2", "Toys2", "90", 11.5, "1");
	List<Showing> movieSchedules = List.of(new Showing(spiderMan, 2, LocalDateTime.now()));

	@Test
	void testGetMovieSchedules() throws Exception {

		when(movieSvc.getMovieSchedules()).thenReturn(movieSchedules);

		MockHttpServletRequestBuilder requestBuilder =  MockMvcRequestBuilders.get("/movieschedules");
		
		ResultActions perform = mockMvc.perform(requestBuilder);
		 
		MvcResult mvcResult = perform.andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(200, status);
		
	}
	
	@Test
	void testGetFormattedMovieSchedules() throws Exception {

		when(movieSvc.getFormattedMovieSchedules()).thenReturn("1:Toys2 10.5");

		MockHttpServletRequestBuilder requestBuilder =  MockMvcRequestBuilders.get("/formattedmovieschedules");
		
		ResultActions perform = mockMvc.perform(requestBuilder);
		 
		MvcResult mvcResult = perform.andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(200, status);
		
	}
	
	
	@Test
	void testReserve() throws Exception {
		
		Customer cust1 = new Customer("1","Jyothi"); 
		
		Movie spiderMan = new Movie("Toys2", "Toys2", "90", 10.0, "1");
		
		Showing show1 = new Showing(spiderMan, 2, LocalDateTime.now()); 

		when(movieSvc.reserve(cust1, 2, 5)).thenReturn(new Reservation(cust1,show1,5,50,5,45));
				
		MockHttpServletRequestBuilder requestBuilder =  MockMvcRequestBuilders.get("/reserve")
														.param("customerName", "Jyothi")
											            .param("showNum", "1")
											            .param("ticketCount", "5");
		
		ResultActions perform = mockMvc.perform(requestBuilder);
		 
		MvcResult mvcResult = perform.andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(200, status);
		
	}
	

}
