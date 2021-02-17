package com.fdm.FarmWorkSpringBoot.dal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fdm.FarmWorkSpringBoot.model.User;
/**
 * @author Robert.Dooley
 * @version 1.0
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	/**
	 * Finds a list of users by email
	 * 
	 * @param	email as a String
	 * @return 	List of Users
	 * @since 	1.0
	 */
	List<User>  findByEmail(String email);
	/**
	 * Finds a User by their username
	 * 
	 * @param	username as a String
	 * @return 	User
	 * @since 	1.0
	 */
	User findByUsername(String username);
}
