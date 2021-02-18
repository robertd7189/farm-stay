package com.fdm.FarmWorkSpringBoot.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fdm.FarmWorkSpringBoot.model.Business;;

public class BusinessTest {

	private Business testBusiness;
	String testName = "testName";
	String testLocation = "testLocation";
	
	@Before
	public void init() {
		testBusiness = new Business(testName, testLocation);
	}

	@Test
	public void _test_setName_changes_business_Name() {
		String testNewName = "newname";
		testBusiness.setName(testNewName);
		assertEquals(testNewName, testBusiness.getName());
	}

	@Test
	public void _test_setLocation_changes_business_location() {
		String testNewLocation = "newLocation";
		testBusiness.setLocation(testNewLocation);
		assertEquals(testNewLocation, testBusiness.getLocation());
	}

	@Test
	public void test_toString() {
		String returnedString;
		String toStringTest = "Business [id=" + testBusiness.getId() + ", name=" + testName + ", location=" + testLocation + "]";
		returnedString = testBusiness.toString();
		assertEquals(toStringTest, returnedString);
	}

}
