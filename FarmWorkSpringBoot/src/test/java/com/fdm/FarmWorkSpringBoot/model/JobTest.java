package com.fdm.FarmWorkSpringBoot.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fdm.FarmWorkSpringBoot.model.Job;

public class JobTest {

	private Job testJob;
	String testTitle = "testTitle";
	String testType = "testType";
	double testAvgPay = 15.00;
	
	@Before
	public void init() {
		testJob = new Job(testTitle, testType);
	}

	@Test
	public void _test_setTitle_changes_job_title() {
		String testNewTitle = "newTitle";
		testJob.setTitle(testNewTitle);
		assertEquals(testNewTitle, testJob.getTitle());
	}

	@Test
	public void _test_setType_changes_job_type() {
		String testNewType = "newType";
		testJob.setType(testNewType);
		assertEquals(testNewType, testJob.getType());
	}
	
	@Test
	public void _test_setAvgPay_changes_job_AvgPay() {
		double testNewAvgPay = 12.00;
		testJob.setAvgPay(testNewAvgPay);
		assertEquals(testNewAvgPay, testJob.getAvgPay(), 0.0001);
	}

	@Test
	public void test_toString() {
		String returnedString;
		testJob.setAvgPay(testAvgPay);
		String toStringTest = "Job [id=" + testJob.getId() + ", title=" + testTitle + ", type=" + testType + ", avgPay=" + testAvgPay + "]";
		returnedString = testJob.toString();
		assertEquals(toStringTest, returnedString);
	}

}
