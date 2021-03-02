package com.fdm.FarmWorkSpringBoot.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdm.FarmWorkSpringBoot.model.Business;
import com.fdm.FarmWorkSpringBoot.model.Review;
import com.fdm.FarmWorkSpringBoot.service.BusinessService;
import com.fdm.FarmWorkSpringBoot.service.ReviewService;
/**
 * @author Robert.Dooley
 * @version 1.0
 */
@Controller
public class BusinessController {

	static final String REDIRECT_BUSINESS_NOT_ADDED_URL = "redirect:/businessNotAdded";
	static final String REDIRECT_FIND_JOB_BY_TITLE_URL = "redirect:/findJobByTitle";
	static final String BUSINESS_EXISTS_ATTRIBUTE = "businessExists";
	static final String ADD_BUSINESS_URL = "addBusiness";
	static final String BIND_BUSINESS_ATTRIBUTE = "bindBusiness";
	static final String DISPLAY_BUSINESS_URL = "displayBusiness";
	static final String ALL_BUSINESS_REVIEWS_ATTRIBUTE = "allBusinessReviews";
	static final String CURRENT_BUSINESS_ATTRIBUTE = "currentBusiness";
	static final String FIND_BUSINESS_URL = "findBusiness";
	private Log log = LogFactory.getLog(BusinessController.class);
	private BusinessService businessService;
	private ReviewService reviewService;

	public BusinessController() {
		super();
	}

	@Autowired
	public BusinessController(BusinessService businessService, ReviewService reviewService) {
		super();
		this.businessService = businessService;
		this.reviewService = reviewService;
	}
	/**
	 * Get mapping for finding a business
	 * 
	 * @return 	relevant .jsp as String
	 * @since 	1.0
	 */
	@GetMapping("/findBusinessByName")
	public String getBusinessByName(HttpSession session) {
		if (session.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE) == null)
			return UserController.REDIRECT_ERROR_NO_USER_LOGGED_IN_URL;
		return FIND_BUSINESS_URL;
	}
	/**
	 * Processes the form for finding a business
	 * Sets relevant model and session attributes
	 * 
	 * @return 	relevant .jsp as String
	 * @since 	1.0
	 */
	@PostMapping("/findBusinessByName")
	public String processBusinessByName(HttpSession session, Model model, @RequestParam String name) {
		session.setAttribute(CURRENT_BUSINESS_ATTRIBUTE, null);
		model.addAttribute(CURRENT_BUSINESS_ATTRIBUTE, null);
		Business returnedBusiness = businessService.findBusinessesByName(name);
		log.info("Business: " + returnedBusiness);
		if(returnedBusiness == null) {
			model.addAttribute(CURRENT_BUSINESS_ATTRIBUTE, null);
		} else {
			session.setAttribute(CURRENT_BUSINESS_ATTRIBUTE, returnedBusiness);
			model.addAttribute(CURRENT_BUSINESS_ATTRIBUTE, returnedBusiness);
			List<Review> reviews = reviewService.findAllReviewsByBusiness(returnedBusiness);
			model.addAttribute(ALL_BUSINESS_REVIEWS_ATTRIBUTE, reviews);
			session.setAttribute("writingReview", true);
		}
		return DISPLAY_BUSINESS_URL;
	}
	/**
	 * Get mapping for adding a business
	 * 
	 * @return 	relevant .jsp as String
	 * @since 	1.0
	 */
	@GetMapping("/addBusiness")
	public String getAddBusinessPage(Model model, HttpSession session) {
		if (session.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE) == null)
			return UserController.REDIRECT_ERROR_NO_USER_LOGGED_IN_URL;
		Business businessObj = new Business();
		model.addAttribute(BIND_BUSINESS_ATTRIBUTE, businessObj);
		return ADD_BUSINESS_URL;
	}
	/**
	 * Processes the form for adding a business
	 * Sets relevant model and session attributes
	 * 
	 * @return 	relevant .jsp as String
	 * @since 	1.0
	 */
	@PostMapping("/addBusiness")
	public String processAddBusiness(HttpSession session, Business business, Model model) {
			businessService.saveBusiness(business);
			session.setAttribute(BUSINESS_EXISTS_ATTRIBUTE, business);
			session.setAttribute(CURRENT_BUSINESS_ATTRIBUTE, business);
			model.addAttribute(CURRENT_BUSINESS_ATTRIBUTE, business);
			
			return DISPLAY_BUSINESS_URL;
	}
	/**
	 * Get mapping for displaying a business
	 * 
	 * @return 	relevant .jsp as String
	 * @since 	1.0
	 */
	@GetMapping("/displayBusiness")
	public String getdisplayBusinessPage(HttpSession session) {
		if (session.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE) == null)
			return UserController.REDIRECT_ERROR_NO_USER_LOGGED_IN_URL;
		return DISPLAY_BUSINESS_URL;
	}

}
