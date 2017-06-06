package easybuy.server.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;

import easybuy.server.comm.Util;
import easybuy.server.model.HttpResult;
import easybuy.server.model.User;
import easybuy.server.model.UserInfo;
import easybuy.server.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public Object logIn(String userName, String password, HttpSession session) {
		User user = null;
		String message = null;
		
		session.removeAttribute("user");
		
		if (Util.isBlank(userName) || Util.isBlank(password)) {
			message = "用户名或密码为空";
		}
		
		if (message == null) {
			if (userService.getUser(userName) == null) {
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
			result = new HttpResult<UserInfo>(1, message, user.toUserInfo());
		} else {
			result = new HttpResult<UserInfo>(0, message, null);
		}
		
		return JSON.toJSON(result);
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public Object register(String userName, String password, String description, String avatar, HttpSession session) {
		String message = null;
		
		message = userService.register(userName, password, description, avatar);
		
		HttpResult<String> result = null;
		
		if (message == null) {
			result = new HttpResult<String>(1, message, null);
		} else {
			result = new HttpResult<String>(0, message, null);
		}
		
		return JSON.toJSON(result);
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public Object logOut(HttpSession session) {
		session.removeAttribute("user");
		
		HttpResult<String> result = new HttpResult<String>(1, "log out success", null);
		
		return JSON.toJSON(result);
	}
	
	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	public Object changePassword(String userName, String oldPassword, String newPassword, HttpSession session) {
		String message = null;
		
		message = userService.changePassword(userName, oldPassword, newPassword);
		
		HttpResult<String> result = null;
		
		if (message == null) {
			result = new HttpResult<String>(1, message, null);
		} else {
			result = new HttpResult<String>(0, message, null);
		}
		
		return JSON.toJSON(result);
	}
}
