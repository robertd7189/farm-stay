package com.fdm.FarmWorkSpringBoot.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fdm.FarmWorkSpringBoot.model.User;

public class UserTest {

	private User testUser;
	String testUsername = "testUsername";
	String testPassword = "testPassword";
	String testFirstName = "testFName";
	String testLastName = "testLName";
	String testEmail = "testEmail";

	@Before
	public void init() {
		testUser = new User(testUsername, testEmail, testPassword, testFirstName, testLastName);
	}

	@Test
	public void _test_setFName_changes_user_fName() {
		String testNewFName = "newFname";
		testUser.setfName(testNewFName);
		assertEquals(testNewFName, testUser.getfName());
	}
	
	@Test
	public void _test_setId_changes_user_Id() {
		int testNewId = 2;
		testUser.setId(testNewId);
		assertEquals(testNewId, testUser.getId());
	}
	
	@Test
	public void _test_setReviews_changes_user_reviews() {
		List<Review> newReviews = new ArrayList<Review>();
		testUser.setReviews(newReviews);;
		assertEquals(newReviews, testUser.getReviews());
	}

	@Test
	public void _test_setLName_changes_user_lName() {
		String testNewLName = "newLname";
		testUser.setlName(testNewLName);
		assertEquals(testNewLName, testUser.getlName());
	}

	@Test
	public void _test_setUsername_changes_user_username() {
		String testNewUsername = "newUsername";
		testUser.setUsername(testNewUsername);
		assertEquals(testNewUsername, testUser.getUsername());
	}

	@Test
	public void _test_setPassword_changes_user_password() {
		String testNewPassword = "newPassword";
		testUser.setPassword(testNewPassword);
		assertEquals(testNewPassword, testUser.getPassword());
	}

	@Test
	public void _test_setEmail_changes_user_Email() {
		String testNewEmail = "newEmail";
		testUser.setEmail(testNewEmail);
		assertEquals(testNewEmail, testUser.getEmail());
	}

	@Test
	public void test_toString() {
		String returnedString;
		String toStringTest = "User [id=" + testUser.getId() + ", username=" + testUsername + ", fName=" + testFirstName
				+ ", lName=" + testLastName + ", password=" + testPassword + ", email=" + testEmail + "]";
		returnedString = testUser.toString();
		assertEquals(toStringTest, returnedString);
	}

}
