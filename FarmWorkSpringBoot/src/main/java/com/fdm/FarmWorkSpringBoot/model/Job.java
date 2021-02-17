package com.fdm.FarmWorkSpringBoot.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
/**
 * @author Robert.Dooley
 * @version 1.0
 */
@Entity
public class Job {
	
	@Id
	@GeneratedValue
	private int id;
	private String title;
	private String type;
	private double avgPay;
	
	
	@OneToMany(mappedBy = "job")
	private List<Review> reviews;


	public Job() {
		super();
	}


	public Job(String title, String type) {
		super();
		this.title = title;
		this.type = type;
	}


	@Override
	public String toString() {
		return "Job [id=" + id + ", title=" + title + ", type=" + type + ", avgPay=" + avgPay + "]";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public double getAvgPay() {
		return avgPay;
	}


	public void setAvgPay(double avgPay) {
		this.avgPay = avgPay;
	}


	public List<Review> getReviews() {
		return reviews;
	}


	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
}

