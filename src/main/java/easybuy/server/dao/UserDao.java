package easybuy.server.dao;

import java.util.ArrayList;
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
import easybuy.server.model.MovieTime;
import easybuy.server.model.SeatInfo;
import easybuy.server.model.Theater;
import easybuy.server.model.Ticket;
import easybuy.server.model.User;
import easybuy.server.service.MovieService;
import easybuy.server.service.TheaterService;

@Component
public class UserDao {
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private TheaterService theaterService;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
	
	// 用户登录
	public User logIn(String userName, String password) {
		List<User> users = null;
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "from User where userName = :userName and password = :password";
			Query<User> query = sess.createQuery(hql, User.class);
			users = query.setParameter("userName", userName).setParameter("password", password).setCacheable(true).getResultList();
			
			tx.commit();
		} catch (Exception e) {
			logger.error("UserDao::logIn函数出错:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		
		return (users == null || users.isEmpty()) ? null : users.get(0);
	}
	
	// 用户注册
	public String register(String userName, String password, String description, String avatar) {
		String message = null;	
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			User user = new User(userName, password, description, avatar);
			sess.save(user);
			
			tx.commit();
		} catch (Exception e) {
			message = "用户名已被注册";
			logger.error("UserDao::register函数出错:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		
		return message;
	}
	
	// 查询用户 - 通过用户名
	public User getUserByUserName(String userName) {
		List<User> users = null;
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "from User where userName = :userName";
			Query<User> query = sess.createQuery(hql, User.class);
			users = query.setParameter("userName", userName).setCacheable(true).getResultList();
			
			tx.commit();
		} catch (Exception e) {
			logger.error("UserDao::getUserByUserName函数出错:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		
		return (users == null || users.isEmpty()) ? null : users.get(0);
	}
	
	// 查询用户 - 通过用户Id
	public User getUserByUserId(Integer userId) {
		List<User> users = null;
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "from User where userId = :userId";
			Query<User> query = sess.createQuery(hql, User.class);
			users = query.setParameter("userId", userId).setCacheable(true).getResultList();
			
			tx.commit();
		} catch (Exception e) {
			logger.error("UserDao::getUserByUserId函数出错:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		
		return (users == null || users.isEmpty()) ? null : users.get(0);
	}
	
	// 修改密码
	public String changePassword(String userName, String oldPassword, String newPassword) {
		String message = null;
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "update User set password = :newPassword where userName = :userName and password = :oldPassword";
			Query<?> query = sess.createQuery(hql);
			query.setParameter("newPassword", newPassword).setParameter("userName", userName).setParameter("oldPassword", oldPassword).setCacheable(true);
			int flag = query.executeUpdate();
			if (flag == 0){
				message = "原密码错误";
			}
			
			tx.commit();
		} catch (Exception e) {
			message = "数据库访问错误";
			logger.error("UserDao::changePassword函数出错:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		
		return message;
	}
	
	// 根据用户Id获取电影票
	public List<Ticket> getTicketByUserId(Integer userId) {
		List<Ticket> result = null;
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "from Ticket where userId = :userId";
			Query<Ticket> query = sess.createQuery(hql, Ticket.class);
			result = query.setParameter("userId", userId).setCacheable(true).getResultList();
			
			tx.commit();
		} catch (Exception e) {
			logger.error("UserDao::getTicketByUserId函数出错:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		
		return result;
	}
	
	public String createOrder(String userId, String movieTimeId, String seats) {
		String message = null;
		
		MovieTime movieTime = null;
		Movie movie = null;
		Theater theater = null;
		Ticket ticket = null;
		
		movieTime = movieService.getMovieTimeById(Integer.parseInt(movieTimeId));
		
		if (movieTime == null) {
			message = "MovieTime不存在";
		}
		
		if (message == null) {
			movie = movieService.searchMovieByid(movieTime.getMovieId());
			theater = theaterService.searchTheaterById(movieTime.getTheaterId());
			
			if (movie == null) {
				message = "电影不存在";
			}
			if (theater == null) {
				message = "影院不存在";
			}
		}
		
		String[] seatTemps = seats.split("\\u007C");
		List<SeatInfo> seatInfos = new ArrayList<SeatInfo>();
		
	
		if (message == null) {
			try {
//				System.out.println("hhh: " + seatTemps.length);
//				System.out.println("hhh: " + seatTemps[1]);
				for (String seatTemp : seatTemps) {
					String num = seatTemp.substring(1, 2);
					Integer row;
					Integer column;
					if (!(num.equals("排"))) {
						row = Integer.parseInt(seatTemp.substring(0, 2));
						column = Integer.parseInt(seatTemp.substring(3, seatTemp.length()-1));
					} else {
						row = Integer.parseInt(seatTemp.substring(0, 1));
						column = Integer.parseInt(seatTemp.substring(2, seatTemp.length()-1));
					}
					
					System.out.println("row: " + row + " column: " + column);
					
					Integer position = (row - 1) * 10 + column;
					Integer status = 2;
					
					SeatInfo seatInfo = new SeatInfo(position, row, column, status, Integer.parseInt(movieTimeId));
					seatInfos.add(seatInfo);
				}
			} catch (Exception e) {
				message = "座位信息不合法";
			}
		}
		
		if (message == null) {
			ticket = new Ticket(Integer.parseInt(userId), movie.getMovieName(), theater.getTheaterName(),
					movieTime.getDate() + " " + movieTime.getStartTime(), movieTime.getHallName(), seats);
			
			Session sess = null;
			Transaction tx = null;
			try {
				sess = sessionFactory.openSession();
				tx = sess.beginTransaction();
				
				for (SeatInfo seatInfo : seatInfos) {
					sess.save(seatInfo);
				}
				
				sess.save(ticket);
				
				tx.commit();
			} catch (Exception e) {
				message = "创建订单失败";
				logger.error("UserDao::createOrder函数出错:" + e.getMessage());
				
				if (tx != null) {
					tx.rollback();
				}
			} finally {
				if (sess != null) {
					sess.close();				
				}
			}
		}
		
		return message;
	}
}
