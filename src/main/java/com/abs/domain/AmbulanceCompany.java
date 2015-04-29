package com.abs.domain;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Date;

@XStreamAlias("AmbulanceCompany")
public class AmbulanceCompany implements Comparable<AmbulanceCompany>{
	
	private Integer id;
    private Integer userId;
	private String name;
	private double cost;
	private Integer costScore;
	private String timeActive;
	private String timeInactive;
	private boolean cardiac;
		
	public AmbulanceCompany(Integer id, Integer userId, String name, double cost, String timeActive,
							String timeInactive, boolean cardiac) {
		super();
		this.id = id;
        this.userId = userId;
		this.name = name;
		this.cost = cost;
		this.timeActive = timeActive;
		this.timeInactive = timeInactive;
		this.cardiac = cardiac;
	}

	public AmbulanceCompany(){}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCostScore() {
		System.out.println("CS: "+costScore);
		return costScore;
	}

	public void setCostScore(Integer costScore) {
		this.costScore = costScore;
	}

	public int compareTo(AmbulanceCompany ac) {
		double comparePrice = ((AmbulanceCompany) ac).getCost();

		if(this.cost == comparePrice) return 0;
		else if(this.cost > comparePrice) return 1;
		else return -1;
	}
}
