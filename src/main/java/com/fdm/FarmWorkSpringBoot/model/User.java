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
public class User {

	@Id
	@GeneratedValue
	private int id;
	private String username;
	private String email;
	private String password;
	private String fName;
	private String lName;
	
	@OneToMany(mappedBy = "user")
	private List<Review> reviews;
	
	public User() {
		super();
	}
	
	public User(String username, String email, String password, String fName, String lName) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", fName=" + fName + ", lName=" + lName + ", password=" + password
				+ ", email=" + email + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

}
