package easybuy.server.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import easybuy.server.comm.Util;
import easybuy.server.model.User;
import easybuy.server.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "logInByUsername", method = {RequestMethod.GET, RequestMethod.POST})
	public String logInByUsername(String username, String password, HttpSession session) {
		User user = null;
		String message = null;
		
		session.removeAttribute("user");
		
		if (Util.isBlank(username) || Util.isBlank(password)) {
			message = "用户名或密码为空";
		}
		
		if (message == null) {
			if (userService.getUserByUsername(username) == null) {
				message = "用户不存在";
			}
		}
		
		if (message == null) {
			user = userService.logInByUsername(username, password);
			if (user != null) {
				session.setAttribute("user", user);
			} else {
				message = "密码错误";
			}
		}
		
		return message;
	}
	
	@RequestMapping(value = "logInByPhoneNumber", method = {RequestMethod.GET, RequestMethod.POST})
	public String logInByPhoneNumber(String phone_number, String password, HttpSession session) {
		User user = null;
		String message = null;
		
		session.removeAttribute("user");
		
		if (Util.isBlank(phone_number) || Util.isBlank(password)) {
			message = "手机号或密码为空";
		}
		
		if (message == null) {
			if (userService.getUserByPhoneNumber(phone_number) == null) {
				message = "用户不存在";
			}
		}
		
		if (message == null) {
			user = userService.logInByPhoneNumber(phone_number, password);
			if (user != null) {
				session.setAttribute("user", user);
			} else {
				message = "密码错误";
			}
		}
		
		return message;
	}
}
