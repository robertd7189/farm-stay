package com.fdm.FarmWorkSpringBoot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.FarmWorkSpringBoot.dal.UserRepository;
import com.fdm.FarmWorkSpringBoot.model.User;
/**
 * @author Robert.Dooley
 * @version 1.0
 */
@Service
public class UserService {
	
	private UserRepository userRepository;

	public UserService() {
		super();
	}

	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	/**
	 * Saves a user to the database
	 * 
	 * @param	user to be saved
	 * @return 	User
	 * @since 	1.0
	 */
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	/**
	 * Finds a User by their username
	 * 
	 * @param	username as a String
	 * @return 	User
	 * @since 	1.0
	 */
	public User findUserByUsername(String username) {
		User foundUser = userRepository.findByUsername(username);
		if(foundUser == null)
			return null;
		
		return foundUser;
	}
	/**
	 * Finds all users in the database
	 * 
	 * @return 	List of all users
	 * @since 	1.0
	 */
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}
	/**
	 * Deletes a user from the databse
	 * 
	 * @param	username as a String
	 * @since 	1.0
	 */
	public void deleteUser(String username) {
		User user = findUserByUsername(username);
		userRepository.delete(user);
	}
	
	

}
