package com.abs.domain;

public class Patient {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private Integer wardId;
	
	public Patient(Integer id, String firstName, String lastName, Integer wardId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.wardId = wardId;
	}
	
	public Patient(String firstName, String lastName, Integer wardId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.wardId = wardId;
	}
	
	public Patient(){}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getWardId() {
		return wardId;
	}
	public void setWardId(Integer wardId) {
		this.wardId = wardId;
	}
	
	

}
