package com.fdm.FarmWorkSpringBoot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
/**
 * @author Robert.Dooley
 * @version 1.0
 */
@Entity
public class Review {
	
	@Id
	@GeneratedValue
	private int id;
	private String comment;
	private double payRate;
	private String accommodationType;
	private double accommodationCost;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Job job;
	
	@ManyToOne
	private Business business;

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(String comment, double payRate, String accommodationType, double accommodationCost, User user,
			Job job, Business business) {
		super();
		this.comment = comment;
		this.payRate = payRate;
		this.accommodationType = accommodationType;
		this.accommodationCost = accommodationCost;
		this.user = user;
		this.job = job;
		this.business = business;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", comment=" + comment + ", payRate=" + payRate + ", accommodationType="
				+ accommodationType + ", accommodationCost=" + accommodationCost + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getPayRate() {
		return payRate;
	}

	public void setPayRate(double payRate) {
		this.payRate = payRate;
	}

	public String getAccommodationType() {
		return accommodationType;
	}

	public void setAccommodationType(String accommodationType) {
		this.accommodationType = accommodationType;
	}

	public double getAccommodationCost() {
		return accommodationCost;
	}

	public void setAccommodationCost(double accommodationCost) {
		this.accommodationCost = accommodationCost;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}
}

