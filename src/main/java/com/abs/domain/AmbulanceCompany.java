package com.abs.domain;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Date;

@XStreamAlias("AmbulanceCompany")
public class AmbulanceCompany /*implements Comparable<AmbulanceCompany>*/{
	
	private int id;
	private String name;
	private double price;
	private Date timeActive;
	private Date timeInactive;
	private boolean cardiac;
	private int capacity;
		
	public AmbulanceCompany(int id, String name, double price, Date timeActive,
			Date timeInactive, boolean cardiac, int capacity) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.timeActive = timeActive;
		this.timeInactive = timeInactive;
		this.cardiac = cardiac;
		this.capacity = capacity;
	}
	
	public AmbulanceCompany(String name, double price, Date timeActive,
			Date timeInactive, boolean cardiac, int capacity) {
		super();
		this.name = name;
		this.price = price;
		this.timeActive = timeActive;
		this.timeInactive = timeInactive;
		this.cardiac = cardiac;
		this.capacity = capacity;
	}
	
	public AmbulanceCompany(){}
	
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getTimeActive() {
		return timeActive;
	}
	public void setTimeActive(Date timeActive) {
		this.timeActive = timeActive;
	}
	public Date getTimeInactive() {
		return timeInactive;
	}
	public void setTimeInactive(Date timeInactive) {
		this.timeInactive = timeInactive;
	}
	public boolean isCardiac() {
		return cardiac;
	}
	public void setCardiac(boolean cardiac) {
		this.cardiac = cardiac;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	

	

/*	public int compareTo(AmbulanceCompany ac) {
		double comparePrice = ((AmbulanceCompany) ac).getPrice();
		
		if(this.price == comparePrice) return 0;
		else if(this.price > comparePrice) return 1;
		else return -1;		
	}	*/
}
