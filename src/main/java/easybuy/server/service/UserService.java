package easybuy.server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easybuy.server.comm.Util;
import easybuy.server.dao.UserDao;
import easybuy.server.model.Ticket;
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
			message = "用户名或密码为空";
		}
		
		if (message == null) {
			if (getUserByUserName(userName) != null) {
				message = "用户名已被注册";
			}
		}
		
		if (message == null) {
			return userDao.register(userName, password, description, avatar);
		}
		
		return message;
	}
	
	public User getUserByUserName(String userName) {
		if (Util.isBlank(userName)) {
			return null;
		}
		
		return userDao.getUserByUserName(userName);
	}
	
	public User getUserByUserId(Integer userId) {
		if (userId == null) {
			return null;
		}
		
		return userDao.getUserByUserId(userId);
	}
	
	public String changePassword(String userName, String oldPassword, String newPassword) {
		String message = null;
		
		if (Util.isBlank(userName) || Util.isBlank(oldPassword) || Util.isBlank(newPassword)) {
			message = "用户名或密码为空";
		}
		
		if (message == null) {
			if (getUserByUserName(userName) == null) {
				message = "用户不存在";
			}
		}
		
		if (message == null) {
			return userDao.changePassword(userName, oldPassword, newPassword);
		}
		
		return message;
	}
	
	public List<Ticket> getTicketByUserId(Integer userId) {
		if (userId == null) {
			return new ArrayList<Ticket>();
		}
		
		return userDao.getTicketByUserId(userId);
	}
}
