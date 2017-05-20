package easybuy.server.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import easybuy.server.model.User;
import easybuy.server.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
public class UserDaoTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void logInByUsernameTest() {
		String username = "user";
		String password = "123456";
		
		User user = userService.logInByUsername(username, password);
		System.out.println("\nlog in test:");
		System.out.println("log in by username:" + username);
		if (user == null) {
			System.out.println("User does not exist or incorrect password\n");
		} else {
			System.out.println("Id:" + user.getId());
			System.out.println("Username:" + user.getUsername());
			System.out.println("Phone Number:" + user.getPhone_number() + "\n");
		}
	}
	
	@Test
	public void logInByPhoneNumberTest() {
		String phone_number = "11111111111";
		String password = "123456";
		
		User user = userService.logInByPhoneNumber(phone_number, password);
		System.out.println("\nlog in test:");
		System.out.println("log in by phone number:" + phone_number);
		if (user == null) {
			System.out.println("User does not exist or incorrect password\n");
		} else {
			System.out.println("Id:" + user.getId());
			System.out.println("Username:" + user.getUsername());
			System.out.println("Phone Number:" + user.getPhone_number() + "\n");
		}
	}
	
	@Test
	public void addUserTest() {
		String username = "user3";
		String password = "456789";
		String phone_number = "33333333333";
		
		String message = userService.addUser(username, password, phone_number);
		if (message == null) {
			User user = userService.logInByUsername(username, password);
			if (user != null) {
				message = "success";
			} else {
				message = "fail";
			}
		}
		System.out.println("\nadd user test:" + message + "\n");
	}
	
	@Test
	public void changePasswordTest() {
		String username = "user";
		String oldPassword = "123456";
		String newPassword = "654321";
		
		String message = userService.changePassword(username, oldPassword, newPassword);
		if (message == null) {
			User user = userService.logInByUsername(username, newPassword);
			if (user != null) {
				message = "success";
			} else {
				message = "fail";
			}
		}
		System.out.println("\nchange password test:" + message + "\n");
		userService.changePassword(username, newPassword, oldPassword);
	}
	
	@Test
	public void changeUsernameTest() {
		String phone_number = "11111111111";
		String oldUsername = "user";
		String newUsername = "new";
		
		String message = userService.changeUsername(phone_number, newUsername);
		if (message == null) {
			User user = userService.getUserByUsername(newUsername);
			if (user != null) {
				message = "success";
			} else {
				message = "fail";
			}
		}
		System.out.println("\nchange username test:" + message + "\n");
		userService.changeUsername(phone_number, oldUsername);
	}
	
	@Test
	public void changePhoneNumberTest() {
		String oldPhoneNumber = "11111111111";
		String newPhoneNumber = "66666666666";
		String username = "user";
		
		String message = userService.changePhoneNumber(username, newPhoneNumber);
		if (message == null) {
			User user = userService.getUserByPhoneNumber(newPhoneNumber);
			if (user != null) {
				message = "success";
			} else {
				message = "fail";
			}
		}
		System.out.println("\nchange phone number test:" + message + "\n");
		userService.changePhoneNumber(username, oldPhoneNumber);
	}
}
