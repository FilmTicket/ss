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
	
	public User logInByUsername(String username, String password) {
		if (Util.isBlank(username) || Util.isBlank(password)) {
			return null;
		}
		
		return userDao.logInByUsername(username, password);
	}
	
	public User logInByPhoneNumber(String phone_number, String password) {
		if (Util.isBlank(phone_number) || Util.isBlank(password)) {
			return null;
		}
		
		return userDao.logInByPhoneNumber(phone_number, password);
	}
	
	public String addUser(String username, String password, String phone_number) {
		String message = null;
		
		if (Util.isBlank(username) || Util.isBlank(password) || Util.isBlank(phone_number)) {
			message = "�û�����������ֻ���Ϊ��";
		}
		
		if (message == null) {
			if (getUserByUsername(username) != null) {
				message = "�û����ѱ�ע��";
			} else if (getUserByPhoneNumber(phone_number) != null) {
				message = "�ֻ����ѱ�ע��";
			}
		}
		
		if (message == null) {
			return userDao.addUser(username, password, phone_number);
		}
		
		return message;
	}
	
	public User getUserByUsername(String username) {
		if (Util.isBlank(username)) {
			return null;
		}
		
		return userDao.getUserByUsername(username);
	}
	
	public User getUserByPhoneNumber(String phone_number) {
		if (Util.isBlank(phone_number)) {
			return null;
		}
		
		return userDao.getUserByPhoneNumber(phone_number);
	}
	
	public String changePassword(String username, String oldPassword, String newPassword) {
		String message = null;
		
		if (Util.isBlank(username) || Util.isBlank(oldPassword) || Util.isBlank(newPassword)) {
			message = "�û���������Ϊ��";
		}
		
		if (message == null) {
			if (getUserByUsername(username) == null) {
				message = "�û�������";
			}
		}
		
		if (message == null) {
			return userDao.changePassword(username, oldPassword, newPassword);
		}
		
		return message;
	}
	
	public String changeUsername(String phone_number, String newUsername) {
		String message = null;
		
		if (Util.isBlank(phone_number) || Util.isBlank(newUsername)) {
			message = "�û������ֻ���Ϊ��";
		}
		
		if (message == null) {
			if (getUserByPhoneNumber(phone_number) == null) {
				message = "�û�������";
			} else if (getUserByUsername(newUsername) != null) {
				message = "�û����ѱ�ע��";
			}
		}
		
		if (message == null) {
			return userDao.changeUsername(phone_number, newUsername);
		}
		
		return message;
	}
	
	public String changePhoneNumber(String username, String newPhoneNumber) {
		String message = null;
		
		if (Util.isBlank(username) || Util.isBlank(newPhoneNumber)) {
			message = "�û������ֻ���Ϊ��";
		}
		
		if (message == null) {
			if (getUserByUsername(username) == null) {
				message = "�û�������";
			} else if (getUserByPhoneNumber(newPhoneNumber) != null) {
				message = "�ֻ����ѱ�ע��";
			}
		}
		
		if (message == null) {
			return userDao.changePhoneNumber(username, newPhoneNumber);
		}
		
		return message;
	}
}
