package easybuy.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easybuy.server.comm.Util;
import easybuy.server.dao.MovieDao;
import easybuy.server.model.Movie;
import easybuy.server.model.PopularMovie;

@Service
public class MovieService {
	
	@Autowired
	private MovieDao  movieDao;
	
	public List<PopularMovie> getPopular() {
		return movieDao.getPopular();
	}
	
	public List<Movie> searchMovie(String keyword) {
		if (Util.isBlank(keyword)) {
			return null;
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
}
