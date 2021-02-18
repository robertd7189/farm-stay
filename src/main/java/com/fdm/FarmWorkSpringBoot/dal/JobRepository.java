package com.fdm.FarmWorkSpringBoot.dal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fdm.FarmWorkSpringBoot.model.Job;
/**
 * @author Robert.Dooley
 * @version 1.0
 */
@Repository
public interface JobRepository extends CrudRepository<Job, Integer>{
	/**
	 * Finds a job by title
	 * 
	 * @param	title as String
	 * @return 	Job
	 * @since 	1.0
	 */
	Job findByTitle(String title);

}
