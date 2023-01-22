package com.profile.app.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Field.Write;

@Document
public class UserProfile {

	@Id 
	private String id;
	
	@Field
	private String fullName;
	
	@Field()
	private String email;
	
	@Field
	private Long mobileNo;
	
	@Field
	private String role;
	
	@Field
	private LocalDate dateOfBirth;
	
	@Field
	private String gender;
	
	@Field
	private String username;
	
	@Field
	private String password;
	
	@Field
	private String encodedPassword;
	
	@Field
	private Address address;
	
	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	public UserProfile(String id, String fullName, String email, Long mobileNo, String role, LocalDate dateOfBirth,
			String gender, String password, String encodedPassword, Address address) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.mobileNo = mobileNo;
		this.role = role;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.password = password;
		this.encodedPassword = encodedPassword;
		this.address = address;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getEncodedPassword() {
		return encodedPassword;
	}

	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "UserProfile [Id=" + id + ", fullName=" + fullName + ", email=" + email + ", mobileNo=" + mobileNo
				+ ", role=" + role + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", password=" + password
				+ "]";
	}
	
	
	
}
