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
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(MovieDao.class);
	
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
			message = "数据库访问错误";
			logger.error("MovieDao::addMovie函数出错:" + e.getMessage());
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
			message = "数据库访问错误";
			logger.error("MovieDao::addPopularMovie函数出错:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		
		return message;
	}
	
	public List<PopularMovie> getPopular() {
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
			logger.error("MovieDao::getPopular函数出错:" + e.getMessage());
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
			logger.error("MovieDao::searchMovie函数出错:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		
		return movies;
	}
	
	public String addMovies(List<Movie> movies) {
		String message = null;
		
		for (int i = 0; i < movies.size(); i++) {
			Movie temp = movies.get(i);
			message = addMovie(temp.getMovieName(), temp.getMovieDes(), temp.getPostUrl());
			if (message != null) {
				break;
			}
		}
		
		return message;
	}
	
	public String addPopularMovies(List<PopularMovie> populars) {
		String message = null;
		
		for (int i = 0; i < populars.size(); i++) {
			PopularMovie temp = populars.get(i);
			message = addPopularMovie(temp.getMovieName(), temp.getMovieDes(), temp.getPostUrl());
			if (message != null) {
				break;
			}
		}
		
		return message;
	}
	
	public String deleteAllMovies() {
		String message = null;
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "delete Movie";
			Query<?> query = sess.createQuery(hql);
			int flag = query.executeUpdate();
			
			if (flag == 0) {
				message = "删除电影失败，数据库为空";
			}
			
			tx.commit();
		} catch (Exception e) {
			message = "数据库访问错误";
			logger.error("MovieDao::deleteAllMovie函数出错:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		
		return message;
	}
	
	public String deleteAllPopularMovies() {
		String message = null;
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "delete PopularMovie";
			Query<?> query = sess.createQuery(hql);
			int flag = query.executeUpdate();
			
			if (flag == 0) {
				message = "删除热门电影失败，数据库为空";
			}
			
			tx.commit();
		} catch (Exception e) {
			message = "数据库访问错误";
			logger.error("MovieDao::deleteAllPopularMovie函数出错:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		
		return message;
	}
}
