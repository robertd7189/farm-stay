package com.fdm.FarmWorkSpringBoot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdm.FarmWorkSpringBoot.model.Business;
import com.fdm.FarmWorkSpringBoot.model.Job;
import com.fdm.FarmWorkSpringBoot.model.Review;
import com.fdm.FarmWorkSpringBoot.model.User;
import com.fdm.FarmWorkSpringBoot.service.JobService;
import com.fdm.FarmWorkSpringBoot.service.ReviewService;

public class ReviewControllerTest {

private ReviewController reviewController;
	
	@Mock 
	private Model mockModel;
	
	@Mock
	private HttpSession  mockSession;
	
	@Mock
	private JobService mockJobService;
	
	@Mock
	private ReviewService mockReviewService;
	
	@Mock
	private Review mockReview;
	
	@Mock
	private User mockUser;
	
	@Mock
	private Business mockBusiness;
	
	@Mock
	private Job mockJob;
	
	@Mock
	private List<Review> mockReviews;
	
	private Review testReview;
	String testComment = "testComment";
	double testPayRate = 18.00;
	String testAccomType = "testAccomType";
	double testAccomCost = 150.00;
	
	@SuppressWarnings("deprecation")
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		reviewController = new ReviewController(mockReviewService, mockJobService);
		testReview = new Review(testComment, testPayRate, testAccomType, testAccomCost, mockUser, mockJob, mockBusiness);
	}
	
	@Test
	public void test_getWriteReviewsPage_returns_url_addJob() {
		String returnedString;
		when(mockSession.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE)).thenReturn(mockUser);
		returnedString = reviewController.getWriteReviewPage(mockModel, mockSession);
		verify(mockModel, times(1)).addAttribute(eq(ReviewController.BIND_REVIEW_ATTRIBUTE), isA(Review.class));
		assertEquals(ReviewController.WRITE_REVIEW_URL, returnedString);
	}
	
	@Test
	public void test_processWriteReviewsPage_returns_url_display_business() {
		String returnedString;
		when(mockSession.getAttribute(ReviewController.CURRENT_JOB_ATTRIBUTE)).thenReturn(mockJob);
		when(mockSession.getAttribute(ReviewController.CURRENT_BUSINESS_ATTRIBUTE)).thenReturn(mockBusiness);
		when(mockReviewService.findAllReviewsByBusiness(mockBusiness)).thenReturn(mockReviews);
		returnedString = reviewController.processWriteReviewPage(testReview, mockSession, mockModel);
		verify(mockModel, times(1)).addAttribute(ReviewController.CURRENT_BUSINESS_ATTRIBUTE, mockBusiness);
		verify(mockModel, times(1)).addAttribute(ReviewController.ALL_BUSINESS_REVIEWS_ATTRIBUTE, mockReviews);
		assertEquals(ReviewController.DISPLAY_BUSINESS_URL, returnedString);
	}
}
