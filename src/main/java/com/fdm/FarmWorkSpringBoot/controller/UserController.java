package com.fdm.FarmWorkSpringBoot.controller;

import javax.servlet.http.HttpSession;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fdm.FarmWorkSpringBoot.model.User;
import com.fdm.FarmWorkSpringBoot.service.UserService;

/**
 * @author Robert.Dooley
 * @version 1.0
 */


@Controller
public class UserController {

	static final String HOME_URL = "home";
	static final String BIND_USER_ATTRIBUTE_NAME = "bindUser";
	static final String REGISTER_USER_URL = "registerUser";
	static final String LOGIN_URL = "login";
	static final String LOGOUT_URL = "logout";
	static final String UPDATE_USER_URL = "updateUser";
	static final String REDIRECT_USER_UPDATED_URL = "redirect:/userUpdated";
	static final String REMOVE_USER_URL = "removeUser";
	static final String REDIRECT_ERROR_CAN_NOT_REMOVE_CURRENT_USER_URL = "redirect:/errorCanNotRemoveCurrentUser";
	static final String REDIRECT_USER_DELETED_URL = "redirect:/userDeleted";
	static final String REDIRECT_ERROR_NO_SUCH_USER_URL = "redirect:/errorNoSuchUser";
	static final String ACTIVE_USER_ATTRIBUTE = "activeUser";
	static final String CHECK_USER_URL = "checkUser";
	static final String REDIRECT_ERROR_NO_USER_LOGGED_IN_URL = "redirect:/errorNoUserLoggedIn";
	static final String REDIRECT_ERROR_WRONG_LOGIN_URL = "redirect:/errorWrongLogin";
	private Log log = LogFactory.getLog(UserController.class);
	private UserService userService;
	
	public UserController() {
		super();
	}

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	/**
	 * Get mapping for registering a user
	 * 
	 * @return 	relevant .jsp as String
	 * @since 	1.0
	 */
	@GetMapping("/registerUser")
	public String getRegisterUserPage(Model model) {
		User userObj = new User();
		model.addAttribute(BIND_USER_ATTRIBUTE_NAME, userObj);
		return REGISTER_USER_URL;
	}
	/**
	 * Get mapping for checking a users details
	 * 
	 * @return 	relevant .jsp as String
	 * @since 	1.0
	 */
	@GetMapping("/checkUser")
	public String getCheckUserPage(HttpSession session) {
		if (session.getAttribute(ACTIVE_USER_ATTRIBUTE) != null) {
			log.info("session: " + session.getAttribute(ACTIVE_USER_ATTRIBUTE));
			return CHECK_USER_URL;
		}
		else {
			return REDIRECT_ERROR_NO_USER_LOGGED_IN_URL;
		}
	}
	/**
	 * Processes the form for registering a user
	 * Sets relevant model and session attributes
	 * 
	 * @return 	relevant .jsp as String
	 * @since 	1.0
	 */
	@PostMapping("/processUser")
	public String processRegisterUserPage(User returnedUser, HttpSession session) {
		if (returnedUser != null) {
			userService.saveUser(returnedUser);
			session.setAttribute(ACTIVE_USER_ATTRIBUTE, returnedUser);
			updateUserDetails(session);

			return CHECK_USER_URL;
		} else {
			return REDIRECT_ERROR_NO_USER_LOGGED_IN_URL;
		}

	}

	private void updateUserDetails(HttpSession session) {
		User user = (User) session.getAttribute(ACTIVE_USER_ATTRIBUTE);
		if (user != null) {
			session.setAttribute("username", user.getUsername());
			session.setAttribute("fName", user.getfName());
			session.setAttribute("lName", user.getlName());
			session.setAttribute("password", user.getPassword());
			session.setAttribute("email", user.getEmail());
		}
	}

	@GetMapping("/login")
	public String loginUserPage() {
		return LOGIN_URL;
	}
	/**
	 * Processes the form for logging in a user
	 * Sets relevant model and session attributes
	 * 
	 * @return 	relevant .jsp as String
	 * @since 	1.0
	 */
	@PostMapping("/loginUser")
	public String processLoginUserPage(@RequestParam String userUsername, @RequestParam String userPassword,
			HttpSession session) {
		String username = userUsername;
		String password = userPassword;
		User user = userService.findUserByUsername(username);
		log.info("User: " + user);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				session.setAttribute(ACTIVE_USER_ATTRIBUTE, user);
				updateUserDetails(session);
				
				return CHECK_USER_URL;
			} else {
				return REDIRECT_ERROR_WRONG_LOGIN_URL;
			}
		} else {
			return REDIRECT_ERROR_WRONG_LOGIN_URL;
		}
	}
	/**
	 * Get mapping for logging out a user
	 * 
	 * @return 	relevant .jsp as String
	 * @since 	1.0
	 */
	@GetMapping("/logout")
	public String logoutUserPage(Model model, HttpSession session) {
		if (session.getAttribute(ACTIVE_USER_ATTRIBUTE) == null) {
			return REDIRECT_ERROR_NO_USER_LOGGED_IN_URL;
		}
		session.invalidate();
		return HOME_URL;
	}
	/**
	 * Get mapping for updating a user
	 * 
	 * @return 	relevant .jsp as String
	 * @since 	1.0
	 */
	@GetMapping("/updateUser")
	public String updateUserPage(HttpSession session) {
		if (session.getAttribute(ACTIVE_USER_ATTRIBUTE) == null) {
			return REDIRECT_ERROR_NO_USER_LOGGED_IN_URL;
		}
		return UPDATE_USER_URL;
	}
	/**
	 * Processes the form for updating a user
	 * Sets relevant model and session attributes
	 * 
	 * @return 	relevant .jsp as String
	 * @since 	1.0
	 */
	@PostMapping("/updateUser")
	public String processUpdateUserPage(@RequestParam String newUserUsername, @RequestParam String newUserPassword,
			HttpSession session) {
		String username = newUserUsername;
		String password = newUserPassword;
		User user = (User) session.getAttribute(ACTIVE_USER_ATTRIBUTE);
		user.setUsername(username);
		user.setPassword(password);
		userService.saveUser(user);
		user = userService.findUserByUsername(newUserUsername);
		session.setAttribute(ACTIVE_USER_ATTRIBUTE, user);
		updateUserDetails(session);
		return REDIRECT_USER_UPDATED_URL;
	}
	/**
	 * Get mapping for removing a user
	 * 
	 * @return 	relevant .jsp as String
	 * @since 	1.0
	 */
	@GetMapping("/removeUser")
	public String deleteUserPage(HttpSession session) {
		if (session.getAttribute(ACTIVE_USER_ATTRIBUTE) != null)
			return REMOVE_USER_URL;
		else
			return REDIRECT_ERROR_NO_USER_LOGGED_IN_URL;
	}
	/**
	 * Processes the form for removing a user
	 * Sets relevant model and session attributes
	 * 
	 * @return 	relevant .jsp as String
	 * @since 	1.0
	 */
	@PostMapping("/removeUser")
	public String processRemoveUserPage(@RequestParam String username, HttpSession session) {
		User user = (User) session.getAttribute(ACTIVE_USER_ATTRIBUTE);
		if (userService.findUserByUsername(username) != null) {
			if (username.equals(user.getUsername()))
				return REDIRECT_ERROR_CAN_NOT_REMOVE_CURRENT_USER_URL;
			userService.deleteUser(username);
			return REDIRECT_USER_DELETED_URL;
		} else
			return REDIRECT_ERROR_NO_SUCH_USER_URL;
	}

}