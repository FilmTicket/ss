package easybuy.server.controller;

import java.util.List;

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
	
	@ResponseBody
	@RequestMapping(value = "searchMovie", method = RequestMethod.POST)
	public Object searchMovie(String keyword) {
		List<Movie> movies = null;
		String message = null;
		
		if (Util.isBlank(keyword)) {
			message = "keyword为空";
		}
						
		if (message == null) {
			movies = movieService.searchMovie(keyword);			
			if (movies == null||movies.isEmpty()) {
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
	@RequestMapping(value = "searchMovie", method = RequestMethod.GET)
	public Object getPoppular(String keyword) {
		List<PopularMovie> populars = null;
		String message = null;
						
		if (message == null) {
			populars = movieService.getPoppular();			
			if (populars == null||populars.isEmpty()) {
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
