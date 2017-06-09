package easybuy.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easybuy.server.comm.Util;
import easybuy.server.dao.MovieDao;
import easybuy.server.model.Movie;
import easybuy.server.model.MovieTime;
import easybuy.server.model.PopularMovie;
import easybuy.server.model.SeatInfo;

@Service
public class MovieService {
	
	@Autowired
	private MovieDao  movieDao;
	
	public List<PopularMovie> getPopular() {
		return movieDao.getPopular();
	}
	
	public List<Movie> searchMovie(String keyword) {
		if (Util.isBlank(keyword)) {
			return new ArrayList<Movie>();
		}
		
		return movieDao.searchMovie(keyword);
	}
	
	public String addMovies(List<Movie> movies) {
		String message = null;
		
		if (movies == null || movies.isEmpty()) {
			message = "电影列表为空";
		}
		
		if (message == null) {
			return movieDao.addMovies(movies);
		}
		
		return message;
	}
	
	public String addPopularMovies(List<PopularMovie> populars) {
		String message = null;
		
		if (populars == null || populars.isEmpty()) {
			message = "热门电影列表为空";
		}
		
		if (message == null) {
			return movieDao.addPopularMovies(populars);
		}
		
		return message;
	}
	
	public String deleteAllMovies() {
		return movieDao.deleteAllMovies();
	}
	
	public String deleteAllPopularMovies() {
		return movieDao.deleteAllPopularMovies();
	}
	
	public List<MovieTime> getMovieTime(String theaterId, String movieId, String date) {
		if (Util.isBlank(theaterId) || Util.isBlank(movieId) || Util.isBlank(date)) {
			return new ArrayList<MovieTime>();
		}
		
		return movieDao.getMovieTime(theaterId, movieId, date);
	}
	
	public List<Movie> getMoviesByTheaterId(String theaterId) {
		if (Util.isBlank(theaterId)) {
			return new ArrayList<Movie>();
		}
		
		return movieDao.getMoviesByTheaterId(theaterId);
	}
	
	public List<SeatInfo> getSeatInfoByMovieTimeId(String movieTimeId) {
		if (Util.isBlank(movieTimeId)) {
			return new ArrayList<SeatInfo>();
		}
		
		return movieDao.getSeatInfoByMovieTimeId(movieTimeId);
	}
	
	public Movie searchMovieByid(Integer movieid) {
		if (movieid == null) {
			return null;
		}
		
		return movieDao.searchMovieByid(movieid);
	}
	
	public MovieTime getMovieTimeById(Integer movieTimeId) {
		if (movieTimeId == null) {
			return null;
		}
		
		return movieDao.getMovieTimeById(movieTimeId);
	}
	
	public String createMovieTimeTable(List<String[]> lists) {
		return movieDao.createMovieTimeTable(lists);
	}
}
