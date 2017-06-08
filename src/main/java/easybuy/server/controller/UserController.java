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
			message = "鐢ㄦ埛鍚嶆垨瀵嗙爜涓虹┖";
		}
		
		if (message == null) {
			if (userService.getUser(userName) == null) {
				message = "鐢ㄦ埛涓嶅瓨鍦�";
			}
		}
		
		if (message == null) {
			user = userService.logIn(userName, password);
			
			if (user == null) {
				message = "瀵嗙爜閿欒";
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
	public Object getTicketByUserId(Integer userId) {
		String message = null;
		List<Ticket> tickets = null;
		tickets = userService.getTicketByUserId(userId);
		
		HttpResult<List<Ticket>> result = null;
		
		if (tickets.size() == 0) {
			message = "该用户电影票为空";
		}
		
		if (message == null) {
			result = new HttpResult<List<Ticket>>(1, message, tickets);
		} else {
			result = new HttpResult<List<Ticket>>(1, message, null);
		}
		return result;
	}
}
