package com.fdm.FarmWorkSpringBoot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdm.FarmWorkSpringBoot.model.Job;
import com.fdm.FarmWorkSpringBoot.model.User;
import com.fdm.FarmWorkSpringBoot.service.JobService;

public class JobControllerTest {

	private JobController jobController;
	
	@Mock 
	private Model mockModel;
	
	@Mock
	private HttpSession  mockSession;
	
	@Mock
	private JobService mockJobService;
	
	@Mock
	private Job mockJob;
	
	@Mock
	private User mockUser;
	
	private Job testJob;
	String testTitle = "testTitle";
	String testType = "testType";
	double testAvgPay = 15.00;
	
	@SuppressWarnings("deprecation")
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		jobController = new JobController(mockJobService);
		testJob = new Job(testTitle, testType);
		testJob.setAvgPay(testAvgPay);
	}
	
	@Test
	public void test_getJobByTitle_returns_url_findJob() {
		String returnedString;
		when(mockSession.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE)).thenReturn(mockUser);
		returnedString = jobController.getJobByTitle(mockSession);
		assertEquals(JobController.FIND_JOB_URL, returnedString);
	}
	
	@Test
	public void test_getJobByTitle_returns_url_no_user_error_when_no_user() {
		String returnedString;
		when(mockSession.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE)).thenReturn(null);
		returnedString = jobController.getJobByTitle(mockSession);
		assertEquals(UserController.REDIRECT_ERROR_NO_USER_LOGGED_IN_URL, returnedString);
	}
	
	@Test
	public void test_processFindJobByTitle_returns_url_displayJob_when_job_not_found() {
		String returnedString;
		when(mockJobService.findJobByTitle(testTitle)).thenReturn(null);
		returnedString = jobController.processFindJobByTitle(mockSession, mockModel, testTitle);
		verify(mockModel, times(1)).addAttribute(JobController.CURRENT_JOB_ATTRIBUTE, "Error, job not found");
		assertEquals(JobController.DISPLAY_JOB_URL, returnedString);
	}
	
	@Test
	public void test_processFindJobByTitle_returns_url_findBusiness() {
		String returnedString;
		when(mockJobService.findJobByTitle(testTitle)).thenReturn(testJob);
		returnedString = jobController.processFindJobByTitle(mockSession, mockModel, testTitle);
		verify(mockSession, times(1)).setAttribute(JobController.CURRENT_JOB_ATTRIBUTE, testJob);
		verify(mockModel, times(1)).addAttribute(JobController.CURRENT_JOB_ATTRIBUTE, testJob);
		assertEquals(JobController.DISPLAY_JOB_URL, returnedString);
	}
	
	@Test
	public void test_getAddJobsPage_returns_url_addJob() {
		String returnedString;
		when(mockSession.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE)).thenReturn(mockUser);
		returnedString = jobController.getAddJobPage(mockModel, mockSession);
		verify(mockModel, times(1)).addAttribute(eq(JobController.BIND_JOB_ATTRIBUTE), isA(Job.class));
		assertEquals(JobController.ADD_JOB_URL, returnedString);
	}
	
	@Test
	public void test_processAddJob_returns_url_redirect_write_review() {
		String returnedString;
		returnedString = jobController.processAddJob(mockSession, testJob);
		verify(mockSession, times(1)).setAttribute(JobController.JOB_EXISTS_ATTRIBUTE, testJob);
		verify(mockSession, times(1)).setAttribute(JobController.CURRENT_JOB_ATTRIBUTE, testJob);
		assertEquals(JobController.DISPLAY_JOB_URL, returnedString);
	}
}
