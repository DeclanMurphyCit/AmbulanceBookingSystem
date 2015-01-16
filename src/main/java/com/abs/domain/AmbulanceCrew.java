package com.abs.domain;

public class AmbulanceCrew {
	
	private int id;
	private boolean active;
	
	public AmbulanceCrew(int id, boolean active) {
		super();
		this.id = id;
		this.active = active;
	}
	
	public AmbulanceCrew(boolean active) {
		super();
		this.active = active;
	}
	
	public AmbulanceCrew(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
}