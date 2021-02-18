package com.fdm.FarmWorkSpringBoot.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*; 

import com.fdm.FarmWorkSpringBoot.model.User;
import com.fdm.FarmWorkSpringBoot.dal.UserRepository;
import com.fdm.FarmWorkSpringBoot.service.UserService;

public class UserServiceTest {
	
	private UserService userService;
	
	@Mock
	private UserRepository mockRepository;
	
	@Mock
	private User mockUser;
	
	@SuppressWarnings("deprecation")
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		userService = new UserService(mockRepository);
	}
	
	@Test
	public void test_addUser_calls_saveUser_from_repo() {
		userService.saveUser(mockUser);
		verify(mockRepository, times(1)).save(mockUser);
	}
	
	@Test
	public void test_getUserByUsername_calls_getUserByUsername_from_repo() {
		userService.findUserByUsername(mockUser.getUsername());
		verify(mockRepository, times(1)).findByUsername(mockUser.getUsername());
	}
	
	@Test
	public void test_updateUser_calls_updateUser_from_repo() {
		String oldUsername = "test";
		mockUser.setUsername(oldUsername);
		userService.saveUser(mockUser);
		verify(mockRepository, times(1)).save(mockUser);
	}
	
	@Test
	public void test_deleteUser_calls_deleteUser_from_repo() {
		when(userService.findUserByUsername(mockUser.getUsername())).thenReturn(mockUser);
		userService.deleteUser(mockUser.getUsername());
		verify(mockRepository, times(1)).delete(mockUser);
	}
}
