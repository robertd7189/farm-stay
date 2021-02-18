package com.fdm.FarmWorkSpringBoot.controller;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.fdm.FarmWorkSpringBoot.model.User;
import com.fdm.FarmWorkSpringBoot.service.UserService;

public class UserControllerTest {
	
	private UserController userController;
	
	@Mock 
	private Model mockModel;
	
	@Mock
	private HttpSession  mockSession;
	
	@Mock
	private UserService mockService;
	
	@Mock
	private User mockUser;
	
	private User testUser;
	String testUsername = "testUsername";
	String testPassword = "testPassword";
	String testFirstName = "testFName";
	String testLastName = "testLName";
	String testEmail = "testEmail";
	
	@SuppressWarnings("deprecation")
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		userController = new UserController(mockService);
		testUser = new User(testUsername, testEmail, testPassword, testFirstName, testLastName);
	}
	
	@Test
	public void test_getRegisterUserPage_returns_url_register_user() {
		String returnedString;
		String attributeName = UserController.BIND_USER_ATTRIBUTE_NAME;
		returnedString = userController.getRegisterUserPage(mockModel);
		verify(mockModel, times(1)).addAttribute(eq(attributeName), isA(User.class));
		assertEquals(UserController.REGISTER_USER_URL, returnedString);
	}
	
	@Test
	public void test_getCheckUserPage_returns_url_check_user() {
		String returnedString;
		when(mockSession.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE)).thenReturn(mockUser);
		returnedString = userController.getCheckUserPage(mockSession);
		verify(mockSession, times(2)).getAttribute(UserController.ACTIVE_USER_ATTRIBUTE);
		assertEquals(UserController.CHECK_USER_URL, returnedString);
	}
	
	@Test
	public void test_getCheckUserPage_returns_url_redirect_if_no_user() {
		String returnedString;
		when(mockSession.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE)).thenReturn(null);
		returnedString = userController.getCheckUserPage(mockSession);
		verify(mockSession, times(1)).getAttribute(UserController.ACTIVE_USER_ATTRIBUTE);
		assertEquals(UserController.REDIRECT_ERROR_NO_USER_LOGGED_IN_URL, returnedString);
	}
	
	@Test
	public void test_processRegisterUserPage_returns_url_check_user() {
		String returnedString;
		returnedString = userController.processRegisterUserPage(mockUser, mockSession);
		verify(mockSession, times(1)).setAttribute(UserController.ACTIVE_USER_ATTRIBUTE, mockUser);
		assertEquals(UserController.CHECK_USER_URL, returnedString);
	}
	
	@Test
	public void test_processRegisterUserPage_returns_url_redirect_if_no_user() {
		String returnedString;
		returnedString = userController.processRegisterUserPage(null, mockSession);
		assertEquals(UserController.REDIRECT_ERROR_NO_USER_LOGGED_IN_URL, returnedString);
	}
	
	@Test
	public void test_updateUserDetails_sets_appropriate_attributes() {
		
	}

	@Test
	public void test_loginUserPage_returns_url_login() {
		String returnedString;
		returnedString = userController.loginUserPage();
		assertEquals(UserController.LOGIN_URL, returnedString);
	}
	
	@Test
	public void test_processLoginUserPage_returns_url_check_user() {
		String returnedString;
		when(mockService.findUserByUsername(testUsername)).thenReturn(testUser);
		returnedString = userController.processLoginUserPage(testUsername, testPassword, mockSession);
		verify(mockSession, times(1)).setAttribute(UserController.ACTIVE_USER_ATTRIBUTE, testUser);
		assertEquals(UserController.CHECK_USER_URL, returnedString);
	}
	
	@Test
	public void test_processLoginUserPage_returns_url_redirect_wrong_login_if_password_wrong() {
		String returnedString;
		when(mockService.findUserByUsername(testUsername)).thenReturn(testUser);
		returnedString = userController.processLoginUserPage(testUsername, "wrongPassword", mockSession);
		assertEquals(UserController.REDIRECT_ERROR_WRONG_LOGIN_URL, returnedString);
	}
	
	@Test
	public void test_processLoginUserPage_returns_url_redirect_wrong_login_if_username_wrong() {
		String returnedString;
		when(mockService.findUserByUsername(testUsername)).thenReturn(null);
		returnedString = userController.processLoginUserPage(testUsername, testPassword, mockSession);
		assertEquals(UserController.REDIRECT_ERROR_WRONG_LOGIN_URL, returnedString);
	}
	
	@Test
	public void test_logoutUserPage_returns_url_logout() {
		String returnedString;
		when(mockSession.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE)).thenReturn(testUser);
		returnedString = userController.logoutUserPage(mockModel, mockSession);
		assertEquals(UserController.HOME_URL, returnedString);
	}
	
	@Test
	public void test_logoutUserPage_returns_url_redirect_if_no_user_logged_in() {
		String returnedString;
		when(mockSession.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE)).thenReturn(null);
		returnedString = userController.logoutUserPage(mockModel, mockSession);
		assertEquals(UserController.REDIRECT_ERROR_NO_USER_LOGGED_IN_URL, returnedString);
	}
	
	@Test
	public void test_updateUserPage_returns_url_logout() {
		String returnedString;
		when(mockSession.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE)).thenReturn(testUser);
		returnedString = userController.updateUserPage(mockSession);
		assertEquals(UserController.UPDATE_USER_URL, returnedString);
	}
	
	@Test
	public void test_updateUserPage_returns_url_redirect_if_no_user_logged_in() {
		String returnedString;
		when(mockSession.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE)).thenReturn(null);
		returnedString = userController.updateUserPage(mockSession);
		assertEquals(UserController.REDIRECT_ERROR_NO_USER_LOGGED_IN_URL, returnedString);
	}
	
	@Test
	public void test_processUpdateUserPage_returns_url_user_updated() {
		String returnedString;
		String newTestUsername = "testUsername1";
		String newTestPassword = "testPassword1";
		when(mockSession.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE)).thenReturn(testUser);
		returnedString = userController.processUpdateUserPage(newTestUsername, newTestPassword, mockSession);
		verify(mockService, times(1)).saveUser(testUser);
		verify(mockService, times(1)).findUserByUsername(newTestUsername);
		assertEquals(UserController.REDIRECT_USER_UPDATED_URL, returnedString);
	}
	
	@Test
	public void test_deleteUserPage_returns_url_remove_user() {
		String returnedString;
		when(mockSession.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE)).thenReturn(testUser);
		returnedString = userController.deleteUserPage(mockSession);
		assertEquals(UserController.REMOVE_USER_URL, returnedString);
	}
	
	@Test
	public void test_deleteUserPage_returns_url_redirect_if_no_user_logged_in() {
		String returnedString;
		when(mockSession.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE)).thenReturn(null);
		returnedString = userController.deleteUserPage(mockSession);
		assertEquals(UserController.REDIRECT_ERROR_NO_USER_LOGGED_IN_URL, returnedString);
	}
	
	@Test
	public void test_processRemoveUserPage_returns_url_redirect_if_no_user_logged_in() {
		String returnedString;
		when(mockSession.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE)).thenReturn(null);
		when(mockService.findUserByUsername(testUsername)).thenReturn(null);
		returnedString = userController.processRemoveUserPage(testUsername, mockSession);
		assertEquals(UserController.REDIRECT_ERROR_NO_SUCH_USER_URL, returnedString);
	}
	
	@Test
	public void test_processRemoveUserPage_returns_url_redirect_current_user_cannot_be_removed() {
		String returnedString;
		when(mockSession.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE)).thenReturn(testUser);
		when(mockService.findUserByUsername(testUsername)).thenReturn(testUser);
		returnedString = userController.processRemoveUserPage(testUsername, mockSession);
		assertEquals(UserController.REDIRECT_ERROR_CAN_NOT_REMOVE_CURRENT_USER_URL, returnedString);
	}
	
	@Test
	public void test_processRemoveUserPage_returns_url_redirect_user_deleted() {
		String returnedString;
		String newTestUsername = "testUsername1";
		User newTestUser = new User();
		when(mockSession.getAttribute(UserController.ACTIVE_USER_ATTRIBUTE)).thenReturn(testUser);
		when(mockService.findUserByUsername(newTestUsername)).thenReturn(newTestUser);
		returnedString = userController.processRemoveUserPage(newTestUsername, mockSession);
		verify(mockService, times(1)).deleteUser(newTestUsername);
		assertEquals(UserController.REDIRECT_USER_DELETED_URL, returnedString);
	}
	
}
