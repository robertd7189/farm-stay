package com.fdm.FarmWorkSpringBoot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * @author Robert.Dooley
 * @version 1.0
 */
@Controller
public class HomeController {
	
	static final String HOME_URL = "home";
	static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
	static final String UPDATED_USER_MESSAGE_ATTRIBUTE = "updatedUserMessage";

	
	/**
	 * Gets mapping for the home page
	 * 
	 * @return 	relevant .jsp as String
	 * @since 	1.0
	 */
	@GetMapping("/")
	public String getHomePage() {
		return "home";
	}
	/**
	 * Gets mapping for the home page
	 * 
	 * @return 	relevant .jsp as String
	 * @since 	1.0
	 */
	@GetMapping("/home")
	public String getHomePageWithHome(HttpSession session) {
		session.removeAttribute("writingReview");
		return "home";
	}
	
	@GetMapping("/errorWrongLogin")
	public String getWrongLoginHomePage(Model model) {
		model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Sorry your username and password do not match");
		return HOME_URL;
	}

	@GetMapping("/errorNoUserLoggedIn")
	public String getNoLoggedInUserPage(Model model) {
		model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Sorry, no user is logged in");
		return HOME_URL;
	}

	@GetMapping("/userUpdated")
	public String userUpdatedPage(Model model) {
		model.addAttribute(UPDATED_USER_MESSAGE_ATTRIBUTE, "User details have been updated");
		return HOME_URL;
	}

	@GetMapping("/errorNoSuchUser")
	public String getNoSuchUserPage(Model model) {
		model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Sorry there is no user with that username");
		return HOME_URL;
	}

	@GetMapping("/errorCanNotRemoveCurrentUser")
	public String getCanNotRemoveCurrentUserPage(Model model, HttpSession session) {
		model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Sorry you can not remove the current user");
		return HOME_URL;
	}

	@GetMapping("/userDeleted")
	public String userDeletedPage(Model model) {
		model.addAttribute(UPDATED_USER_MESSAGE_ATTRIBUTE, "User has been removed");
		return HOME_URL;
	}
}
