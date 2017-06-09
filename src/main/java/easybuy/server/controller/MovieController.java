package easybuy.server.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import easybuy.server.comm.Util;
import easybuy.server.model.HttpResult;
import easybuy.server.model.Movie;
import easybuy.server.model.PopularMovie;
import easybuy.server.service.MovieService;

@Controller
@RequestMapping("movie")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
	
	@ResponseBody
	@RequestMapping(value = "searchMovie", method = RequestMethod.POST)
	public Object searchMovie(String keyword, HttpSession session) {
		List<Movie> movies = null;
		String message = null;
		
		logger.info("Request to search movie, session id:" + session.getId());
		
		if (Util.isBlank(keyword)) {
			message = "keyword为空";
		}
						
		if (message == null) {
			movies = movieService.searchMovie(keyword);			
			if (movies == null || movies.isEmpty()) {
				message = "电影不存在";
			}
		}
		
		HttpResult<List<Movie>> result = null;
		
		if (message == null) {
			result = new HttpResult<List<Movie> >(1, "", movies);
		} else {
			result = new HttpResult<List<Movie> >(0, message, null);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "getPopular", method = RequestMethod.GET)
	public Object getPopular(String keyword, HttpSession session) {
		List<PopularMovie> populars = null;
		String message = null;
		
		logger.info("Request to get popular, session id:" + session.getId());
						
		if (message == null) {
			populars = movieService.getPopular();			
			if (populars == null || populars.isEmpty()) {
				message = "流行电影不存在";
			}
		}
		
		HttpResult<List<PopularMovie>> result = null;
		
		if (message == null) {
			result = new HttpResult<List<PopularMovie> >(1, "", populars);
		} else {
			result = new HttpResult<List<PopularMovie> >(0, message, null);
		}
		
		return result;
	}
}
