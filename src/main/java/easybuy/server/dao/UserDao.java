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

import easybuy.server.model.User;

@Component
public class UserDao {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	// 用户登录
	public User logIn(String userName, String password) {
		List<User> users = null;
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "from User where userName = ? and password = ?";
			Query<User> query = sess.createQuery(hql, User.class);
			users = query.setParameter(0, userName).setParameter(1, password).setCacheable(true).getResultList();
			
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
	
	// 用户查询 - 通过用户名
	public User getUser(String userName) {
		List<User> users = null;
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "from User where userName = ?";
			Query<User> query = sess.createQuery(hql, User.class);
			users = query.setParameter(0, userName).setCacheable(true).getResultList();
			
			tx.commit();
		} catch (Exception e) {
			logger.error("UserDao::getUser函数出错:" + e.getMessage());
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
			
			String hql = "update User set password = ? where userName = ? and password = ?";
			Query<?> query = sess.createQuery(hql);
			query.setParameter(0, newPassword).setParameter(1, userName).setParameter(2, oldPassword).setCacheable(true);
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
}
