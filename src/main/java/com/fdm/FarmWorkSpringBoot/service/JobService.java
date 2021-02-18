package com.fdm.FarmWorkSpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.FarmWorkSpringBoot.dal.JobRepository;
import com.fdm.FarmWorkSpringBoot.model.Job;

/**
 * @author Robert.Dooley
 * @version 1.0
 */
@Service
public class JobService {

	private JobRepository jobRepository;

	public JobService() {
		super();
	}

	@Autowired
	public JobService(JobRepository jobRepository) {
		super();
		this.jobRepository = jobRepository;
	}
	/**
	 * Saves a job to the database
	 * 
	 * @param	job to be saved
	 * @return 	Job
	 * @since 	1.0
	 */
	public Job saveJob(Job job) {
		return this.jobRepository.save(job);
	}

	/**
	 * Finds a job by title
	 * 
	 * @param title as String
	 * @return Job
	 * @since 1.0
	 */
	public Job findJobByTitle(String title) {

		Job foundJob = jobRepository.findByTitle(title);
		if (foundJob == null)
			return null;

		return foundJob;
	}

}
