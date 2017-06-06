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
	public void logInByUsername(String username, String password, HttpSession session) {
		
	}
}
