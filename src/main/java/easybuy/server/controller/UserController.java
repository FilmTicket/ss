package easybuy.server.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import easybuy.server.comm.Util;
import easybuy.server.model.HttpResult;
import easybuy.server.model.Ticket;
import easybuy.server.model.User;
import easybuy.server.model.UserInfo;
import easybuy.server.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public Object logIn(String userName, String password, HttpSession session) {
		User user = null;
		String message = null;
		
		logger.info("Request to log in, session id:" + session.getId());
		
		session.removeAttribute("user");

		if (Util.isBlank(userName) || Util.isBlank(password)) {
			message = "用户名或密码为空";
		}
		
		if (message == null) {
			if (userService.getUserByUserName(userName) == null) {
				message = "用户不存在";
			}
		}
		
		if (message == null) {
			user = userService.logIn(userName, password);
			
			if (user == null) {
				message = "密码错误";
			} else {
				session.setAttribute("user", user.toUserInfo());
			}
		}
		
		HttpResult<UserInfo> result = null;
		
		if (message == null) {
			result = new HttpResult<UserInfo>(1, "", user.toUserInfo());
		} else {
			result = new HttpResult<UserInfo>(0, message, null);
		}

		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public Object register(String userName, String password, String description, String avatar, HttpSession session) {
		String message = null;
		
		logger.info("Request to register, session id:" + session.getId());
		
		message = userService.register(userName, password, description, avatar);
		
		HttpResult<String> result = null;
		
		if (message == null) {
			result = new HttpResult<String>(1, "", "");
		} else {
			result = new HttpResult<String>(0, message, "");
		}

		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public Object logOut(HttpSession session) {
		logger.info("Request to log out, session id:" + session.getId());
		
		session.removeAttribute("user");
		
		HttpResult<String> result = new HttpResult<String>(1, "", "");
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	public Object changePassword(String userName, String oldPassword, String newPassword, HttpSession session) {
		String message = null;
		
		logger.info("Request to change passowrd, session id:" + session.getId());
		
		message = userService.changePassword(userName, oldPassword, newPassword);
		
		HttpResult<String> result = null;
		
		if (message == null) {
			result = new HttpResult<String>(1, "", "");
		} else {
			result = new HttpResult<String>(0, message, "");
		}

		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "getTicketByUserId", method = RequestMethod.POST)
	public Object getTicketByUserId(Integer userId, HttpSession session) {
		List<Ticket> tickets = null;
		String message = null;
		
		logger.info("Request to get ticket by userId, session id:" + session.getId());
		
		if (userId == null) {
			message = "用户Id为空";
		}
		
		if (message == null) {
			if (userService.getUserByUserId(userId) == null) {
				message = "用户不存在";
			}
		}
		
		if (message == null) {
			tickets = userService.getTicketByUserId(userId);
		}
		
		HttpResult<List<Ticket>> result = null;
		
		if (message == null) {
			result = new HttpResult<List<Ticket>>(1, "", tickets);
		} else {
			result = new HttpResult<List<Ticket>>(0, message, null);
		}
		
		return result;
	}
}
