package com.profile.app.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Address {
	
	private int houseNumber;
	
	private String streetName;
	
	private String colonyName;
	
	private String city;
	
	private String state;
	
	private Long pinCode;
	
	

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(int houseNumber, String streetName, String colonyName, String city, String state, Long pinCode) {
		super();
		this.houseNumber = houseNumber;
		this.streetName = streetName;
		this.colonyName = colonyName;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getColonyName() {
		return colonyName;
	}

	public void setColonyName(String colonyName) {
		this.colonyName = colonyName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getPinCode() {
		return pinCode;
	}

	public void setPinCode(Long pinCode) {
		this.pinCode = pinCode;
	}
	
	
	
}
