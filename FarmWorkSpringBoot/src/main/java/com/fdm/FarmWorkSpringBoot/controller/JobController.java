package com.fdm.FarmWorkSpringBoot.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdm.FarmWorkSpringBoot.model.Job;
import com.fdm.FarmWorkSpringBoot.service.JobService;

/**
 * @author Robert.Dooley
 * @version 1.0
 */

@Controller
public class JobController {

	static final String REDIRECT_JOB_NOT_ADDED_URL = "redirect:/jobNotAdded";
	static final String JOB_EXISTS_ATTRIBUTE = "jobExists";
	static final String ADD_JOB_URL = "addJob";
	static final String BIND_JOB_ATTRIBUTE = "bindJob";
	static final String REDIRECT_WRITE_REVIEW_URL = "redirect:/writeReview";
	static final String DISPLAY_JOB_URL = "displayJob";
	static final String CURRENT_JOB_ATTRIBUTE = "currentJob";
	static final String FIND_JOB_URL = "findJob";
//	private Log log = LogFactory.getLog(JobController.class);
	private JobService jobService;
	
	private Log log = LogFactory.getLog(JobController.class);

	public JobController() {
		super();
	}

	@Autowired
	public JobController(JobService jobService) {
		super();
		this.jobService = jobService;
	}
	/**
	 * Get mapping for finding a job
	 * 
	 * @return 	relevant .jsp as String
	 * @since 	1.0
	 */
	@GetMapping("/findJobByTitle")
	public String getJobByTitle(HttpSession session) {
		if (session.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE) == null)
			return UserController.REDIRECT_ERROR_NO_USER_LOGGED_IN_URL;

		return FIND_JOB_URL;
	}
	/**
	 * Processes the form for finding a job
	 * Sets relevant model and session attributes
	 * 
	 * @return 	relevant .jsp as String
	 * @since 	1.0
	 */
	@PostMapping("findJobByTitle")
	public String processFindJobByTitle(HttpSession session, Model model, @RequestParam String title) {
		session.removeAttribute(CURRENT_JOB_ATTRIBUTE);
		model.addAttribute(CURRENT_JOB_ATTRIBUTE, null);
		Job returnedJob = jobService.findJobByTitle(title);
		if (returnedJob == null) {
			model.addAttribute(CURRENT_JOB_ATTRIBUTE, "Error, job not found");
			return DISPLAY_JOB_URL;
		} else {
			session.setAttribute(CURRENT_JOB_ATTRIBUTE, returnedJob);
			model.addAttribute(CURRENT_JOB_ATTRIBUTE, returnedJob);
			if(session.getAttribute("writingReview") == null || !(boolean)session.getAttribute("writingReview"))
				return DISPLAY_JOB_URL;
		}
		return REDIRECT_WRITE_REVIEW_URL;

	}
	/**
	 * Get mapping for adding a job
	 * 
	 * @return 	relevant .jsp as String
	 * @since 	1.0
	 */
	@GetMapping("/addJob")
	public String getAddJobPage(Model model, HttpSession session) {
		if (session.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE) == null)
			return UserController.REDIRECT_ERROR_NO_USER_LOGGED_IN_URL;
		Job jobObj = new Job();
		model.addAttribute(BIND_JOB_ATTRIBUTE, jobObj);
		return ADD_JOB_URL;
	}
	/**
	 * Processes the form for adding a job
	 * Sets relevant model and session attributes
	 * 
	 * @return 	relevant .jsp as String
	 * @since 	1.0
	 */
	@PostMapping("/addJob")
	public String processAddJob(HttpSession session, Job job) {
		jobService.saveJob(job);
		session.setAttribute(JOB_EXISTS_ATTRIBUTE, job);
		session.setAttribute(CURRENT_JOB_ATTRIBUTE, job);
		log.info(session.getAttribute("writingReview"));
		if(session.getAttribute("writingReview") == null || !(boolean)session.getAttribute("writingReview"))
			return DISPLAY_JOB_URL;
		
		return REDIRECT_WRITE_REVIEW_URL;
	}
}
