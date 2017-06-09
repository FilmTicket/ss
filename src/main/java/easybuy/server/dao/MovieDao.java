package easybuy.server.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import easybuy.server.model.MovieTime;
import easybuy.server.model.SeatInfo;

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
	
	public String addMovieTime(String date, String startTime, String endTime, Integer movieId, String movieType, 
			                   Integer theaterId, String price, String hallName) {		
        
		String message = null;
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			MovieTime movietime = new MovieTime(date, startTime, endTime, movieId, movieType, theaterId, price, hallName);
			sess.save(movietime);
			
			tx.commit();
		} catch (Exception e) {
			message = "数据库访问错误";
			logger.error("MovieDao::addMovieTime函数出错:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		
		return message;
	}
	
	public List<MovieTime> getMovieTime (String theaterId, String movieId, String date) {
        List<MovieTime> movietimes = null;
		
		Session sess = null;
		Transaction tx = null;
		
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "from MovieTime where theaterId = ? and movieId = ? and date like :name";
			Query<MovieTime> query = sess.createQuery(hql, MovieTime.class);
			movietimes = query.setParameter(0, Integer.parseInt(theaterId)).setParameter(1, Integer.parseInt(movieId)).setParameter("name", "%"+date+"%").setCacheable(true).getResultList();
			
			tx.commit();
		} catch (Exception e) {
			logger.error("MovieDao::searchMovie函数出错:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		
		return movietimes;
	}
	
	public Movie searchMovieByid(Integer movieid) {
        Movie movie = null;
		
		Session sess = null;
		Transaction tx = null;
		
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "from Movie where movieId = ?";
			Query<Movie> query = sess.createQuery(hql, Movie.class);
			movie = query.setParameter(0, movieid).setCacheable(true).getResultList().get(0);
			
			tx.commit();
		} catch (Exception e) {
			logger.error("MovieDao::searchMovieByid函数出错:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		
		return movie;
	}
	public List<Integer> getMovieidsByTheaterId(String theaterId) {
        
		List<Integer> movieids = new ArrayList<Integer>();
		List<MovieTime> movietimes = null;
		Session sess = null;
		Transaction tx = null;
		
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "from MovieTime where theaterId = ?";
			Query<MovieTime> query = sess.createQuery(hql, MovieTime.class);
			movietimes = query.setParameter(0, Integer.parseInt(theaterId)).setCacheable(true).getResultList();
			
			if (movietimes == null || movietimes.isEmpty()) {
				
			} else {
				for (int i = 0; i < movietimes.size(); i++) {
					movieids.add(movietimes.get(i).getMovieId());
				}
				
				//去重复操作
				Set set = new HashSet(movieids);
				movieids.clear();
				movieids.addAll(set);
			}
			tx.commit();
		} catch (Exception e) {
			logger.error("MovieDao::getMovieidsByTheaterId函数出错:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		
		return movieids;
	}
	
	public List<Movie> getMoviesByTheaterId(String theaterId) {
		List<Movie> movies = new ArrayList<Movie>();
		List<Integer> movieids = getMovieidsByTheaterId(theaterId);
		
		for (int i = 0; i < movieids.size(); ++i) {
			Movie temp = searchMovieByid(movieids.get(i));
			if (temp != null) 
			movies.add(temp);
		}
		return movies;
	}
	
	public List<SeatInfo> getSeatInfoByMovieTimeId(String movieTimeId) {
        
		List<SeatInfo> seats = null;
		Session sess = null;
		Transaction tx = null;
		
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "from SeatInfo where movieTimeId = ?";
			Query<SeatInfo> query = sess.createQuery(hql, SeatInfo.class);
			seats = query.setParameter(0, Integer.parseInt(movieTimeId)).setCacheable(true).getResultList();
			
			tx.commit();
		} catch (Exception e) {
			logger.error("MovieDao::getSeatInfoByMovieTimeId函数出错:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		
		return seats;
	}
	
}
