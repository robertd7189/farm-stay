package com.fdm.FarmWorkSpringBoot.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fdm.FarmWorkSpringBoot.model.Business;
import com.fdm.FarmWorkSpringBoot.model.Job;
import com.fdm.FarmWorkSpringBoot.model.Review;
import com.fdm.FarmWorkSpringBoot.model.User;
import com.fdm.FarmWorkSpringBoot.service.BusinessService;
import com.fdm.FarmWorkSpringBoot.service.JobService;
import com.fdm.FarmWorkSpringBoot.service.ReviewService;
import com.fdm.FarmWorkSpringBoot.service.UserService;
/**
 * @author Robert.Dooley
 * @version 1.0
 * Run at startup to pre populate the database with some
 * Users, Businesses, Jobs and Reviews
 */
@Component
public class DataLoader implements ApplicationRunner{

	private UserService userService;
	private BusinessService businessService;
	private ReviewService reviewService;
	private JobService jobService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		User user1 = new User("Robert", "asdsdfwr", "12345", "Robert", "Dooley");
		User user2 = new User("James", "asdsdfwr", "asdfg", "James", "Dooley");
		userService.saveUser(user1);
		userService.saveUser(user2);
		
		Business bus1 = new Business("Joes Farm", "Kerang");
		Business bus2 = new Business("Jerris Farm", "Quambatook");
		businessService.saveBusiness(bus1);
		businessService.saveBusiness(bus2);
		
		Job job1 = new Job("Fruit Picker", "Picker");
		Job job2 = new Job("Grape Picker", "Picker");
		job1.setAvgPay(15.97);
		job2.setAvgPay(17.25);
		jobService.saveJob(job1);
		jobService.saveJob(job2);
		
		Review review1 = new Review("Easy Job", 15.97, "Share House", 160, user1, job1, bus1);
		Review review2 = new Review("Fun Job", 15.97, "Share House", 160, user2, job1, bus1);
		Review review3 = new Review("Hard Job", 17.00, "Caravan", 140, user1, job2, bus2);
		Review review4 = new Review("Tough Job", 17.50, "Caravan", 140, user2, job2, bus2);
		reviewService.saveReview(review1);
		reviewService.saveReview(review2);
		reviewService.saveReview(review3);
		reviewService.saveReview(review4);
		

		
	}

	public DataLoader() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public DataLoader(UserService userService, BusinessService businessService, ReviewService reviewService,
			JobService jobService) {
		super();
		this.userService = userService;
		this.businessService = businessService;
		this.reviewService = reviewService;
		this.jobService = jobService;
	}
	
	

}
