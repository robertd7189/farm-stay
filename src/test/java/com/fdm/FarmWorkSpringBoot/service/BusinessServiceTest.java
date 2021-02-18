package com.fdm.FarmWorkSpringBoot.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*; 

import com.fdm.FarmWorkSpringBoot.model.Business;
import com.fdm.FarmWorkSpringBoot.dal.BusinessRepository;
import com.fdm.FarmWorkSpringBoot.service.BusinessService;

public class BusinessServiceTest {
	
	private BusinessService businessService;
	
	@Mock
	private BusinessRepository mockRepository;
	
	@Mock
	private Business mockBusiness;
	
	private Business testBusiness;
	private String testName = "testName";
	private String testLocation = "testLocation";
	
	@SuppressWarnings("deprecation")
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		businessService = new BusinessService(mockRepository);
		testBusiness = new Business(testName, testLocation);
	}
	
	@Test
	public void test_saveBusiness_calls_saveBusiness_from_repo() {
		businessService.saveBusiness(mockBusiness);
		verify(mockRepository, times(1)).save(mockBusiness);
	}
	
	@Test
	public void test_findBusinessByName_calls_findByName_from_repo_returns_null_when_not_found() {
		businessService.findBusinessesByName(mockBusiness.getName());
		verify(mockRepository, times(1)).findByName(mockBusiness.getName());
	}
	
	@Test
	public void test_findBusinessByName_calls_findByName_from_repo_returns_business() {
		when(mockRepository.findByName(testName)).thenReturn(testBusiness);
		businessService.findBusinessesByName(testBusiness.getName());
		verify(mockRepository, times(1)).findByName(testBusiness.getName());
	}
	
	@Test
	public void test_findBusinessByLocation_calls_findByLocation_from_repo() {
		businessService.findBusinessesByLocation(testLocation);
		verify(mockRepository, times(1)).findByLocation(testLocation);
	}

}
