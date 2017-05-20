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
	
	// �û���¼ - ͨ���û���
	public User logInByUsername(String username, String password) {
		List<User> users = null;
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "from User where username = ? and password = ?";
			Query<User> query = sess.createQuery(hql, User.class);
			users = query.setParameter(0, username).setParameter(1, password).setCacheable(true).getResultList();
			
			tx.commit();
		} catch (Exception e) {
			logger.error("UserDao::logInByUsername��������:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		return (users == null || users.isEmpty()) ? null : users.get(0);
	}
	
	// �û���¼ - ͨ���ֻ���
	public User logInByPhoneNumber(String phone_number, String password) {
		List<User> users = null;
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "from User where phone_number = ? and password = ?";
			Query<User> query = sess.createQuery(hql, User.class);
			users = query.setParameter(0, phone_number).setParameter(1, password).setCacheable(true).getResultList();
			
			tx.commit();
		} catch (Exception e) {
			logger.error("UserDao::logInByPhoneNumber��������:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		return (users == null || users.isEmpty()) ? null : users.get(0);
	}
	
	// �û�ע��
	public String addUser(String username, String password, String phone_number) {
		String message = null;	
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			User user = new User(username, password, phone_number);
			sess.save(user);
			
			tx.commit();
		} catch (Exception e) {
			message = "�û�ע��ʧ�ܣ��û������ֻ����ѱ�ע��";
			logger.error("UserDao::addUser��������:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		return message;
	}
	
	// �û���ѯ - ͨ���û���
	public User getUserByUsername(String username) {
		List<User> users = null;
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "from User where username = ?";
			Query<User> query = sess.createQuery(hql, User.class);
			users = query.setParameter(0, username).setCacheable(true).getResultList();
			
			tx.commit();
		} catch (Exception e) {
			logger.error("UserDao::getUserByUsername��������:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		return (users == null || users.isEmpty()) ? null : users.get(0);
	}
	
	// �û���ѯ - ͨ���ֻ���
	public User getUserByPhoneNumber(String phone_number) {
		List<User> users = null;
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "from User where phone_number = ?";
			Query<User> query = sess.createQuery(hql, User.class);
			users = query.setParameter(0, phone_number).setCacheable(true).getResultList();
			
			tx.commit();
		} catch (Exception e) {
			logger.error("UserDao::getUserByPhoneNumber��������:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		return (users == null || users.isEmpty()) ? null : users.get(0);
	}
	
	// �޸�����
	public String changePassword(String username, String oldPassword, String newPassword) {
		String message = null;
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "update User set password = ? where username = ? and password = ?";
			Query<?> query = sess.createQuery(hql);
			query.setParameter(0, newPassword).setParameter(1, username).setParameter(2, oldPassword).setCacheable(true);
			int flag = query.executeUpdate();
			
			if (flag == 0){
				message = "�޸�����ʧ�ܣ�ԭ�������";
			}
			
			tx.commit();
		} catch (Exception e) {
			message = "�޸�����ʧ�ܣ����ݿ���ʴ���";
			logger.error("UserDao::changePassword��������:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		return message;
	}
	
	// �޸��û���
	public String changeUsername(String phone_number, String newUsername) {
		String message = null;
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "update User set username = ? where phone_number = ?";
			Query<?> query = sess.createQuery(hql);
			query.setParameter(0, newUsername).setParameter(1, phone_number).setCacheable(true);
			int flag = query.executeUpdate();
			
			if (flag == 0){
				message = "�޸��û���ʧ�ܣ��û�������";
			}
			
			tx.commit();
		} catch (Exception e) {
			message = "�޸��û���ʧ�ܣ��û����ѱ�ע��";
			logger.error("UserDao::changeUsername��������:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		return message;
	}
	
	// �޸��ֻ���
	public String changePhoneNumber(String username, String newPhoneNumber) {
		String message = null;
		
		Session sess = null;
		Transaction tx = null;
		try {
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			
			String hql = "update User set phone_number = ? where username = ?";
			Query<?> query = sess.createQuery(hql);
			query.setParameter(0, newPhoneNumber).setParameter(1, username).setCacheable(true);
			int flag = query.executeUpdate();
			
			if (flag == 0){
				message = "�޸��ֻ���ʧ�ܣ��û�������";
			}
			
			tx.commit();
		} catch (Exception e) {
			message = "�޸��ֻ���ʧ�ܣ��ֻ����ѱ�ע��";
			logger.error("UserDao::changePhoneNumber��������:" + e.getMessage());
		} finally {
			if (sess != null) {
				sess.close();
			}
		}
		return message;
	}
}
