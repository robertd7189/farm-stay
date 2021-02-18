package com.fdm.FarmWorkSpringBoot.setup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.FarmWorkSpringBoot.model.Business;
import com.fdm.FarmWorkSpringBoot.model.Job;
import com.fdm.FarmWorkSpringBoot.model.Review;
import com.fdm.FarmWorkSpringBoot.model.User;
import com.fdm.FarmWorkSpringBoot.service.BusinessService;
import com.fdm.FarmWorkSpringBoot.service.JobService;
import com.fdm.FarmWorkSpringBoot.service.ReviewService;
import com.fdm.FarmWorkSpringBoot.service.UserService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DataLoaderTest {
	
	private DataLoader dataLoader;
	
	@Captor
	ArgumentCaptor<User> userCaptor;
	
	@Captor
	ArgumentCaptor<Business> businessCaptor;
	
	@Captor
	ArgumentCaptor<Review> reviewCaptor;
	
	@Captor
	ArgumentCaptor<Job> jobCaptor;
	
	@Mock
	private JobService mockJobService;
	
	@Mock
	private UserService mockUserService;
	
	@Mock
	private BusinessService mockBusinessService;
	
	@Mock
	private ReviewService mockReviewService;
//	
//	@Mock
//	private User mockUser;
//	
//	User user1 = new User("Robert", "asdsdfwr", "12345", "Robert", "Dooley");
//	User user2 = new User("James", "asdsdfwr", "asdfg", "James", "Dooley");
//	
//	Business bus1 = new Business("Joes Farm", "Kerang");
//	Business bus2 = new Business("Jerris Farm", "Quambatook");
//	
//	Job job1 = new Job("Fruit Picker", "Picker");
//	Job job2 = new Job("Grape Picker", "Picker");
//	
//	Review review1 = new Review("Easy Job", 15.97, "Share House", 160, user1, job1, bus1);
//	Review review2 = new Review("Fun Job", 15.97, "Share House", 160, user2, job1, bus1);
//	Review review3 = new Review("Hard Job", 17.00, "Caravan", 140, user1, job2, bus2);
//	Review review4 = new Review("Tough Job", 17.50, "Caravan", 140, user2, job2, bus2);
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		dataLoader = new DataLoader(mockUserService, mockBusinessService, mockReviewService, mockJobService);
	}
	
	@Test
	public void test_run_calls_all_appropriate_saves_from_services() {
		try {
			dataLoader.run(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		verify(mockUserService, times(2)).saveUser(userCaptor.capture());
		verify(mockJobService, times(2)).saveJob(jobCaptor.capture());
		verify(mockBusinessService, times(2)).saveBusiness(businessCaptor.capture());
		verify(mockReviewService, times(4)).saveReview(reviewCaptor.capture());
		assertEquals("James", userCaptor.getValue().getUsername());
		assertEquals("Jerris Farm", businessCaptor.getValue().getName());
		assertEquals("Grape Picker", jobCaptor.getValue().getTitle());
		assertEquals("Tough Job", reviewCaptor.getValue().getComment());
	}

}
