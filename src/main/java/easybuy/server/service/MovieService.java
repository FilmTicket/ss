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
	private MovieDao  moviedao;
	
	public List<PopularMovie> getPoppular() {
		return moviedao.getPoppular();
	}
	
	public List<Movie> searchMovie(String keyword) {
		if (Util.isBlank(keyword)) {
			return null;
		}
		return moviedao.searchMovie(keyword);
	}
}
