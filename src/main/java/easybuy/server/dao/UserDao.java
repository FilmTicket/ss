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
	
	// �û���¼
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
			logger.error("UserDao::logIn��������:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		return (users == null || users.isEmpty()) ? null : users.get(0);
	}
	
	// �û�ע��
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
			message = "�û����ѱ�ע��";
			logger.error("UserDao::register��������:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		return message;
	}
	
	// �û���ѯ - ͨ���û���
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
			logger.error("UserDao::getUser��������:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		return (users == null || users.isEmpty()) ? null : users.get(0);
	}
	
	// �޸�����
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
				message = "ԭ�������";
			}
			
			tx.commit();
		} catch (Exception e) {
			message = "���ݿ���ʴ���";
			logger.error("UserDao::changePassword��������:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		return message;
	}
}
