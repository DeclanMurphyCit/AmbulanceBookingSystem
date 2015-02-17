package com.abs.domain;

public class User {
	
	private Integer id;
	private String userName;
	private String firstName;
	private String lastName;
	private Integer userType;

	public User(Integer id, String userName, String firstName, String lastName) {
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	//Replace with spring user security

}
