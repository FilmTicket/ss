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
			message = "用户名或密码或手机号为空";
		}
		
		if (message == null) {
			if (getUserByUsername(username) != null) {
				message = "用户名已被注册";
			} else if (getUserByPhoneNumber(phone_number) != null) {
				message = "手机号已被注册";
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
			message = "用户名或密码为空";
		}
		
		if (message == null) {
			if (getUserByUsername(username) == null) {
				message = "用户不存在";
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
			message = "用户名或手机号为空";
		}
		
		if (message == null) {
			if (getUserByPhoneNumber(phone_number) == null) {
				message = "用户不存在";
			} else if (getUserByUsername(newUsername) != null) {
				message = "用户名已被注册";
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
			message = "用户名或手机号为空";
		}
		
		if (message == null) {
			if (getUserByUsername(username) == null) {
				message = "用户不存在";
			} else if (getUserByPhoneNumber(newPhoneNumber) != null) {
				message = "手机号已被注册";
			}
		}
		
		if (message == null) {
			return userDao.changePhoneNumber(username, newPhoneNumber);
		}
		
		return message;
	}
}
