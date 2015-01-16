package com.abs.domain;

public class Patient {
	
	private int id;
	private String firstName;
	private String lastName;
	private int wardId;	
	
	public Patient(int id, String firstName, String lastName, int wardId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.wardId = wardId;
	}
	
	public Patient(String firstName, String lastName, int wardId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.wardId = wardId;
	}
	
	public Patient(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getWardId() {
		return wardId;
	}
	public void setWardId(int wardId) {
		this.wardId = wardId;
	}
	
	

}
