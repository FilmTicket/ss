package easybuy.server.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import easybuy.server.model.Movie;
import easybuy.server.model.PopularMovie;

@Component
public class MovieDao {
	
	private static final Logger logger = LoggerFactory.getLogger(TheaterDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public String addMovie(String movieName, String movieDes, String postUrl) {
        String message = null;
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			Movie movie = new Movie(movieName, movieDes, postUrl);
			sess.save(movie);
			
			tx.commit();
		} catch (Exception e) {
			message = "Movie insert fail!";
			logger.error("MovieDao::addMovie" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		return message;
	}
	
	public String addPopularMovie(String movieName, String movieDes, String postUrl) {
        String message = null;
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			PopularMovie movie = new PopularMovie(movieName, movieDes, postUrl);
			sess.save(movie);
			
			tx.commit();
		} catch (Exception e) {
			message = "PopularMovie insert fail!";
			logger.error("MovieDao::addMovie" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		return message;
	}
	
	public List<PopularMovie> getPoppular() {
        List<PopularMovie> populars = null;		
		Session sess = null;
		Transaction tx = null;
		
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "from PopularMovie";
			Query<PopularMovie> query = sess.createQuery(hql, PopularMovie.class);
			populars = query.setCacheable(true).getResultList();
			tx.commit();
		} catch (Exception e) {
			logger.error("MovieDao::getPoppular" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		
		return populars;		
	}
	
	public List<Movie> searchMovie(String keyword) {
        List<Movie> movies = null;
		
		Session sess = null;
		Transaction tx = null;
		
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "from Movie where movieName like :name";
			Query<Movie> query = sess.createQuery(hql, Movie.class);
			movies = query.setParameter("name", "%"+keyword+"%").setCacheable(true).getResultList();
			tx.commit();
		} catch (Exception e) {
			logger.error("MovieDao::searchMovie" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		
		return movies;
	}
	
	public void addMovies(List<Movie> movies) {
		for (int i = 0; i < movies.size(); i++) {
			Movie temp = movies.get(i);
			addMovie(temp.getMovieName(), temp.getMovieDes(), temp.getPostUrl());
		}		
	}
	
	public void addPopularMovies(List<PopularMovie> populars) {
		for (int i = 0; i < populars.size(); i++) {
			PopularMovie temp = populars.get(i);
			addMovie(temp.getMovieName(), temp.getMovieDes(), temp.getPostUrl());
		}	
	}
}
