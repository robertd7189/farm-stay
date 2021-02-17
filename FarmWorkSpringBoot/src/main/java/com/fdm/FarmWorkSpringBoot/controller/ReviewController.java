package com.fdm.FarmWorkSpringBoot.controller;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fdm.FarmWorkSpringBoot.model.Business;
import com.fdm.FarmWorkSpringBoot.model.Job;
import com.fdm.FarmWorkSpringBoot.model.Review;
import com.fdm.FarmWorkSpringBoot.model.User;
import com.fdm.FarmWorkSpringBoot.service.JobService;
import com.fdm.FarmWorkSpringBoot.service.ReviewService;

/**
 * @author Robert.Dooley
 * @version 1.0
 */
@Controller
public class ReviewController {

	static final String REDIRECT_ERROR_NO_REVIEW_CREATED_URL = "redirect:/errorNoReviewCreated";
	static final String DISPLAY_BUSINESS_URL = "displayBusiness";
	static final String ALL_BUSINESS_REVIEWS_ATTRIBUTE = "allBusinessReviews";
	static final String CURRENT_REVIEW_ATTRIBUTE = "currentReview";
	static final String CURRENT_JOB_ATTRIBUTE = "currentJob";
	static final String CURRENT_BUSINESS_ATTRIBUTE = "currentBusiness";
	static final String WRITE_REVIEW_URL = "writeReview";
	static final String BIND_REVIEW_ATTRIBUTE = "bindReview";
//	private Log log = LogFactory.getLog(ReviewController.class);
	private ReviewService reviewService;
	private JobService jobService;

	public ReviewController() {
		super();
	}

	@Autowired
	public ReviewController(ReviewService reviewService, JobService jobService) {
		super();
		this.reviewService = reviewService;
		this.jobService = jobService;
	}

	/**
	 * Gets mapping for the write review page
	 * 
	 * @return relevant .jsp as String
	 * @since 1.0
	 */
	@GetMapping("/writeReview")
	public String getWriteReviewPage(Model model, HttpSession session) {
		if (session.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE) == null)
			return UserController.REDIRECT_ERROR_NO_USER_LOGGED_IN_URL;
		Review reviewObj = new Review();
		model.addAttribute(BIND_REVIEW_ATTRIBUTE, reviewObj);
		return WRITE_REVIEW_URL;
	}

	/**
	 * Processes the form for the write review page Gets the relevant User, Business and Job associated with the Review
	 * 
	 * @return relevant .jsp as String
	 * @since 1.0
	 */
	@PostMapping("/processReview")
	public String processWriteReviewPage(Review returnedReview, HttpSession session, Model model) {
		returnedReview.setUser((User) session.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE));
		Business currentBusiness = (Business) session.getAttribute(CURRENT_BUSINESS_ATTRIBUTE);
		returnedReview.setBusiness(currentBusiness);
		returnedReview.setJob((Job) session.getAttribute(CURRENT_JOB_ATTRIBUTE));
		reviewService.saveReview(returnedReview);
		session.setAttribute(CURRENT_REVIEW_ATTRIBUTE, returnedReview);
		Job currentJob = (Job) session.getAttribute(CURRENT_JOB_ATTRIBUTE);
		currentJob.setAvgPay(updateAvgPrice(session));
		jobService.saveJob(currentJob);
		model.addAttribute(CURRENT_BUSINESS_ATTRIBUTE, currentBusiness);
		model.addAttribute(ALL_BUSINESS_REVIEWS_ATTRIBUTE, reviewService.findAllReviewsByBusiness(currentBusiness));
		session.removeAttribute("writingReview");
		return DISPLAY_BUSINESS_URL;
	}

	/**
	 * Helper function to update a Jobs average pay every time a Review is left
	 * 
	 * @return calculated average pay as a double
	 * @since 1.0
	 */
	private double updateAvgPrice(HttpSession session) {
		List<Review> reviews = reviewService.findAllReviewsByJob((Job) session.getAttribute(CURRENT_JOB_ATTRIBUTE));
		double totalPay = 0;
		double count = 0;
		for (Review review : reviews) {
			totalPay += review.getPayRate();
			count++;
		}
		DecimalFormat df = new DecimalFormat("#.##");
		return Double.valueOf(df.format(totalPay / count));
	}
}
