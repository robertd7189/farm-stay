package com.fdm.FarmWorkSpringBoot.service;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdm.FarmWorkSpringBoot.dal.BusinessRepository;
import com.fdm.FarmWorkSpringBoot.model.Business;
/**
 * @author Robert.Dooley
 * @version 1.0
 */
@Service
public class BusinessService {

	private BusinessRepository businessRepository;
	private Log log = LogFactory.getLog(BusinessService.class);

	public BusinessService() {
		super();
	}

	@Autowired
	public BusinessService(BusinessRepository businessRepository) {
		super();
		this.businessRepository = businessRepository;
	}
	/**
	 * Saves a business to the database using the business repository
	 * 
	 * @param	business to be saved
	 * @return 	Business that has been saved
	 * @since 	1.0
	 */
	public Business saveBusiness(Business business) {
		if (businessRepository.findByName(business.getName()) == null)
			return this.businessRepository.save(business);
		return business;
	}
	/**
	 * Finds a business by name
	 * 
	 * @param	name as String
	 * @return 	Business
	 * @since 	1.0
	 */
	public Business findBusinessesByName(String name) {
		
		Business foundBusiness = businessRepository.findByName(name);
		log.info("business: " + foundBusiness + " name: " + name);
		if(foundBusiness == null)
			return null;
		
		return foundBusiness;
	}
	/**
	 * Finds a business by location
	 * 
	 * @param	location as String
	 * @return 	List of Businesses
	 * @since 	1.0
	 */
	public List<Business> findBusinessesByLocation(String location) {
		return (List<Business>) businessRepository.findByLocation(location);
	}
}
