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
import easybuy.server.model.MovieTime;
import easybuy.server.model.PopularMovie;
import easybuy.server.model.SeatInfo;
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
	
	@ResponseBody
	@RequestMapping(value = "getMovieTime", method = RequestMethod.POST)
	public Object getMovieTime(String theaterId, String movieId, String date, HttpSession session) {
		List<MovieTime> movietimes = null;
		String message = null;
		
		logger.info("Request to getMovieTime, session id:" + session.getId());
						
		if (message == null) {
			movietimes = movieService.getMovieTime(theaterId, movieId, date);		
			if (movietimes == null || movietimes.isEmpty()) {
				message = "电影排场不存在";
			}
		}
		
		HttpResult<List<MovieTime>> result = null;
		
		if (message == null) {
			result = new HttpResult<List<MovieTime> >(1, "", movietimes);
		} else {
			result = new HttpResult<List<MovieTime> >(0, message, null);
		}	
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "getMoviesByTheaterId", method = RequestMethod.POST)
	public Object getMoviesByTheaterId(String theaterId, HttpSession session) {
		List<Movie> movies = null;
		String message = null;
		
		logger.info("Request to getMoviesByTheaterId, session id:" + session.getId());
						
		if (message == null) {
			movies = movieService.getMoviesByTheaterId(theaterId);		
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
	@RequestMapping(value = "getSeatInfoByMovieTimeId", method = RequestMethod.POST)
	public Object getSeatInfoByMovieTimeId(String movieTimeId, HttpSession session) {
		List<SeatInfo> SeatInfos = null;
		
		logger.info("Request to getSeatInfoByMovieTimeId, session id:" + session.getId());
		
		SeatInfos = movieService.getSeatInfoByMovieTimeId(movieTimeId);
		
		HttpResult<List<SeatInfo>> result = null;
		
		result = new HttpResult<List<SeatInfo> >(1, "", SeatInfos);
		
		return result;
	}
	
}
