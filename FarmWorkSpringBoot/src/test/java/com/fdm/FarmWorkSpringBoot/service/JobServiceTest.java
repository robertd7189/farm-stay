package com.fdm.FarmWorkSpringBoot.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*; 

import com.fdm.FarmWorkSpringBoot.model.Job;
import com.fdm.FarmWorkSpringBoot.dal.JobRepository;

public class JobServiceTest {
	
	private JobService jobService;
	
	@Mock
	private JobRepository mockRepository;
	
	@Mock
	private Job mockJob;
	
	private Job testJob;
	private String testTitle = "testTitle";
	private String testType = "testType";
	
	@SuppressWarnings("deprecation")
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		jobService = new JobService(mockRepository);
		testJob = new Job(testTitle, testType);
	}
	
	@Test
	public void test_saveJob_calls_saveJob_from_repo() {
		jobService.saveJob(mockJob);
		verify(mockRepository, times(1)).save(mockJob);
	}
	
	@Test
	public void test_findJobByTitle_calls_findByTitle_from_repo_returns_null_when_not_found() {
		jobService.findJobByTitle(mockJob.getTitle());
		verify(mockRepository, times(1)).findByTitle(mockJob.getTitle());
	}
	
	@Test
	public void test_findJobByTitle_calls_findByName_from_repo_returns_job() {
		when(mockRepository.findByTitle(testTitle)).thenReturn(testJob);
		jobService.findJobByTitle(testTitle);
		verify(mockRepository, times(1)).findByTitle(testJob.getTitle());
	}
}
