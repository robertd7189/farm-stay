package com.fdm.FarmWorkSpringBoot.dal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fdm.FarmWorkSpringBoot.model.Business;
/**
 * @author Robert.Dooley
 * @version 1.0
 */
@Repository
public interface BusinessRepository  extends CrudRepository<Business, Integer>{
	/**
	 * Finds a business by location
	 * 
	 * @param	location as String
	 * @return 	List of Businesses
	 * @since 	1.0
	 */
	List<Business> findByLocation(String location);
	/**
	 * Finds a business by name
	 * 
	 * @param	name as String
	 * @return 	Business
	 * @since 	1.0
	 */
	Business findByName(String name);
}
