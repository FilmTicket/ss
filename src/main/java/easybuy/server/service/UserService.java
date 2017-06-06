package easybuy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easybuy.server.comm.Util;
import easybuy.server.dao.UserDao;
import easybuy.server.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public User logIn(String userName, String password) {
		if (Util.isBlank(userName) || Util.isBlank(password)) {
			return null;
		}
		
		return userDao.logIn(userName, password);
	}
	
	public String register(String userName, String password, String description, String avatar) {
		String message = null;
		
		if (Util.isBlank(userName) || Util.isBlank(password)) {
			message = "�û���������Ϊ��";
		}
		
		if (message == null) {
			if (getUser(userName) != null) {
				message = "�û����ѱ�ע��";
			}
		}
		
		if (message == null) {
			return userDao.register(userName, password, description, avatar);
		}
		
		return message;
	}
	
	public User getUser(String userName) {
		if (Util.isBlank(userName)) {
			return null;
		}
		
		return userDao.getUser(userName);
	}
	
	public String changePassword(String userName, String oldPassword, String newPassword) {
		String message = null;
		
		if (Util.isBlank(userName) || Util.isBlank(oldPassword) || Util.isBlank(newPassword)) {
			message = "�û���������Ϊ��";
		}
		
		if (message == null) {
			if (getUser(userName) == null) {
				message = "�û�������";
			}
		}
		
		if (message == null) {
			return userDao.changePassword(userName, oldPassword, newPassword);
		}
		
		return message;
	}
}
