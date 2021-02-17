package com.fdm.FarmWorkSpringBoot.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdm.FarmWorkSpringBoot.model.Business;
import com.fdm.FarmWorkSpringBoot.model.Review;
import com.fdm.FarmWorkSpringBoot.model.User;
import com.fdm.FarmWorkSpringBoot.service.BusinessService;
import com.fdm.FarmWorkSpringBoot.service.ReviewService;

public class BusinessControllerTest {
	
	private BusinessController businessController;
	
	@Mock 
	private Model mockModel;
	
	@Mock
	private HttpSession  mockSession;
	
	@Mock
	private BusinessService mockBusinessService;
	
	@Mock
	private ReviewService mockReviewService;
	
	@Mock
	private List<Review> mockReviews;
	
	@Mock
	private Business mockBusiness;
	
	@Mock
	private User mockUser;
	
	private Business testBusiness;
	String testName = "testName";
	String testLocation = "testLocation";
	
	@SuppressWarnings("deprecation")
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		businessController = new BusinessController(mockBusinessService, mockReviewService);
		testBusiness = new Business(testName, testLocation);
	}
	
	@Test
	public void test_findBusinessByName_returns_url_redirect_no_user() {
		String returnedString;
		returnedString = businessController.getBusinessByName(mockSession);
		assertEquals(UserController.REDIRECT_ERROR_NO_USER_LOGGED_IN_URL, returnedString);
	}
	
	@Test
	public void test_findBusinessByName_returns_url_findBusiness() {
		String returnedString;
		when(mockSession.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE)).thenReturn(mockUser);
		returnedString = businessController.getBusinessByName(mockSession);
		assertEquals(BusinessController.FIND_BUSINESS_URL, returnedString);
	}
	
	@Test
	public void test_processfindBusinessByName_returns_url_displayBusiness() {
		String returnedString;
		when(mockBusinessService.findBusinessesByName(testName)).thenReturn(testBusiness);
		when(mockReviewService.findAllReviewsByBusiness(testBusiness)).thenReturn(mockReviews);
		returnedString = businessController.processBusinessByName(mockSession, mockModel, testName);
		verify(mockSession, times(1)).setAttribute(BusinessController.CURRENT_BUSINESS_ATTRIBUTE, testBusiness);
		verify(mockModel, times(1)).addAttribute(BusinessController.CURRENT_BUSINESS_ATTRIBUTE, testBusiness);
		verify(mockModel, times(1)).addAttribute(BusinessController.ALL_BUSINESS_REVIEWS_ATTRIBUTE, mockReviews);
		assertEquals(BusinessController.DISPLAY_BUSINESS_URL, returnedString);
	}
	
	@Test
	public void test_processfindBusinessByName_returns_url_redirect_when_no_user() {
		String returnedString;
		when(mockBusinessService.findBusinessesByName(testName)).thenReturn(null);
		returnedString = businessController.processBusinessByName(mockSession, mockModel, testName);
		verify(mockModel, times(2)).addAttribute(BusinessController.CURRENT_BUSINESS_ATTRIBUTE, null);
		assertEquals(BusinessController.DISPLAY_BUSINESS_URL, returnedString);
	}
	
	@Test
	public void test_processfindBusinessByName_returns_url_displayBusiness_when_retruned_business_is_null() {
		String returnedString;
		when(mockSession.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE)).thenReturn(mockUser);
		when(mockBusinessService.findBusinessesByName(testName)).thenReturn(null);
		returnedString = businessController.processBusinessByName(mockSession, mockModel, testName);
		verify(mockModel, times(2)).addAttribute(BusinessController.CURRENT_BUSINESS_ATTRIBUTE, null);
		assertEquals(BusinessController.DISPLAY_BUSINESS_URL, returnedString);
	}
	
	@Test
	public void test_getAddBusinessPage_returns_url_addBusiness() {
		String returnedString;
		when(mockSession.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE)).thenReturn(mockUser);
		returnedString = businessController.getAddBusinessPage(mockModel, mockSession);
		verify(mockModel, times(1)).addAttribute(eq(BusinessController.BIND_BUSINESS_ATTRIBUTE), isA(Business.class));
		assertEquals(BusinessController.ADD_BUSINESS_URL, returnedString);
	}
	
	@Test
	public void test_getAddBusinessPage_returns_url_redirect_when_no_user() {
		String returnedString;
		returnedString = businessController.getAddBusinessPage(mockModel, mockSession);
		assertEquals(UserController.REDIRECT_ERROR_NO_USER_LOGGED_IN_URL, returnedString);
	}
	
	@Test
	public void test_processAddBusiness_returns_url_redirect_find_job() {
		String returnedString;
		returnedString = businessController.processAddBusiness(mockSession, testBusiness, mockModel);
		verify(mockSession, times(1)).setAttribute(BusinessController.BUSINESS_EXISTS_ATTRIBUTE, testBusiness);
		verify(mockSession, times(1)).setAttribute(BusinessController.CURRENT_BUSINESS_ATTRIBUTE, testBusiness);
		assertEquals(BusinessController.DISPLAY_BUSINESS_URL, returnedString);
	}

}
