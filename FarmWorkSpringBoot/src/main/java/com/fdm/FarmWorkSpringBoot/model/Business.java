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
public class Business {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String location;
	
	@OneToMany(mappedBy = "business")
	private List<Review> reviews;

	public Business() {
		super();
	}

	public Business(String name, String location) {
		super();
		this.name = name;
		this.location = location;
	}

	@Override
	public String toString() {
		return "Business [id=" + id + ", name=" + name + ", location=" + location + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	
}

