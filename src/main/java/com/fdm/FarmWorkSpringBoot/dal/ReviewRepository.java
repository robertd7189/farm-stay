package com.fdm.FarmWorkSpringBoot.dal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fdm.FarmWorkSpringBoot.model.Business;
import com.fdm.FarmWorkSpringBoot.model.Job;
import com.fdm.FarmWorkSpringBoot.model.Review;
/**
 * @author Robert.Dooley
 * @version 1.0
 */
@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer>{
	/**
	 * Finds all reviews for a business
	 * 
	 * @param	business
	 * @return 	List of Reviews
	 * @since 	1.0
	 */
	List<Review> findByBusiness(Business business);
	/**
	 * Finds all reviews for a job
	 * 
	 * @param	job
	 * @return 	List of Reviews
	 * @since 	1.0
	 */
	List<Review> findByJob(Job job);
}
