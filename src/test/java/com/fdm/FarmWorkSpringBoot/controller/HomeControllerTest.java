package com.fdm.FarmWorkSpringBoot.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class HomeControllerTest {
	
	private HomeController homeController;
	
	@Mock
	private Model mockModel;
	
	@Mock
	private HttpSession mockSession;
	
	@SuppressWarnings("deprecation")
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		homeController = new HomeController();
	}
	
	@Test
	public void test_getHomePage_returns_home_url() {
		String returnedString;
		returnedString = homeController.getHomePage();
		assertEquals(HomeController.HOME_URL, returnedString);
	}
	
	@Test
	public void test_getWrongLoginHomePage_returns_home_url() {
		String returnedString;
		returnedString = homeController.getWrongLoginHomePage(mockModel);
		verify(mockModel, times(1)).addAttribute(HomeController.ERROR_MESSAGE_ATTRIBUTE, "Sorry your username and password do not match");
		assertEquals(HomeController.HOME_URL, returnedString);
	}
	
	@Test
	public void test_getNoLoggedInUserPage_returns_home_url() {
		String returnedString;
		returnedString = homeController.getNoLoggedInUserPage(mockModel);
		verify(mockModel, times(1)).addAttribute(HomeController.ERROR_MESSAGE_ATTRIBUTE, "Sorry, no user is logged in");
		assertEquals(HomeController.HOME_URL, returnedString);
	}
	
	
	@Test
	public void test_userUpdatedPage_returns_home_url() {
		String returnedString;
		returnedString = homeController.userUpdatedPage(mockModel);
		verify(mockModel, times(1)).addAttribute(HomeController.UPDATED_USER_MESSAGE_ATTRIBUTE, "User details have been updated");
		assertEquals(HomeController.HOME_URL, returnedString);
	}
	@Test
	public void test_getNoSuchUserPage_returns_home_url() {
		String returnedString;
		returnedString = homeController.getNoSuchUserPage(mockModel);
		verify(mockModel, times(1)).addAttribute(HomeController.ERROR_MESSAGE_ATTRIBUTE, "Sorry there is no user with that username");
		assertEquals(HomeController.HOME_URL, returnedString);
	}
	@Test
	public void test_getCanNotRemoveCurrentUserPage_returns_home_url() {
		String returnedString;
		returnedString = homeController.getCanNotRemoveCurrentUserPage(mockModel, mockSession);
		verify(mockModel, times(1)).addAttribute(HomeController.ERROR_MESSAGE_ATTRIBUTE, "Sorry you can not remove the current user");
		assertEquals(HomeController.HOME_URL, returnedString);
	}
	@Test
	public void test_userDeletedPage_returns_home_url() {
		String returnedString;
		returnedString = homeController.userDeletedPage(mockModel);
		verify(mockModel, times(1)).addAttribute(HomeController.UPDATED_USER_MESSAGE_ATTRIBUTE, "User has been removed");
		assertEquals(HomeController.HOME_URL, returnedString);
	}

}
