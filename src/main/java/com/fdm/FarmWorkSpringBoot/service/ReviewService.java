package com.fdm.FarmWorkSpringBoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.FarmWorkSpringBoot.dal.ReviewRepository;
import com.fdm.FarmWorkSpringBoot.model.Business;
import com.fdm.FarmWorkSpringBoot.model.Job;
import com.fdm.FarmWorkSpringBoot.model.Review;
/**
 * @author Robert.Dooley
 * @version 1.0
 */
@Service
public class ReviewService {
	
	private ReviewRepository reviewRepository;

	public ReviewService() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public ReviewService(ReviewRepository reviewRepository) {
		super();
		this.reviewRepository = reviewRepository;
	}
	/**
	 * Saves a review to the database
	 * 
	 * @param	review to be saved
	 * @return 	Review
	 * @since 	1.0
	 */
	public Review saveReview(Review review) {
		return this.reviewRepository.save(review);
	}
	/**
	 * Finds all reviews for a business
	 * 
	 * @param	business
	 * @return 	List of Reviews
	 * @since 	1.0
	 */
	public List<Review> findAllReviewsByBusiness(Business business) {
		return reviewRepository.findByBusiness(business);
	}
	/**
	 * Finds all reviews for a job
	 * 
	 * @param	job
	 * @return 	List of Reviews
	 * @since 	1.0
	 */
	public List<Review> findAllReviewsByJob(Job job) {
		return reviewRepository.findByJob(job);
	}

}
