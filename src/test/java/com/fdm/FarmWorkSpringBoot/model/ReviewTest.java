package com.fdm.FarmWorkSpringBoot.model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.FarmWorkSpringBoot.model.Review;;

public class ReviewTest {

	private Review testReview;
	String testComment = "testComment";
	double testPayRate = 18.00;
	String testAccomType = "testAccomType";
	double testAccomCost = 150.00;
	
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
		testReview = new Review(testComment, testPayRate, testAccomType, testAccomCost, mockUser, mockJob, mockBusiness);
	}

	@Test
	public void _test_setComment_changes_review_comment() {
		String testNewComment = "newComment";
		testReview.setComment(testNewComment);
		assertEquals(testNewComment, testReview.getComment());
	}

	@Test
	public void _test_setPayRate_changes_review_payRate() {
		double testNewPayRate = 19.00;
		testReview.setPayRate(testNewPayRate);;
		assertEquals(testNewPayRate, testReview.getPayRate(), 0.0001);
	}

	@Test
	public void _test_setAccomType_changes_review_accomType() {
		String testNewAccomType = "newAcoomType";
		testReview.setAccommodationType(testNewAccomType);
		assertEquals(testNewAccomType, testReview.getAccommodationType());
	}

	@Test
	public void _test_setAccomCost_changes_review_AccomCost() {
		double testNewAccomCost = 200.00;
		testReview.setAccommodationCost(testNewAccomCost);
		assertEquals(testNewAccomCost, testReview.getAccommodationCost(), 0.0001);
	}

	@Test
	public void _test_setUser_changes_review_user() {
		User testNewUser = new User();
		testReview.setUser(testNewUser);
		assertEquals(testNewUser, testReview.getUser());
	}
	
	@Test
	public void _test_setJob_changes_review_job() {
		Job testNewJob = new Job();
		testReview.setJob(testNewJob);
		assertEquals(testNewJob, testReview.getJob());
	}
	
	@Test
	public void _test_setBusiness_changes_review_business() {
		Business testNewBusiness = new Business();
		testReview.setBusiness(testNewBusiness);
		assertEquals(testNewBusiness, testReview.getBusiness());
	}

	@Test
	public void test_toString() {
		String returnedString;
		String toStringTest = "Review [id=" + testReview.getId() + ", comment=" + testComment + ", payRate=" + testPayRate + ", accommodationType="
				+ testAccomType + ", accommodationCost=" + testAccomCost + "]";
		returnedString = testReview.toString();
		assertEquals(toStringTest, returnedString);
	}

}
