package com.fdm.FarmWorkSpringBoot.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.FarmWorkSpringBoot.dal.ReviewRepository;
import com.fdm.FarmWorkSpringBoot.model.Business;
import com.fdm.FarmWorkSpringBoot.model.Job;
import com.fdm.FarmWorkSpringBoot.model.Review;
import com.fdm.FarmWorkSpringBoot.model.User;

public class ReviewServiceTest {
	
	private ReviewService reviewService;
	
	@Mock
	private ReviewRepository mockRepository;
	
	@Mock
	private Review mockReview;
	
	@Mock
	private User mockUser;
	
	@Mock
	private Business mockBusiness;
	
	@Mock
	private Job mockJob;

	@SuppressWarnings("deprecation")
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		reviewService = new ReviewService(mockRepository);
	}
	
	@Test
	public void test_saveReview_calls_saveReview_from_repo() {
		reviewService.saveReview(mockReview);
		verify(mockRepository, times(1)).save(mockReview);
	}
	
	@Test
	public void test_findAllReviewsByBusiness_calls_findByBusiness_from_repo() {
		reviewService.findAllReviewsByBusiness(mockBusiness);
		verify(mockRepository, times(1)).findByBusiness(mockBusiness);
	}
	
	@Test
	public void test_findAllReviewsByJob_calls_findByJob_from_repo() {
		reviewService.findAllReviewsByJob(mockJob);
		verify(mockRepository, times(1)).findByJob(mockJob);
	}
}
