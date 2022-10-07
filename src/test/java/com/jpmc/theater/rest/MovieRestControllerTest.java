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

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.IMovieService;

/**
 * This is for unit testing MovieController
 * 
 * @author Jyothi
 *
 */
@WebMvcTest(value = MovieRestController.class)
public class MovieRestControllerTest {

	@MockBean
	private IMovieService movieSvc;

	@Autowired
	private MockMvc mockMvc;

	Movie spiderMan = new Movie("Toys2", "Toys2", "90", 11.5, "1");
	List<Showing> moviSchedules = List.of(new Showing(spiderMan, 2, LocalDateTime.now()));

	@Test
	public void testGetMovieSchedules() throws Exception {

		when(movieSvc.getMovieSchedules()).thenReturn(moviSchedules);

		MockHttpServletRequestBuilder requestBuilder =  MockMvcRequestBuilders.get("/movieschedules");
		
		ResultActions perform = mockMvc.perform(requestBuilder);
		 
		MvcResult mvcResult = perform.andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(200, status);
		
	}

}
