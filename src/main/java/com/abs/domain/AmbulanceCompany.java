package com.abs.domain;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Date;

@XStreamAlias("AmbulanceCompany")
public class AmbulanceCompany /*implements Comparable<AmbulanceCompany>*/{
	
	private int id;
	private String name;
	private double cost;
	private String timeActive;
	private String timeInactive;
	private boolean cardiac;
		
	public AmbulanceCompany(int id, String name, double cost, String timeActive,
							String timeInactive, boolean cardiac) {
		super();
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.timeActive = timeActive;
		this.timeInactive = timeInactive;
		this.cardiac = cardiac;
	}
	
	public AmbulanceCompany(String name, double cost, String timeActive,
							String timeInactive, boolean cardiac) {
		super();
		this.name = name;
		this.cost = cost;
		this.timeActive = timeActive;
		this.timeInactive = timeInactive;
		this.cardiac = cardiac;
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
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getTimeActive() {
		return timeActive;
	}
	public void setTimeActive(String timeActive) {
		this.timeActive = timeActive;
	}
	public String getTimeInactive() {
		return timeInactive;
	}
	public void setTimeInactive(String timeInactive) {
		this.timeInactive = timeInactive;
	}
	public boolean isCardiac() {
		return cardiac;
	}
	public void setCardiac(boolean cardiac) {
		this.cardiac = cardiac;
	}
}
